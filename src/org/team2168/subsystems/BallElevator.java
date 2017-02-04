package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Ball Elevator subsystem 
 *@author David M
 */
public class BallElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static Spark elevatorMotor;
	private BallElevator() {
		elevatorMotor = new Spark(RobotMap.ELEVATOR_MOTOR);
	}
	
	private static BallElevator instance = null;
	/** 
	 * singleton getter for Ball Elevator 
	 * @return instance 
	 */
	
	public static BallElevator getInstance(){
		if(instance==null)
			instance = new BallElevator();
		return instance;
	}
    /**
     * @param speed -1.0 to 1.0, positive runs the ball up the elevator, negative down
     */
	
	public void driveElevator(double speed){
		if(RobotMap.REVERSE_ELEVATOR_WHEEL)
			speed = -speed;
			elevatorMotor.set(speed);
	}
	
	public void initDefaultCommand() {
        setDefaultCommand(new MySpecialCommand());
    }
}

