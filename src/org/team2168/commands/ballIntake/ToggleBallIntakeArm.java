package org.team2168.commands.ballIntake;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggles (opens/closes) the ball intake arm at the specified rate
 */
public class ToggleBallIntakeArm extends Command {

	private static double openTime, closedTime;
	private static double nextToggleTime = 0.0;

	/**
	 * 
	 * @param openTime time the intake will stay in the opened position
	 * @param closedTime time the intake will stay in the closed position
	 */
    public ToggleBallIntakeArm(double openTime, double closedTime) {
    	requires(Robot.ballIntakeArm);
    	
        this.openTime = openTime;
        this.closedTime = closedTime;
        nextToggleTime = Timer.getFPGATimestamp();
    }
    
    /**
     * Create command with default toggle rate (1.3s open and 0.4s shut)
     */
    public ToggleBallIntakeArm() {
    	this(1.3, 0.4);
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
    		if(Robot.ballIntakeArm.isArmLowered()) {
        		nextToggleTime = currentTime + closedTime;
    			Robot.ballIntakeArm.Raise();
    		} else {
        		nextToggleTime = currentTime + openTime;
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
