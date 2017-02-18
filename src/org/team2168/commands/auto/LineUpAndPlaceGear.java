package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.gearintake.LowerGearArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LineUpAndPlaceGear extends CommandGroup {

    public LineUpAndPlaceGear() {
    	addSequential(new DriveXDistance(7,0.7),4);
        //TODO Command to line up with vision target
    	addSequential(new DriveXDistance(1,0.7),2);
    	addSequential(new LowerGearArm(), 1);
    	addSequential(new DriveXDistance(-2,0.7),2);
    }
}
