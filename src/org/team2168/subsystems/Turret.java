package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.controllers.PIDPosition;
import org.team2168.PID.sensors.AveragePotentiometer;
import org.team2168.PID.sensors.TCPCamSensor;
import org.team2168.commands.turret.DriveTurretWithJoystick;
import org.team2168.utils.LinearInterpolator;
import org.team2168.utils.TCPSocketSender;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem class for Turret
 * @author Wen Baid
 */
public class Turret extends Subsystem {

    private static SpeedController turretMotor;
    private static AveragePotentiometer turretPot;
    private static DigitalInput limitSwitchRight;
    private static DigitalInput limitSwitchLeft;

	public PIDPosition rotateTurretCameraController;	
	TCPSocketSender TCPRotateTurretCameraController;
	
	public PIDPosition rotateTurretPOTController;	
	TCPSocketSender TCPRotateTurretPOTController;
    
    public TCPCamSensor tcpCamSensor;

    private static Turret instance = null;
    
    double turretPotMax;
    double turretPotMin;

    private static LinearInterpolator turretInterpolator;
    //TODO get these values plez format for points: (volts, degrees)
    private double[][] turretRange = {{RobotMap.TURRET_POT_VOLTAGE_MIN,-90.0},
    		                          {RobotMap.TURRET_POT_VOLTAGE_0,0.0},
    		                          {RobotMap.TURRET_POT_VOLTAGE_MAX,90.0}};
    
    double lastSpeedValue;
    
