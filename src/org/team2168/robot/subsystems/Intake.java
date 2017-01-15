package org.team2168.robot.subsystems;

import org.team2168.robot.RobotMap;
import org.team2168.robot.commands.DriveIntake;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Intake
 */
public class Intake extends Subsystem {

	Victor IntakeMotor1;
	Victor IntakeMotor2;
	
	private static Intake instance = null;
	
	public Intake() {
		
		IntakeMotor1 = new Victor(RobotMap.INTAKE_MOTOR_1);
		IntakeMotor2 = new Victor(RobotMap.INTAKE_MOTOR_2);
		
	}
	
	public static Intake getInstance(){
		if(instance == null)
			instance = new Intake();
		
		return instance;
	}
	
	public void driveIntakeMotor1(double speed) {
		IntakeMotor1.set(speed);
	}
	
	public void driveIntakeMotor2(double speed) {
		IntakeMotor2.set(speed);
	}

	public void driveIntake(double speed) {
		driveIntakeMotor1(speed);
		driveIntakeMotor2(speed);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveIntake(0));
    }
}

