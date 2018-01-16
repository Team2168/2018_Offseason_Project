package org.team2168.commands.PivotArm;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Operate Pivot Arm with joysticks
 */
public class DrivePivotArmWithJoystick extends Command {
	private F310 joystick;
	private double speed;

    public DrivePivotArmWithJoystick(F310 joystick) {
        // Use requires() here to declare subsystem dependencies
        this.joystick = joystick;
    	requires(Robot.pivotArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double value = joystick.getLeftStickRaw_Y();
    	
    	Robot.pivotArm.drivePivotMotors(value * RobotMap.PIVOT_MAX_SPEED );
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pivotArm.drivePivotMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
