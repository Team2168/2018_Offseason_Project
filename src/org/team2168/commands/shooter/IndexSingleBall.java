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
    	//addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(RobotMap.AGITATOR_SPEED_CONSTANT));
    	addSequential(new RunElevatorUntilBallPresent(RobotMap.ELEVATOR_SPEED_CONSTANT)); //XXX: need to drop this speed down a little.
    	
    	//Stop moving for uno momento
    	addParallel(new FuelMotorsStop());
    	addSequential(new Sleep(), 0.2); //Let things settle
    	
    	//fire the ball
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
    	//addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(RobotMap.AGITATOR_SPEED_CONSTANT));
    	addSequential(new RunElevatorUntilBallNotPresent(RobotMap.ELEVATOR_SPEED_CONSTANT));
    	
    	//queue up next a ball
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
    	//addParallel(new DriveIntakeWithConstant(1.0));
    	addParallel(new DriveAgitatorWithConstant(RobotMap.AGITATOR_SPEED_CONSTANT));
    	addSequential(new RunElevatorUntilBallPresent(RobotMap.ELEVATOR_SPEED_CONSTANT));
    	
    	//Leave motors stopped
    	addParallel(new FuelMotorsStop(), 0.01);
    }
}
