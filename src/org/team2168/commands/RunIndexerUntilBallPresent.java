package org.team2168.commands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs indexer until a ball is present
 * @author Wen Baid
 */
public class RunIndexerUntilBallPresent extends Command {
double speed;
	
    public RunIndexerUntilBallPresent(double speed) {
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
    	return Robot.shooterIndexer.isBallPresent();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterIndexer.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
