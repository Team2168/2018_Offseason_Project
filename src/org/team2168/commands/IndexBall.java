package org.team2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Takes a single ball into the shooter indexer
 * @author Wen Baid
 */
public class IndexBall extends CommandGroup {

    public IndexBall() {
    	//TODO determine speed needed
        addSequential(new RunIndexerUntilBallPresent(0.5));
        addSequential(new DriveShooterIndexerWithConstant(0.0));
    }
}
