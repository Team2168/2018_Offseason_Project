package org.team2168.subsystems;
 
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Subsystem for the Gear Intake
 *@author Elijah Reeds
 */
public class GearIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	
	private static GearIntake instance = null;
	
	
	private GearIntake() {
		
	}
	
	public GearIntake getInstance(){
		if(instance == null)
			instance = new GearIntake();
		
		return instance;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

