package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.gearintake.LowerGearArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives straight out of launch pad
 */
public class DriveStraightAndScoreRight extends CommandGroup {

    public DriveStraightAndScoreRight() {
        addSequential(new DriveXDistance(9.5,0.7,0.1),4);
        addSequential(new RotateXDistancePIDZZZ(-60,0.7,0.2),2);
        addSequential(new DriveXDistance(2.5,0.7,0.1),3);
        addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 1.0),3);
    	addSequential(new DriveXDistance(1.0, 0.7,0.1),3);
    	addSequential(new DriveXDistance(0.2, 0.7,0.1),2);
    	addSequential(new LowerGearArm(),1);
    	addSequential(new DriveXDistance(-3.0,0.7,0.1),2);
    }
}
