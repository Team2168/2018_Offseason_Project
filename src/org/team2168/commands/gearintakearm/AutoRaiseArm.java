package org.team2168.commands.gearintakearm;

import org.team2168.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRaiseArm extends Command {

    public AutoRaiseArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearIntakeArm);
    	requires(Robot.gearIntakeRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearIntakeRoller.isGearPresent() && Robot.gearIntakeArm.isArmLowered()){
    		Robot.gearIntakeArm.Raise();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gearIntakeArm.isArmRaised();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
