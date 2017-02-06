package org.team2168.commands.shooterHood;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to move the turret shooter hood with a controller joystick
 */
public class DriveHoodWithJoystick extends Command {

    public DriveHoodWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterhood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Takes the current angle of the shooter hood servo and adds to it based on
    // how far the right operator joystick is pushed on the Y-axis
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Math.abs(Robot.oi.operatorJoystick.getRightStickRaw_Y()) > 0.1)
    		Robot.shooterhood.setAngle(Robot.shooterhood.getAngle()
    				- (RobotMap.HOOD_JOYSTICK_MULTIPLIER * Robot.oi.operatorJoystick.getRightStickRaw_Y()));
    	
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
