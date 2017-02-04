package org.team2168.commands.indexer;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs indexer until a ball is not present
 * @author Wen Baid
 */
public class RunIndexerUntilBallNotPresent extends Command {
	double speed;
	
	/**
	 * 
	 * @param speed -1.0 to 1.0. Positive values bring a ball into the shooter.
	 */
    public RunIndexerUntilBallNotPresent(double speed) {
    	requires(Robot.shooterIndexer);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.shooterIndexer.setSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.shooterIndexer.isBallAbsent();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterIndexer.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
