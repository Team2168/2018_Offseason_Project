package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.DriveClimberWithConstant;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the 2017 climber :)
 * @author John Karoul
 */
public class Climber extends Subsystem {

	private Spark climberMotor1;
	private Spark climberMotor2;
	
	private static Climber instance = null;
	
	private Climber(){
		climberMotor1 = new Spark(RobotMap.CLIMBER_MOTOR_LEFT);
		climberMotor2 = new Spark(RobotMap.CLIMBER_MOTOR_RIGHT);
		
		climberMotor1.setExpiration(0.1);
		climberMotor2.setExpiration(0.1);
		
		climberMotor1.setSafetyEnabled(true);
		climberMotor2.setSafetyEnabled(true);
		
	}
	
	public static Climber getInstance(){
		if(instance == null)
				instance = new Climber();
		return instance;
	}
	
	public void driveClimber(double speed){
		driveLeftClimberMotor(speed);
		driveRightClimberMotor(speed);
	}
	
	private void driveLeftClimberMotor(double speed){
		if(RobotMap.CLIMB_MOTOR_REVERSE_LEFT)
			speed = -speed;
		
		if(speed < 0)
			speed = 0;
		climberMotor1.set(speed);
		
	}
		
	private void driveRightClimberMotor(double speed){
		
		if(RobotMap.CLIMB_MOTOR_REVERSE_RIGHT)
			speed = -speed;
		
		if(speed < 0)
			speed = 0;
		climberMotor2.set(speed);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveClimberWithConstant(0.0));
    }
}

