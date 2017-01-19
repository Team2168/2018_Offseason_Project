package org.team2168.robot.commands;

import org.team2168.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveLiftWithJoystick extends Command {

    public DriveLiftWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double upSpeed = Robot.oi.driverJoystick.getRightTriggerAxisRaw();
    	double downSpeed = Robot.oi.driverJoystick.getLeftTriggerAxisRaw();
    	
    	Robot.lift.drive(upSpeed - downSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.drive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
