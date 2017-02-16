package org.team2168.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.controllers.PIDPosition;
import org.team2168.PID.controllers.PIDSpeed;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.PID.sensors.IMU;
import org.team2168.PID.sensors.TCPCamSensor;
import org.team2168.commands.drivetrain.DriveWithJoystick;
import org.team2168.subsystems.Drivetrain;
import org.team2168.utils.TCPSocketSender;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Drivetrain
 * @author Aidan Sullivan
 */
public class Drivetrain extends Subsystem {
	
	private static VictorSP leftMotor1;
	private static VictorSP leftMotor2;
	private static VictorSP rightMotor1;
	private static VictorSP rightMotor2;

	private ADXRS453Gyro gyroSPI;
	private AverageEncoder drivetrainLeftEncoder;
	private AverageEncoder drivetrainRightEncoder;
	
	public IMU imu;
	public TCPCamSensor tcpCamSensor;
	
	//declare position/speed controllers
	public PIDPosition driveTrainPosController;
	public PIDPosition rotateController;
	public PIDPosition rotateDriveStraightController;
	public PIDPosition rotateCameraController;	
	
	//declare speed controllers
	public PIDSpeed rightSpeedController;
	public PIDSpeed leftSpeedController;
	
	DoubleSolenoid gearShifter;
	
