package org.team2168.commands.turret;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the angle of the turret from -90 to 90
 * @author Wen Baid
 */
public class SetTurretAngle extends Command {

	double angle;
	double error;
	boolean finished;
	
    public SetTurretAngle(double input, double error) {
    	requires(Robot.turret);
    	
    	angle = input;
    	this.error = error;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (((Robot.turret.getPosition()-error) > angle) && (angle < (Robot.turret.getPosition()+error))) {
    		finished = true;
    	}
    	else if (((Robot.turret.getPosition()-error) > angle) && !(angle < (Robot.turret.getPosition()+error))) {
    		Robot.turret.setSpeed(RobotMap.TURRET_MAX_DRIVE);
    	}
    	else if (!((Robot.turret.getPosition()-error) > angle) && (angle < (Robot.turret.getPosition()+error))) {
    		Robot.turret.setSpeed(-RobotMap.TURRET_MAX_DRIVE);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.turret.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.turret.setSpeed(0);
    }
}
