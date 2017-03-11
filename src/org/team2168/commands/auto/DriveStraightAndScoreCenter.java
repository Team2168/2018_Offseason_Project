package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.gearintake.LowerGearArm;
import org.team2168.commands.gearintake.RaiseGearArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives straight out of launch pad
 */
public class DriveStraightAndScoreCenter extends CommandGroup {

    public DriveStraightAndScoreCenter() {
    	addSequential(new ShiftHigh());
        addSequential(new DriveXDistance(8.0,0.7,0.1));
        addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 1.0));
    	addSequential(new DriveXDistance(1.0, 0.7,0.1),2);
    	addSequential(new DriveXDistance(0.2, 0.7,0.1),2);
    	addSequential(new LowerGearArm());
    	addSequential(new DriveXDistance(-3.0,0.7,0.1));
    	addSequential(new RaiseGearArm());
    }
}
