package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.agitator.DriveAgitatorWithConstant;
import org.team2168.commands.ballIntake.DriveIntakeWithConstant;
import org.team2168.commands.ballIntake.LowerBallIntakeArm;
import org.team2168.commands.ballIntake.ToggleBallIntakeArm;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.elevator.DriveElevatorWithConstant;
import org.team2168.commands.flashlight.EnableFlashlight;
import org.team2168.commands.gearintake.LowerGearArmDANGEROUS;
import org.team2168.commands.gearintake.RaiseGearArm;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.shooter.IndexSingleBall;
import org.team2168.commands.shooter.SetHoodToAngle;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.WaitForShooterPIDToFinish;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives straight out of launch pad
 */
public class DriveStraightAndScoreCenterShootingIndexed extends CommandGroup {

    public DriveStraightAndScoreCenterShootingIndexed() {
    	//Drive up and align
        addSequential(new DriveXDistance(8.3,0.7,0.15));
        addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 1.0),1);
        //Drive into peg and drop gear
    	addSequential(new DriveXDistance(0.7, 0.7,0.1),1.5);
    	//Jerk it (if necesssary)
    	addSequential(new DriveXDistance(0.8, 0.7,0.1),0.7);
    	addSequential(new LowerGearArmDANGEROUS(),0.5);
    	//Back off bruh
    	addSequential(new Sleep(), 0.6);
    	addSequential(new DriveXDistance(-3.0,0.7,0.1), 2); 
    	addSequential(new RaiseGearArm());
    	//Prepare for ripum gathering
    	addSequential(new EnableFlashlight());
    	addSequential(new LowerBallIntakeArm());
    	addParallel(new SetHoodToAngle(148.0),2);
    	addParallel(new DriveShooterPIDSpeed(3350));
    	//Gather the ripums
    	addSequential(new WaitForShooterPIDToFinish());
    	addSequential(new Sleep(), 3);
    	
    	
    	//Precipitate - there has to be a better way than calling this 10 times...
    	addParallel(new ToggleBallIntakeArm());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	addSequential(new IndexSingleBall());
    	
    	//Then just run it full out if there's time/balls left.
    	addParallel(new DriveElevatorWithConstant(1.0));
    	addParallel(new DriveIndexerWithConstant(1.0));
    	addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(0.65));
    }
}
