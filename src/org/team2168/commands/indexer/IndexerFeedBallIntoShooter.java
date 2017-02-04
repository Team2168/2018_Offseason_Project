package org.team2168.commands.indexer;

import org.team2168.RobotMap;
import org.team2168.commands.DriveShooterIndexerWithConstant;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Takes one ball from elevator and feeds it through the shooter indexer into the shooter
 * @author Wen Baid
 */
public class IndexerFeedBallIntoShooter extends CommandGroup {

    public IndexerFeedBallIntoShooter() {
        addSequential(new RunIndexerUntilBallPresent(RobotMap.INDEXER_SPEED_CONSTANT));
        addSequential(new RunIndexerUntilBallNotPresent(RobotMap.INDEXER_SPEED_CONSTANT));
        addSequential(new DriveShooterIndexerWithConstant(0.0));
    }
}
