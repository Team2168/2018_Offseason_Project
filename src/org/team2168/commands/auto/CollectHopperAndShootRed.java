package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.ballIntake.LowerBallIntakeArm;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Goes to hopper to load fuel and then shoots into high boiler
 */
public class CollectHopperAndShootRed extends CommandGroup {

    public CollectHopperAndShootRed() {
    	//TODO make this work right
        addSequential(new DriveXDistance(8.0,0.7,0.1));
        addSequential(new RotateXDistancePIDZZZ(90, 0, 0, 1.0),3);
        addSequential(new LowerBallIntakeArm());
        addSequential(new DriveXDistance(3.0,0.7,0.1),2);
    }
}
