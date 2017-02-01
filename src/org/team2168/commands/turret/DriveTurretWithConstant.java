package org.team2168.commands.turret;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs turret with constant
 * @author Wen Baid
 */
public class DriveTurretWithConstant extends Command {

	double speed;
	
    public DriveTurretWithConstant(double turretSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.turret);
    	
    	speed = turretSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.turret.driveTurretMotor(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
