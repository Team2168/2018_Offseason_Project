package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.DriveIntake;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Intake
 */
public class Intake extends Subsystem {

	private static Victor intakeMotor1;
	
	private static Intake instance = null;
	
	private Intake() {
		intakeMotor1 = new Victor(RobotMap.INTAKE_MOTOR_1);
		
	}
	
	public static Intake getInstance(){
		if(instance == null)
			instance = new Intake();
		
		return instance;
	}
	
	public void driveIntakeMotor1(double speed) {
		if(RobotMap.REVERSE_INTAKE_WHEEL_1)
			speed = -speed;
		intakeMotor1.set(speed);
	}
	

	/**
	 * 
	 * @param speed positive values shoot a ball out of the robot
	 */
	public void driveIntake(double speed) {
		driveIntakeMotor1(speed);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveIntake(0));
    }
}

