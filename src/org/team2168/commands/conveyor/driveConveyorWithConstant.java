package org.team2168.commands.conveyor;

import org.team2168.Robot;
import edu.wpi.first.wpilibj.command.Command;


/**
 * drives conveyor with constant voltage
 */
public class driveConveyorWithConstant extends Command {

	double speed;
	
	/**
	 * runs conveyor at constant speed -1.0 - 1.0
	 * @param speed
	 */
    public driveConveyorWithConstant(double inputSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.conveyor);
       
        speed = inputSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.conveyor.driveConveyor(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
