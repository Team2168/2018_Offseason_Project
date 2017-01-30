package org.team2168.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

import org.team2168.RobotMap;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.commands.DriveWithJoystick;
import org.team2168.subsystems.Drivetrain;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Drivetrain
 */
public class Drivetrain extends Subsystem {
	
	private static VictorSP LeftMotor1;
	private static VictorSP LeftMotor2;
	private static VictorSP LeftMotor3;
	
	private static VictorSP RightMotor1;
	private static VictorSP RightMotor2;
	private static VictorSP RightMotor3;
	
	public ADXRS453Gyro gyroSPI;
	
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

	
	
	private Drivetrain() {
		
		LeftMotor1 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_1);
		LeftMotor2 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_2);
		LeftMotor3 = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR_3);
		
		RightMotor1 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_1);
		RightMotor2 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_2);
		RightMotor3 = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR_3);
		
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
				RobotMap.RIGHT_DRIVE_ENCODER_A, 
				RobotMap.RIGHT_DRIVE_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.RIGHT_DRIVE_TRAIN_ENCODER_REVERSE,
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
	
	public static Drivetrain getInstance(){
		if(instance == null)
			instance = new Drivetrain();
		
		return instance;
	}
    
    public void driveLeftMotor1(double speed) {
    	LeftMotor1.set(speed);
    }
    
    public void driveLeftMotor2(double speed) {
    	LeftMotor2.set(speed);
    }
    
    public void driveLeftMotor3(double speed) {
    	LeftMotor3.set(speed);
    }
    
    public void driveLeftSide(double speed) {
    	driveLeftMotor1(speed);
    	driveLeftMotor2(speed);
    	driveLeftMotor3(speed);
    }
    
    public void driveRightMotor1(double speed) {
    	RightMotor1.set(speed);
    }
    
    public void driveRightMotor2(double speed) {
    	RightMotor2.set(speed);
    }
    
    public void driveRightMotor3(double speed) {
    	RightMotor3.set(speed);
    }
    
    public void driveRightSide(double speed) {
    	driveRightMotor1(speed);
    	driveRightMotor2(speed);
    	driveRightMotor3(speed);
    }
    
    public void driveRobot(double leftSpeed, double rightSpeed) {
    	driveLeftSide(leftSpeed);
    	driveRightSide(rightSpeed);
    }
    
    public void getLeftMotor1(double speed){
    	if(RobotMap.DT_REVERSE_LEFT)
    	speed = -speed;
    	LeftMotor1.set(speed);
    }
    public void getLeftMotor2(double speed){
    	if(RobotMap.DT_REVERSE_LEFT)
    	speed = -speed;
    	LeftMotor2.set(speed);
    } 
    public void getLeftMotor3(double speed){
    	if(RobotMap.DT_REVERSE_LEFT)
    	speed = -speed;
    	LeftMotor3.set(speed);
    }
    public void getRightMotor1(double speed){
    	if(RobotMap.DT_REVERSE_LEFT)
    	speed = -speed;
    	RightMotor1.set(speed);
    }
    public void getrightmotor2(double speed){
    	if(RobotMap.DT_REVERSE_LEFT)
    	speed = -speed;
    	RightMotor2.set(speed);
    }
    public void getrightmotor3(double speed){
    	if(RobotMap.DT_REVERSE_LEFT)
    	speed = -speed;
    	RightMotor3.set(speed);
    }

    
    		

    public static double getLeftEncoder() {
    	return LeftEncoder.get();
    }
    
    public static double getRightEncoder() {
    	return RightEncoder.get();
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
    public double getRightPosition(){
    	return RightEncoder.getPos();
    }
    public double getLeftPosition(){
    	return LeftEncoder.getPos();
    }
    public double getAverageDistance(){
    	return (getRightPosition() + getLeftPosition())/2.0;
    }
    
    public void resetRightPosition(){
    	RightEncoder.reset();
    }
    public void resetLeftPosition(){
    	LeftEncoder.reset();
    }
    public void resetPosition(){
    	resetLeftPosition();
    	resetRightPosition();
    }
    
    public double getHeading(){
    	return gyroSPI.getPos();		
    }
    public void resetHeading(){
    	gyroSPI.reset();
    }
    
    public void calibrateGyro(){
    	gyroSPI.calibrate();
    }
    
    public void stopGyroCalibrating(){
    	gyroSPI.stopCalibrating();
    }
    public boolean isGyroCalibrated(){
    	return gyroSPI.hasCompletedCalibration();
    }
    
    /**
     * 
     * @return
     */
    public double getLeftMotor1Voltage(){
    	return LeftMotor1Voltage;
    }
    public double getLeftMotor2Voltage(){
    	return LeftMotor1Voltage;
    } 
    public double getLeftMotor3Voltage(){
    	return LeftMotor1Voltage;
    } 
    public double getRightMotor1Voltage(){
    	return RightMotor1Voltage;
    } 
    public double getRightMotor2Voltage(){
    	return RightMotor2Voltage;
    } 
    public double getRightMotor13Voltage(){
    	return RightMotor3Voltage;
    }
    
    
}


