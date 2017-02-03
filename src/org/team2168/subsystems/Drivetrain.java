package org.team2168.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.commands.DriveWithJoystick;
import org.team2168.subsystems.Drivetrain;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Drivetrain
 * @author Aidan Sullivan
 */
public class Drivetrain extends Subsystem {
	
	private static VictorSP LeftMotor1;
	private static VictorSP LeftMotor2;
	private static VictorSP LeftMotor3;
	
	private static VictorSP RightMotor1;
	private static VictorSP RightMotor2;
	private static VictorSP RightMotor3;
	
	public ADXRS453Gyro gyroSPI;
	
	private DoubleSolenoid GearChanger;

	
	private static AverageEncoder RightEncoder;
	private static AverageEncoder LeftEncoder;
	
	private static Drivetrain instance = null;
	
	//For debugging only
	public volatile double LeftMotor1Voltage;
	public volatile double LeftMotor2Voltage;
	public volatile double LeftMotor3Voltage;
	public volatile double RightMotor1Voltage;
	public volatile double RightMotor2Voltage;
	public volatile double RightMotor3Voltage;

	
	/**
	 * Default constructors for Drivetrain
	 */
	private Drivetrain() {
		
		LeftMotor1 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_1);
		LeftMotor2 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_2);
		LeftMotor3 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_3);
		
		RightMotor1 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_1);
		RightMotor2 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_2);
		RightMotor3 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_3);
		
		
		GearChanger = new DoubleSolenoid(RobotMap.DOUBLE_SOLENOID_LEFT, RobotMap.DOUBLE_SOLENOID_RIGHT);
		
		
		
		RightEncoder = new AverageEncoder(
				RobotMap.RIGHT_DRIVE_ENCODER_A,
				RobotMap.RIGHT_DRIVE_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.RIGHT_DRIVE_TRAIN_ENCODER_REVERSE,
				RobotMap.DRIVE_ENCODING_TYPE,
				RobotMap.DRIVE_SPEED_RETURN_TYPE,
				RobotMap.DRIVE_POS_RETURN_TYPE,
				RobotMap.DRIVE_AVG_ENCODER_VAL);
				
				
		LeftEncoder = new AverageEncoder(
				RobotMap.LEFT_DRIVE_ENCODER_A, 
				RobotMap.LEFT_DRIVE_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.LEFT_DRIVE_TRAIN_ENCODER_REVERSE,
				RobotMap.DRIVE_ENCODING_TYPE,
				RobotMap.DRIVE_SPEED_RETURN_TYPE,
				RobotMap.DRIVE_POS_RETURN_TYPE,
				RobotMap.DRIVE_AVG_ENCODER_VAL);
		
				//Log sensor data
		ConsolePrinter.putNumber("Drivetrain Right Encoder", Drivetrain::getRightEncoder, true, false);
		ConsolePrinter.putNumber("Drivetrain Left Encoder", Drivetrain::getLeftEncoder, true, false);
		
		LeftMotor1Voltage = 0;
		LeftMotor2Voltage = 0;
		LeftMotor3Voltage= 0;
		RightMotor1Voltage = 0;
		RightMotor2Voltage = 0;
		RightMotor3Voltage = 0;
				
	}
	
	/**
	 * Calls instance object and makes it a singleton object of type Drivetrain
	 * @returns Drivetrain object "instance"
	 */
	public static Drivetrain getInstance(){
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
    private void driveLeftMotor1(double speed) {
    	if(RobotMap.DT_REVERSE_LEFT)
        	speed = -speed;
        	LeftMotor1.set(speed);
        	LeftMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;

    }
    
    /**
     * Calls left motor 2 and creates a local variable "speed"
     * Refers to boolean in Robot map and if true, speed = - speed
     * Uses set() command to assign the new speed to left motor 2
     * @param double speed between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    private void driveLeftMotor2(double speed) {
    	if(RobotMap.DT_REVERSE_LEFT)
        	speed = -speed;
        	LeftMotor2.set(speed);
        	LeftMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;

    }
    
    /**
     * Calls left motor 3 and creates a local variable "speed"
     * Refers to boolean in Robot map and if true, speed = - speed
     * Uses set() command to assign the new speed to left motor 3
     * @param double speed between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    private void driveLeftMotor3(double speed) {
    	if(RobotMap.DT_REVERSE_LEFT)
        	speed = -speed;
        	LeftMotor3.set(speed);
        	LeftMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;

    }
    
    /**
     * Take in double speed and sets it to left motors 1, 2, and 3
     * @param speed is a double between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    public void driveLeftSide(double speed) {
    	driveLeftMotor1(speed);
    	driveLeftMotor2(speed);
    	driveLeftMotor3(speed);
    }
    
    /**
     * Calls right motor 1 and creates a local variable "speed"
     * Refers to boolean in Robot map and if true, speed = - speed
     * Uses set() command to assign the new speed to right motor 1
     * @param double speed between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
   private void driveRightMotor1(double speed) {
	   if(RobotMap.DT_REVERSE_RIGHT)
	    	speed = -speed;
	    	RightMotor1.set(speed);
	    	RightMotor1Voltage = Robot.pdp.getBatteryVoltage() *speed;
    }
    
   /**
    * Calls right motor 2 and creates a local variable "speed"
    * Refers to boolean in Robot map and if true, speed = - speed
    * Uses set() command to assign the new speed to right motor 2
    * @param double speed between -1 and 1
    * negative is reverse, positive if forward, 0 is stationary
    */
    private void driveRightMotor2(double speed) {
    	if(RobotMap.DT_REVERSE_RIGHT)
        	speed = -speed;
        	RightMotor2.set(speed);
        	RightMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
    }
    
    /**
     * Calls right motor 3 and creates a local variable "speed"
     * Refers to boolean in Robot map and if true, speed = - speed
     * Uses set() command to assign the new speed to right motor 3
     * @param double speed between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    public void driveRightMotor3(double speed) {
    	if(RobotMap.DT_REVERSE_RIGHT)
        	speed = -speed;
        	RightMotor3.set(speed);
        	RightMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;
    }
    
    /**
     * Takes in a double speed and sets it to their right motors 1, 2, and 3
     * @param speed is a double  between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    public void driveRightSide(double speed) {
    	driveRightMotor1(speed);
    	driveRightMotor2(speed);
    	driveRightMotor3(speed);
    }
    
    /**
     * Takes in speed for right and speed for left and sets them to their respective sides 
     * @param leftSpeed is a double between -1 and 1 
     * @param rightSpeed is a double between -1 and 1
     * negative is reverse, positive if forward, 0 is stationary
     */
    public void driveRobot(double leftSpeed, double rightSpeed) {
    	driveLeftSide(leftSpeed);
    	driveRightSide(rightSpeed);
    }
    
 
   

    public static double getLeftEncoder() {
    	return LeftEncoder.get();
    }
    
    
    public static double getRightEncoder() {
    	return RightEncoder.get();
    }
    
    /**
     * Calls for default command of the drivetrain to be DriveWithJoystick
     */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
    /**
     * returns total distance traveled by right side of drivetrain
     * @return double in inches of total distance traveled by right encoder
     */
    public double getRightPosition(){
    	return RightEncoder.getPos();
    }
    
    /**
     * returns total distance traveled by left side of drivetrain
     * @return double in inches of total distance traveled by left encoder
     */
    public double getLeftPosition(){
    	return LeftEncoder.getPos();
    }
    
    /**
     * returns total distance traveled by drivetrain
     * @return double in inches of average distance traveled by both encoders
     */
    public double getAverageDistance(){
    	return (getRightPosition() + getLeftPosition())/2.0;
    }
    
    /**
     * resets position of right encoder to 0 inches
     */
    public void resetRightPosition(){
    	RightEncoder.reset();
    }
    /**
     * resets position of left encoder to 0 inches
     */
    public void resetLeftPosition(){
    	LeftEncoder.reset();
    }
    
    /**
     * resets position of both Encoders to 0 inches
     */
    public void resetPosition(){
    	resetLeftPosition();
    	resetRightPosition();
    }
    
    /**
     * returns heading of robot
     * @return double between 0 degrees and 360 degrees
     */
    public double getHeading(){
    	return gyroSPI.getPos();		
    }
    
    /**
     * Resets heading of IMU to 0 degrees
     */
    public void resetHeading(){
    	gyroSPI.reset();
    }
    
    /**
     * calls to calibrate gyro
     * Only used when robot is stationary
     */
    public void calibrateGyro(){
    	gyroSPI.calibrate();
    }
    
    /**
     * Terminates active gyro calibration sequence
     */
    public void stopGyroCalibrating(){
    	gyroSPI.stopCalibrating();
    }
    
    /**
     * Returns true if Gyro has calibrated
     */
    public boolean isGyroCalibrated(){
    	return gyroSPI.hasCompletedCalibration();
    }
    
    /**
     * Returns the last commanded voltage of Left Motor 1
     * @return Double in volts between 0 and 12
     */
    public double getLeftMotor1Voltage(){
    	return LeftMotor1Voltage;
    }
    
    /**
     * Returns the last commanded voltage of Left Motor 2
     * @return Double in volts between 0 and 12
     */
    public double getLeftMotor2Voltage(){
    	return LeftMotor2Voltage;
    }
    
    /**
     * Returns the last commanded voltage of Left Motor 3 
     * @return Double in volts between 0 and 12
     */
    public double getLeftMotor3Voltage(){
    	return LeftMotor3Voltage;
    } 
    
    /**
     * Returns the last commanded voltage of Right Motor 1
     * @return Double in volts between 0 and 12
     */
    public double getRightMotor1Voltage(){
    	return RightMotor1Voltage;
    } 
    
    /**
     * Returns the last commanded voltage of Right Motor 2
     * @return Double in volts between 0 and 12
     */
    public double getRightMotor2Voltage(){
    	return RightMotor2Voltage;
    } 
    
    /**
     * Returns the last commanded voltage of Right Motor 3
     * @return Double in volts between 0 and 12
     */
    public double getRightMotor3Voltage(){
    	return RightMotor3Voltage;
    }
    
	/**
	 * Shifts the Drivetrain from High to Low Gear
	 */
    public void shiftGearsHighToLow(){
    	GearChanger.set(DoubleSolenoid.Value.kForward);
    }
    
	/**
	 * Returns true if last commanded shift was High Gear
	 */
    public boolean gearIsHigh()
    {
    	return GearChanger.get()==DoubleSolenoid.Value.kReverse;
    }
    
	/**
	 * Shifts the Drivetrain from Low to High Gear
	 */
    public void shiftGearsLowToHigh(){
    	GearChanger.set(DoubleSolenoid.Value.kReverse);
    }
    
	/**
	 * Returns true if last commanded shift was Low Gear
	 */s
    public boolean gearIsLow()
    {
    	return GearChanger.get()==DoubleSolenoid.Value.kForward;
    }
    
    
}


