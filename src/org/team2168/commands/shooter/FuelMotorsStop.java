package org.team2168.commands.shooter;

import org.team2168.RobotMap;
import org.team2168.commands.agitator.DriveAgitatorWithConstant;
import org.team2168.commands.elevator.DriveElevatorWithConstant;
import org.team2168.commands.elevator.RunElevatorUntilBallNotPresent;
import org.team2168.commands.indexer.DriveIndexerWithConstant;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Stop all the motors used to bring balls into the shooter
 */
public class FuelMotorsStop extends CommandGroup {

    public FuelMotorsStop() {
    	//Stop the ball
    	addParallel(new DriveIndexerWithConstant(0.0));
    	//addParallel(new DriveIntakeWithConstant(0.0));
    	addParallel(new DriveAgitatorWithConstant(0.0));
    	addParallel(new DriveElevatorWithConstant(0.0));
    }
}
