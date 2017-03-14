package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.agitator.DriveAgitatorWithConstant;
import org.team2168.commands.ballIntake.DriveIntakeWithConstant;
import org.team2168.commands.ballIntake.LowerBallIntakeArm;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.elevator.DriveElevatorWithConstant;
import org.team2168.commands.gearintake.LowerGearArmDANGEROUS;
import org.team2168.commands.gearintake.RaiseGearArm;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.WaitForShooterPIDToFinish;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives straight out of launch pad
 */
public class DriveStraightAndScoreCenterShooting extends CommandGroup {

    public DriveStraightAndScoreCenterShooting() {
        addSequential(new DriveXDistance(8.0,0.7,0.1));
        addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 1.0),1);
    	addSequential(new DriveXDistance(0.7, 0.7,0.1),0.7);
    	addSequential(new DriveXDistance(0.4, 0.7,0.1),0.7);
    	addSequential(new LowerGearArmDANGEROUS(),0.5);
    	addSequential(new Sleep(), 0.6);
    	addSequential(new DriveXDistance(-3.0,0.7,0.1));
    	addSequential(new RaiseGearArm());
    	
    	addSequential(new LowerBallIntakeArm());
    	addParallel(new DriveShooterPIDSpeed(6700));
    	addSequential(new WaitForShooterPIDToFinish());
    	addParallel(new DriveElevatorWithConstant(-1.0));
    	addParallel(new DriveIndexerWithConstant(1.0));
    	addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(1.0));
    }
}