    /**
     * Default constructor for Turret subsystem
     */
    private Turret() {
    	if(Robot.isPracticeRobot())
		{
        	turretMotor = new Victor(RobotMap.TURRET_MOTOR);
		}
		else
		{
	    	turretMotor = new Spark(RobotMap.TURRET_MOTOR);
		}
    	
    	limitSwitchRight = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_RIGHT);
    	limitSwitchLeft = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_LEFT);
    	
    	tcpCamSensor = new TCPCamSensor("BoilerCam", RobotMap.BOILER_CAMERA_LISTEN_PORT, RobotMap.CAMERA_SENSOR_PERIOD);

    	rotateTurretCameraController = new PIDPosition(
				"RotationCameraController",
				RobotMap.ROTATE_POSITION_P,
				RobotMap.ROTATE_POSITION_I,
				RobotMap.ROTATE_POSITION_D,
				tcpCamSensor,
				RobotMap.TURRET_PID_PERIOD);
    	
    	
		rotateTurretCameraController.setSIZE(RobotMap.TURRET_PID_ARRAY_SIZE);

		rotateTurretCameraController.startThread();
		
		TCPRotateTurretCameraController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_TURRET_CAMERA_CONTROLLER, rotateTurretCameraController);
		TCPRotateTurretCameraController.start();
		
		if(Robot.isPracticeRobot()){
			turretPot = new AveragePotentiometer(RobotMap.TURRET_POTENTIOMETER,
					 RobotMap.TURRET_POT_VOLTAGE_MIN_PBOT, RobotMap.TURRET_POT_ANGLE_MIN_PBOT,
					 RobotMap.TURRET_POT_VOLTAGE_0_PBOT, RobotMap.TURRET_POT_ANGLE_0_PBOT,
					 RobotMap.TURRET_POT_VOLTAGE_MAX_PBOT, RobotMap.TURRET_POT_ANGLE_MAX_PBOT,
					 RobotMap.TURRET_AVG_ENCODER_VAL);
			turretPotMax = RobotMap.TURRET_POT_VOLTAGE_MAX_PBOT;
			turretPotMin = RobotMap.TURRET_POT_VOLTAGE_MIN_PBOT;
		}
		else {
			turretPot = new AveragePotentiometer(RobotMap.TURRET_POTENTIOMETER,
					 RobotMap.TURRET_POT_VOLTAGE_MIN, RobotMap.TURRET_POT_ANGLE_MIN,
					 RobotMap.TURRET_POT_VOLTAGE_0, RobotMap.TURRET_POT_ANGLE_0,
					 RobotMap.TURRET_POT_VOLTAGE_MAX, RobotMap.TURRET_POT_ANGLE_MAX,
					 RobotMap.TURRET_AVG_ENCODER_VAL);
			turretPotMax = RobotMap.TURRET_POT_VOLTAGE_MAX;
			turretPotMin = RobotMap.TURRET_POT_VOLTAGE_MIN;

		}
    	
		
		
		rotateTurretPOTController = new PIDPosition(
				"RotationCameraController",
				RobotMap.ROTATE_TURRET_P,
				RobotMap.ROTATE_TURRET_I,
				RobotMap.ROTATE_TURRET_D,
				turretPot,
				RobotMap.TURRET_PID_PERIOD);
    	
    	
		rotateTurretPOTController.setSIZE(RobotMap.TURRET_PID_ARRAY_SIZE);

		rotateTurretPOTController.startThread();
		
		TCPRotateTurretPOTController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_TURRET_POT_CONTROLLER, rotateTurretPOTController);
		TCPRotateTurretPOTController.start();
		
		
		
		
		

    	//For to be the very safest and to not break robot
    	((SafePWM) turretMotor).setExpiration(0.1);
    	((SafePWM) turretMotor).setSafetyEnabled(true);
    	
		ConsolePrinter.putBoolean("Turret Right Limit Switch", 
				() -> {return Robot.turret.isLimitSwitchRightActive();}, true, false);
		ConsolePrinter.putBoolean("Turret Left Limit Switch", 
				() -> {return Robot.turret.isLimitSwitchLeftActive();}, true, false);
		ConsolePrinter.putNumber("Turret Pot Interpolated", 
				() -> {return Robot.turret.getPosition();}, true, false);
		ConsolePrinter.putNumber("Turret Pot Raw", 
				() -> {return Robot.turret.getRawPot();}, true, false);
		
		ConsolePrinter.putBoolean("Boiler Camera Status", 
				() -> {return Robot.turret.tcpCamSensor.isCameraConnected();}, true, false);
		ConsolePrinter.putBoolean("Boiler Bone Status", 
				() -> {return Robot.turret.tcpCamSensor.isClientConnected();}, true, false);
		ConsolePrinter.putBoolean("Boiler Processing Status", 
				() -> {return Robot.turret.tcpCamSensor.isProcessingTreadRunning();}, true, false);
		ConsolePrinter.putBoolean("Boiler MJPEG Status", 
				() -> {return Robot.turret.tcpCamSensor.isMJPEGConnected();}, true, false);
		ConsolePrinter.putNumber("Boiler Vision Target Dist", 
				() -> {return Robot.turret.tcpCamSensor.getTargetDistance();}, true, false);
		ConsolePrinter.putNumber("Boiler Vision Target Bearing", 
				() -> {return Robot.turret.tcpCamSensor.getRotationAngle();}, true, false);
		ConsolePrinter.putBoolean("Is Boiler Target Detected", 
				() -> {return Robot.turret.tcpCamSensor.isTargetDetected();}, true, false);
		ConsolePrinter.putBoolean("Is Boiler Target Scorable", 
				() -> {return Robot.turret.tcpCamSensor.isTargetScorable();}, true, false);
		ConsolePrinter.putNumber("Turret Speed", () -> {return Robot.turret.getSpeed();}, true, false);
    }

    /**
     * Returns turret singleton object
     * @return turret singleton object
     */
	public static Turret getInstance() {
		if(instance == null)
			instance = new Turret();
		
		return instance;
	}

	/**
	 * Sets the speed of the motor
	 * @param speed of -1.0 (left/negative angles) to 1.0 (right/positive angles)
	 */
	public void setSpeed(double speed) {
		if(RobotMap.REVERSE_TURRET){
			speed = -speed;
			//Motor negative drives to positive angle
		}
		if(((speed < 0) && (getRawPot() >= turretPotMax))||(speed > 0) && (getRawPot() <= turretPotMin)){
			turretMotor.set(0);
		}
		else if(((speed < 0) && isLimitSwitchRightActive())||(speed > 0 && isLimitSwitchLeftActive())){
			turretMotor.set(0);
		}
		else {
			turretMotor.set(speed);
		}
		
		lastSpeedValue = speed;
		
	}
	
	public double getSpeed() {
		return lastSpeedValue;
	}

	/**
	 * Returns the current position of the turret
	 * @param x is voltage
	 * @return Potentiometer angle
	 */
	public double getPosition() {
		return turretPot.getPos();
	}
	
	public double getRawPot() {
		return turretPot.getRawPos();
		
	}

	/**
	 * Returns status of limit switch
	 * @return true if pressed, false if unpressed
	 */
	public boolean isLimitSwitchRightActive() {
		return limitSwitchRight.get();
	}

	/**
	 * Returns status of limit switch
	 * @return true if pressed, false if unpressed
	 */
	public boolean isLimitSwitchLeftActive() {
		return limitSwitchLeft.get();
	}

    public void initDefaultCommand() {
       // setDefaultCommand(new DriveTurretWithJoystick());
    }
}
