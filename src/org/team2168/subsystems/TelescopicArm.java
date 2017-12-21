package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.TelescopicArm.operateTelescopicArmWithJoysticks;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the telescopic arm
 * @author DM
 */
public class TelescopicArm extends Subsystem {
	private static TelescopicArm instance = null;
	private Victor armMotor1;
	private Victor armMotor2;
	private Victor armMotor3;
	private DoubleSolenoid armBrake;
	private AnalogInput potentiometer;
	private static DigitalInput fullyUp;
	private static DigitalInput fullyDown;
	
	public volatile double telescopicMotor1Voltage;
	public volatile double telescopicMotor2Voltage;
	public volatile double telescopicMotor3Voltage;
	
	/**
	 * Default Constructer for telescopic arm
	 */
	private TelescopicArm() {
		armMotor1 = new Victor(RobotMap.ARM_MOTOR_1);
		armMotor2 = new Victor(RobotMap.ARM_MOTOR_2);
		armMotor3 = new Victor(RobotMap.ARM_MOTOR_3);
		armBrake = new DoubleSolenoid(RobotMap.ARM_BRAKE_FORWARD, RobotMap.ARM_BRAKE_REVERSE);
		fullyUp = new DigitalInput(RobotMap.ARM_RAISED);
		fullyDown = new DigitalInput(RobotMap.ARM_LOWERED);
		potentiometer = new AnalogInput(RobotMap.PIVOT_POSITION_POT);
	}
    
	/**
	 * singleton constructor of Telescopic Arm
	 */
	public static TelescopicArm GetInstance() {
		if (instance ==null)
			instance = new TelescopicArm();
		return instance;
	}
	
	/**
	 * Checks to see if arm is fully up
	 * @return true if pressed, false if not
	 */
	public boolean isArmFullyUp() {
		return !fullyUp.get();
	}
	
	/**
	 * Checks to see if arm is fully down
	 * @return true if pressed, false if not
	 */
	public boolean isArmFullyDown() {
		return !fullyDown.get();
	}
	
	public double getRawPot() {
		return potentiometer.getVoltage();
	}
	
	/**
	 * Drives the first Arm Motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void driveArmMotor1(double speed) {
		if(RobotMap.ARM_MOTOR_REVERSED)
			speed = -speed;
		armMotor1.set(speed);
		telescopicMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	/**
	 * Drives the second Arm Motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void driveArmMotor2(double speed) {
		if(RobotMap.ARM_MOTOR_REVERSED)
			speed = -speed;
		armMotor2.set(speed);
	telescopicMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	/**
	 * Drives the third Arm Motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void driveArmMotor3(double speed) {
		if(RobotMap.ARM_MOTOR_REVERSED)
			speed = -speed;
		armMotor3.set(speed);
		telescopicMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	/**
	 * Drives all Arm Motors at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	public void driveAllMotors(double speed) {
		if((speed > 0 && isArmFullyDown())||(speed < 0 && isArmFullyUp())){
			armMotor1.set(0);
			armMotor2.set(0);
			armMotor3.set(0);
		}
		else {
			armMotor1.set(speed);
			armMotor2.set(speed);
			armMotor3.set(speed);
		}
	}
	
	/**
	 * Enables the pneumatic brake
	 */
	public void enableBrake() {
		armBrake.set(Value.kForward);
	}
	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is enabled
	 */
	public boolean isBrakeEnabled() {
		return armBrake.get() == Value.kForward;
	}

	/**
	 * Disables the pneumatic brake
	 */
	public void disableBrake() {
		armBrake.set(Value.kReverse);
	}
	
	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is disabled
	 */
	public boolean isBrakeDisabled() {
		return armBrake.get() == Value.kReverse;
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new operateTelescopicArmWithJoysticks(OI.operatorJoystick));
    }
}

