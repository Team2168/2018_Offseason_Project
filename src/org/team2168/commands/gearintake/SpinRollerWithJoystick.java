package org.team2168.commands.gearintake;

import org.team2168.Robot;
import org.team2168.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Controls the roller with the right joystick.
 *@author Elijah Reeds
 */
public class SpinRollerWithJoystick extends Command {

    public SpinRollerWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearIntakeRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearIntakeRoller.setMotorSpeed(OI.operatorJoystick.getRightStickRaw_Y());
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
