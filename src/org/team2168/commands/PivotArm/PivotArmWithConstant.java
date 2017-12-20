package org.team2168.commands.PivotArm;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PivotArmWithConstant extends Command {

	double speed;
    public PivotArmWithConstant(double armSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pivotArm);
        speed = armSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pivotArm.drivePivotMotors(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pivotArm.drivePivotMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
