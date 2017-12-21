package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.PivotArm.DrivePivotArmWithJoystick;
import org.team2168.utils.LinearInterpolator;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem Class for Pivot Arm subsystem
 * @author DM
 */
public class PivotArm extends Subsystem {

	private static PivotArm instance = null;
	private Victor pivotMotor1;
	private Victor pivotMotor2;
	private DoubleSolenoid pivotBrake;
	private AnalogInput potentiometer;
	private static DigitalInput fullyLeft;
	private static DigitalInput fullyRight;
	private static LinearInterpolator pivotInterpolator;
	
	private double[][] pivotRange = {{-1.0,-100.0},
            {0.0,0.0},
            {1.0,100.0}};
	
	public volatile double pivotMotor1Voltage;
	public volatile double pivotMotor2Voltage;
	
	
	/**
	 * Default Constructer for telescopic arm
	 */
	private PivotArm() {
		pivotMotor1 = new Victor(RobotMap.PIVOT_MOTOR_1);
		pivotMotor2 = new Victor(RobotMap.PIVOT_MOTOR_2);
		pivotBrake = new DoubleSolenoid(RobotMap.PIVOT_BRAKE_FORWARD, RobotMap.PIVOT_BRAKE_REVERSE);

		potentiometer = new AnalogInput(RobotMap.PIVOT_POSITION_POT);
		pivotInterpolator = new LinearInterpolator(pivotRange);
	}
    
	/**
	 * singleton constructor of Telescopic Arm
	 */
	public static PivotArm GetInstance() {
		if (instance ==null)
			instance = new PivotArm();
		return instance;
	}
	
	/**
	 * Checks to see if arm is fully right
	 * @return true if pressed, false if not
	 */
	public boolean isArmFullyRight() {
		return !fullyRight.get();
	}
	
	/**
	 * Checks to see if arm is fully left
	 * @return true if pressed, false if not
	 */
	public boolean isArmFullyLeft() {
		return !fullyLeft.get();
	}
	/**
	 * Drives the first Arm Motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void drivePivotMotor1(double speed) {
		if(RobotMap.PIVOT_ARM_MOTOR_REVERSED)
			speed = -speed;
		pivotMotor1.set(speed);
		pivotMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	/**
	 * Drives the second Arm Motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void drivePivotMotor2(double speed) {
		if(RobotMap.PIVOT_ARM_MOTOR_REVERSED)
			speed = -speed;
		pivotMotor2.set(speed);
		pivotMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	
	public void drivePivotMotors(double speed) {
		if((speed > 0 && isArmFullyRight())||(speed < 0 && isArmFullyLeft())){
			pivotMotor1.set(0);
			pivotMotor2.set(0);
		}
		else {
			pivotMotor1.set(speed);
			pivotMotor2.set(speed);
		}
	}
	
	/**
	 * Enables the pneumatic brake
	 */
	public void enableBrake() {
		pivotBrake.set(Value.kForward);
	}
	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is enabled
	 */
	public boolean isBrakeEnabled() {
		return pivotBrake.get() == Value.kForward;
	}
	
	/**
	 * Disables the pneumatic brake
	 */
	public void disableBrake() {
		pivotBrake.set(Value.kReverse);
	}
	
	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is disabled
	 */
	
	public boolean isBrakeDisabled() {
		return pivotBrake.get() == Value.kReverse;
	}	
	
	/**
	 * Returns the current position of the Pivot Arm
	 * @param x is voltage
	 * @return Potentiometer position
	 */
	public double getPosition() {
		return pivotInterpolator.interpolate(potentiometer.getVoltage());
	}
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DrivePivotArmWithJoystick(OI.getInstance().operatorJoystick));
    }
}

