package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the 2017 climber :)
 * @author John Karoul
 */
public class Climber extends Subsystem {

	private Spark liftMotor1;
	private Spark liftMotor2;
	
	private static Climber instance = null;
	
	private Climber(){
		liftMotor1 = new Spark(RobotMap.LIFT_MOTOR_1);
		liftMotor2 = new Spark(RobotMap.LIFT_MOTOR_2);
	}
	
	public static Climber getInstance(){
		if(instance == null)
				instance = new Climber();
		return instance;
	}
	
	public void driveClimber(double speed){
		if(RobotMap.LIFT_MOTOR_REVERSE)
			speed = -speed;
		
		liftMotor1.set(speed);
		liftMotor2.set(speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

