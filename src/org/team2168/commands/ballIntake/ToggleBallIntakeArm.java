package org.team2168.commands.ballIntake;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggles (opens/closes) the ball intake arm at the specified rate
 */
public class ToggleBallIntakeArm extends Command {

	private static double toggleRate;
	private static double nextToggleTime = 0.0;
	
	/**
	 * 
	 * @param rate Time in seconds to wait between toggling the intakes position.
	 */
    public ToggleBallIntakeArm(double rate) {
    	requires(Robot.ballIntakeArm);
    	
        toggleRate = rate;
        nextToggleTime = Timer.getFPGATimestamp();
    }
    
    /**
     * Create command with default toggle rate of 0.7 seconds between transitions
     */
    public ToggleBallIntakeArm() {
    	this(0.7);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.ballIntakeArm.Raise();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentTime = Timer.getFPGATimestamp();
    	
    	//Have we waited long enough?
    	if(currentTime >= nextToggleTime) {
    		nextToggleTime = currentTime + toggleRate;

    		if(Robot.ballIntakeArm.isArmLowered()) {
    			Robot.ballIntakeArm.Raise();
    		} else {
    			Robot.ballIntakeArm.Lower();
    		}
    	}
    }

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
