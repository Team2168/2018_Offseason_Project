package org.team2168.commands.shooter;

import org.team2168.RobotMap;
import org.team2168.commands.agitator.DriveAgitatorWithConstant;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.ballIntake.DriveIntakeWithConstant;
import org.team2168.commands.elevator.DriveElevatorWithConstant;
import org.team2168.commands.elevator.RunElevatorUntilBallNotPresent;
import org.team2168.commands.elevator.RunElevatorUntilBallPresent;
import org.team2168.commands.indexer.DriveIndexerWithConstant;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive all the motors that move the fule into the shooter..
 */
public class FuelMotorsDrive extends CommandGroup {

    public FuelMotorsDrive() {
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
    	//addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(RobotMap.AGITATOR_SPEED_CONSTANT));
    	addSequential(new RunElevatorUntilBallPresent(RobotMap.INDEXER_SPEED_CONSTANT));
    }
}
