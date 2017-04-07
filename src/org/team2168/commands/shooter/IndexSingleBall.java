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
 * Bring a single ball from the hopper/indexer into the shooter.
 */
public class IndexSingleBall extends CommandGroup {

    public IndexSingleBall() {
    	//queue up a ball
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
    	addParallel(new DriveAgitatorWithConstant(RobotMap.AGITATOR_SPEED_CONSTANT));
    	addSequential(new RunElevatorUntilBallPresent(RobotMap.ELEVATOR_SPEED_CONSTANT));
    	
    	//Stop moving for uno momento
    	addParallel(new FuelMotorsStop(), 0.01);
    	addSequential(new Sleep(), 0.1); //Let things settle
    	
    	//fire the ball
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
    	//addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(RobotMap.AGITATOR_SPEED_CONSTANT));
    	addSequential(new RunElevatorUntilBallNotPresent(RobotMap.ELEVATOR_SPEED_CONSTANT));
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT), 0.1); //keep running the indexer a little so the ball clears out of shooter
    	
    	//queue up next a ball
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
    	addParallel(new DriveAgitatorWithConstant(RobotMap.AGITATOR_SPEED_CONSTANT));
    	addSequential(new RunElevatorUntilBallPresent(RobotMap.ELEVATOR_SPEED_CONSTANT));
    	
    	//Leave motors stopped
    	addSequential(new FuelMotorsStop(), 0.01);
    }
}
