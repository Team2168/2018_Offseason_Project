package org.team2168.commands.drivetrain;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoystickEmergencyJoystick extends Command {
	
    public DriveWithJoystickEmergencyJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
		
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
		}

    /**
     * Gets the joystick positions from OI and sends them to the drivetrain subsystem.
     * @author Krystina
     */
    protected void execute() {

    	/**
    	 *Tank Drive
    	 */
    		Robot.drivetrain.driveLeft(Robot.oi.driverOperatorEJoystick.getLeftStickRaw_Y());
        	Robot.drivetrain.driveRight(Robot.oi.driverOperatorEJoystick.getRightStickRaw_Y());
        
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
