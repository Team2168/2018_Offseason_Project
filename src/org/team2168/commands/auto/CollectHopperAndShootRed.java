package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.agitator.DriveAgitatorWithConstant;
import org.team2168.commands.ballIntake.DriveIntakeWithConstant;
import org.team2168.commands.ballIntake.LowerBallIntakeArm;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.elevator.DriveElevatorWithConstant;
import org.team2168.commands.flashlight.EnableFlashlight;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.shooter.SetHoodToAngle;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.WaitForShooterPIDToFinish;
import org.team2168.commands.turret.SetTurretAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Goes to hopper to load fuel and then shoots into high boiler
 */
public class CollectHopperAndShootRed extends CommandGroup {

    public CollectHopperAndShootRed() {
    	//TODO make this work right
    	//Drive to hopper and collect fuel
        addSequential(new DriveXDistance(8.0,0.7,0.1));
        addSequential(new RotateXDistancePIDZZZ(90, 0, 0, 1.0),3);
        addSequential(new LowerBallIntakeArm());
        addSequential(new DriveXDistance(3.0,0.7,0.1),2);
        //Prepare for ripum gathering
        addParallel(new SetTurretAngle(-90, 3.0));
    	addParallel(new SetHoodToAngle(130),2);
    	addParallel(new DriveShooterPIDSpeed(5500));
    	//Gather the ripums
    	addSequential(new WaitForShooterPIDToFinish());
    	addSequential(new Sleep(), 3);
    	//Precipitate
    	addParallel(new DriveElevatorWithConstant(1.0));
    	addParallel(new DriveIndexerWithConstant(1.0));
    	addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(1.0));
    }
}