	private static Drivetrain instance = null;
	

	
	//declare TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	TCPSocketSender TCPdrivePosController;
	TCPSocketSender TCPrightSpeedController;
	TCPSocketSender TCPleftSpeedController;
	TCPSocketSender TCProtateController;
	TCPSocketSender TCProtateCameraController;
	
	
	public volatile double leftMotor1Voltage;
	public volatile double leftMotor2Voltage;
	public volatile double rightMotor1Voltage;
	public volatile double rightMotor2Voltage;
	
	
	/**
	 * Default constructors for Drivetrain
	 */
	private Drivetrain() {
		leftMotor1 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_1);
		leftMotor2 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_2);
		rightMotor1 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_1);
		rightMotor2 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_2);

		drivetrainRightEncoder = new AverageEncoder(
				RobotMap.RIGHT_DRIVE_ENCODER_A,
				RobotMap.RIGHT_DRIVE_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.RIGHT_DRIVE_TRAIN_ENCODER_REVERSE,
				RobotMap.DRIVE_ENCODING_TYPE,
				RobotMap.DRIVE_SPEED_RETURN_TYPE,
				RobotMap.DRIVE_POS_RETURN_TYPE,
				RobotMap.DRIVE_AVG_ENCODER_VAL);

		drivetrainLeftEncoder = new AverageEncoder(
				RobotMap.LEFT_DRIVE_ENCODER_A, 
				RobotMap.LEFT_DRIVE_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.LEFT_DRIVE_TRAIN_ENCODER_REVERSE,
				RobotMap.DRIVE_ENCODING_TYPE,
				RobotMap.DRIVE_SPEED_RETURN_TYPE,
				RobotMap.DRIVE_POS_RETURN_TYPE,
				RobotMap.DRIVE_AVG_ENCODER_VAL);
		
		gyroSPI = new ADXRS453Gyro();
		gyroSPI.startThread();
		
		imu = new IMU(drivetrainLeftEncoder,drivetrainRightEncoder,RobotMap.WHEEL_BASE);

		tcpCamSensor = new TCPCamSensor(RobotMap.GEAR_CAMERA_LISTEN_PORT, RobotMap.CAMERA_SENSOR_PERIOD);

		//enable shifting solenoids
		gearShifter = new DoubleSolenoid(RobotMap.DRIVETRAIN_LOW_GEAR, RobotMap.DRIVETRAIN_HIGH_GEAR);
		
		//DriveStraight Controller
				rotateController = new PIDPosition(
						"RotationController",
						RobotMap.ROTATE_POSITION_P,
						RobotMap.ROTATE_POSITION_I,
						RobotMap.ROTATE_POSITION_D,
						gyroSPI,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);
				
				rotateCameraController = new PIDPosition(
						"RotationCameraController",
						RobotMap.ROTATE_POSITION_P,
						RobotMap.ROTATE_POSITION_I,
						RobotMap.ROTATE_POSITION_D,
						tcpCamSensor,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);
				
				rotateDriveStraightController = new PIDPosition(
						"RotationStraightController",
						RobotMap.ROTATE_POSITION_P_Drive_Straight,
						RobotMap.ROTATE_POSITION_I_Drive_Straight,
						RobotMap.ROTATE_POSITION_D_Drive_Straight,
						gyroSPI,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);

				driveTrainPosController = new PIDPosition(
						"driveTrainPosController",
						RobotMap.DRIVE_TRAIN_RIGHT_POSITION_P,
						RobotMap.DRIVE_TRAIN_RIGHT_POSITION_I,
						RobotMap.DRIVE_TRAIN_RIGHT_POSITION_D,
						imu,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);

				//Spawn new PID Controller
				rightSpeedController = new PIDSpeed(
						"rightSpeedController",
						RobotMap.DRIVE_TRAIN_RIGHT_SPEED_P,
						RobotMap.DRIVE_TRAIN_RIGHT_SPEED_I,
						RobotMap.DRIVE_TRAIN_RIGHT_SPEED_D, 1,
						drivetrainRightEncoder,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);

				leftSpeedController = new PIDSpeed(
						"leftSpeedController",
						RobotMap.DRIVE_TRAIN_LEFT_SPEED_P,
						RobotMap.DRIVE_TRAIN_LEFT_SPEED_I,
						RobotMap.DRIVE_TRAIN_LEFT_SPEED_D, 1,
						drivetrainLeftEncoder,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);


				//add min and max output defaults and set array size
				rightSpeedController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				leftSpeedController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				driveTrainPosController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				rotateController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				rotateDriveStraightController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				rotateCameraController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);

				//start controller threads
				rightSpeedController.startThread();
				leftSpeedController.startThread();
				driveTrainPosController.startThread();
				rotateController.startThread();
				rotateDriveStraightController.startThread();
				rotateCameraController.startThread();

				
				
				
				
				//start TCP Servers for DEBUGING ONLY
				TCPdrivePosController = new TCPSocketSender(RobotMap.TCP_SERVER_DRIVE_TRAIN_POS, driveTrainPosController);
				TCPdrivePosController.start();

				TCPrightSpeedController = new TCPSocketSender(RobotMap.TCO_SERVER_RIGHT_DRIVE_TRAIN_SPEED, rightSpeedController);
				TCPrightSpeedController.start();

				TCPleftSpeedController = new TCPSocketSender(RobotMap.TCP_SERVER_LEFT_DRIVE_TRAIN_SPEED, leftSpeedController);
				TCPleftSpeedController.start();

				TCProtateController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_CONTROLLER, rotateController);
				TCProtateController.start();
				
				TCProtateController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_CONTROLLER_STRAIGHT, rotateDriveStraightController);
				TCProtateController.start();
				
				TCProtateCameraController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_CAMERA_CONTROLLER, rotateCameraController);
				TCProtateCameraController.start();
				
				leftMotor1Voltage = 0;
				leftMotor2Voltage = 0;

				rightMotor1Voltage = 0;
				rightMotor2Voltage = 0;

		
		
		
		
		//Log sensor data
		ConsolePrinter.putNumber("Drivetrain right Encoder",
				() -> {return Drivetrain.getInstance().getRightPosition();}, true, false);
		ConsolePrinter.putNumber("Drivetrain left Encoder",
				() -> {return Drivetrain.getInstance().getLeftPosition();}, true, false);
		ConsolePrinter.putNumber("Drivetrain Heading",
				() -> {return Drivetrain.getInstance().getHeading();}, true, false);
		ConsolePrinter.putBoolean("Drivetrain Gyro Calibrated",
				() -> {return Drivetrain.getInstance().isGyroCalibrated();}, true, false);
	}
	
	/**
	 * Calls instance object and makes it a singleton object of type Drivetrain
	 * @returns Drivetrain object "instance"
	 */
	public static Drivetrain getInstance() {
		if(instance == null)
			instance = new Drivetrain();
		
		return instance;
	}
    
	/**
     * Calls left motor 1 and creates a local variable "speed"
     * Refers to boolean in Robot map and if true, speed = - speed
     * Uses set() command to assign the new speed to left motor 1
     * @param double speed between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    private void driveleftMotor1(double speed) {
    	if(RobotMap.DT_REVERSE_LEFT)
        	speed = -speed;

    	leftMotor1.set(speed);
    	leftMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;

    }
    
    /**
     * Calls left motor 2 and creates a local variable "speed"
     * Refers to boolean in Robot map and if true, speed = - speed
     * Uses set() command to assign the new speed to left motor 2
     * @param double speed between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    private void driveleftMotor2(double speed) {
    	if(RobotMap.DT_REVERSE_LEFT)
        	speed = -speed;

    	leftMotor2.set(speed);
    	leftMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
    }
    
    /**
     * Take in double speed and sets it to left motors 1, 2, and 3
     * @param speed is a double between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    public void driveLeft(double speed) {
    	driveleftMotor1(speed);
    	driveleftMotor2(speed);
    }
    
    /**
     * Calls right motor 1 and creates a local variable "speed"
     * Refers to boolean in Robot map and if true, speed = - speed
     * Uses set() command to assign the new speed to right motor 1
     * @param double speed between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
   private void driverightMotor1(double speed) {
	   if(RobotMap.DT_REVERSE_RIGHT)
	    	speed = -speed;

    	rightMotor1.set(speed);
    	rightMotor1Voltage = Robot.pdp.getBatteryVoltage() *speed;
    }
    
   /**
    * Calls right motor 2 and creates a local variable "speed"
    * Refers to boolean in Robot map and if true, speed = - speed
    * Uses set() command to assign the new speed to right motor 2
    * @param double speed between -1 and 1
    * negative is reverse, positive if forward, 0 is stationary
    */
    private void driverightMotor2(double speed) {
    	if(RobotMap.DT_REVERSE_RIGHT)
        	speed = -speed;
    	
    	rightMotor2.set(speed);
    	rightMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
    }
    
    /**
     * Takes in a double speed and sets it to their right motors 1, 2, and 3
     * @param speed is a double  between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    public void driveRight(double speed) {
    	driverightMotor1(speed);
    	driverightMotor2(speed);
    }
    
    /**
     * Takes in speed for right and speed for left and sets them to their respective sides 
     * @param leftSpeed is a double between -1 and 1 
     * @param rightSpeed is a double between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	driveLeft(leftSpeed);
    	driveRight(rightSpeed);
    }

    /**
     * Calls for default command of the drivetrain to be DriveWithJoystick
     */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
    /**
     * returns total distance traveled by right side of drivetrain
     * @return double in feet of total distance traveled by right encoder
     */
    public double getRightPosition() {
    	return drivetrainRightEncoder.getPos();
    }
    
    /**
     * returns total distance traveled by left side of drivetrain
     * @return double in feet of total distance traveled by left encoder
     */
    public double getLeftPosition() {
    	return drivetrainLeftEncoder.getPos();
    }
    
    /**
     * returns total distance traveled by drivetrain
     * @return double in inches of average distance traveled by both encoders
     */
    public double getAverageDistance() {
    	return (getRightPosition() + getLeftPosition())/2.0;
    }
    
    /**
     * resets position of right encoder to 0 inches
     */
    public void resetRightPosition() {
    	drivetrainRightEncoder.reset();
    }
    /**
     * resets position of left encoder to 0 inches
     */
    public void resetLeftPosition() {
    	drivetrainLeftEncoder.reset();
    }
    
    /**
     * resets position of both Encoders to 0 inches
     */
    public void resetPosition() {
    	resetLeftPosition();
    	resetRightPosition();
    }
    
    /**
     * returns heading of robot
     * @return double between 0 degrees and 360 degrees
     */
    public double getHeading() {
    	return gyroSPI.getPos();		
    }
    
    /**
     * Resets heading of IMU to 0 degrees
     */
    public void resetHeading() {
    	gyroSPI.reset();
    }
    
    /**
     * calls to calibrate gyro
     * Only used when robot is stationary
     */
    public void calibrateGyro() {
    	gyroSPI.calibrate();
    }
    
    /**
     * Terminates active gyro calibration sequence
     */
    public void stopGyroCalibrating() {
    	gyroSPI.stopCalibrating();
    }
    
    /**
     * Returns true if Gyro has calibrated
     */
    public boolean isGyroCalibrated() {
    	return gyroSPI.hasCompletedCalibration();
    }
    
    /**
     * Returns the last commanded voltage of left Motor 1
     * @return Double in volts between 0 and 12
     */
    public double getleftMotor1Voltage() {
    	return leftMotor1Voltage;
    }
    
    /**
     * Returns the last commanded voltage of left Motor 2
     * @return Double in volts between 0 and 12
     */
    public double getleftMotor2Voltage() {
    	return leftMotor2Voltage;
    }
    
    /**
     * Returns the last commanded voltage of right Motor 1
     * @return Double in volts between 0 and 12
     */
    public double getrightMotor1Voltage() {
    	return rightMotor1Voltage;
    } 
    
    /**
     * Returns the last commanded voltage of right Motor 2
     * @return Double in volts between 0 and 12
     */
    public double getrightMotor2Voltage() {
    	return rightMotor2Voltage;
    }
    
    /**
	 * Shifts the Drivetrain from High to Low Gear
	 */
    public void shiftGearsHighToLow(){
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    }
    
	/**
	 * Returns true if last commanded shift was High Gear
	 */
    public boolean gearIsHigh()
    {
    	return gearShifter.get()==DoubleSolenoid.Value.kReverse;
    }
    
	/**
	 * Shifts the Drivetrain from Low to High Gear
	 */
    public void shiftGearsLowToHigh(){
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
    
	/**
	 * Returns true if last commanded shift was Low Gear
	 */
    public boolean gearIsLow()
    {
    	return gearShifter.get()==DoubleSolenoid.Value.kForward;
    }
}
