package org.team2168.commands.shooterHood;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to set the angle of the turret shooter hood
 * @author Peter Dentch
 */
public class SetHoodToAngle extends Command {

	double angle;
	
	/**
	 * Method which sets the parameter inputAngle to the double angle defined above
	 * @param inputAngle takes in an angle to which the shooter hood will be set
	 */
    public SetHoodToAngle(double inputAngle) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterhood);
    	
    	angle = inputAngle;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Takes in an angle for the servo motor's position and sets it to that angle
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterhood.setAngle(angle);
    }

    /**
     * This is when the command went through, not necessarily when the hood reached its intended angle
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.shooterhood.getAngle() == angle)
        	return true;
        else
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
