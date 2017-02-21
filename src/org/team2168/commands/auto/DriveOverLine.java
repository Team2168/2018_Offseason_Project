package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives straight out of launch pad
 */
public class DriveOverLine extends CommandGroup {

    public DriveOverLine() {
        addSequential(new DriveXDistance(10,0.7),10);
    }
}
