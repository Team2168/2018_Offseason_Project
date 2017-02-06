package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.ballIntake.DriveIntakeWithJoystick;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the ball intake
 */
public class BallIntake extends Subsystem {

	private static Spark intakeMotor;
	
	private static BallIntake instance = null;
	
	private BallIntake() {
		intakeMotor = new Spark(RobotMap.BALL_INTAKE_MOTOR);
	}
	
	public static BallIntake getInstance(){
		if(instance == null)
			instance = new BallIntake();
		
		return instance;
	}
	
	/**
	 * 
	 * @param speed 1.0 to -1.0, positive values bring balls into the robot.
	 */
	public void driveIntake(double speed) {
		if(RobotMap.REVERSE_INTAKE_WHEEL)
			speed = -speed;
		
		intakeMotor.set(speed);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveIntakeWithJoystick());
    }
}

