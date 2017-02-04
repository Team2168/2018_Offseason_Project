package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntakeArm extends Subsystem {
	
	private DoubleSolenoid GearIntakeArmPiston;
	private DigitalInput GearIntakeHallEffectSensor;
	
	private static GearIntakeArm instance = null;
	
	
	private GearIntakeArm() {
		GearIntakeArmPiston = new DoubleSolenoid(RobotMap.GEAR_INTAKE_PISTON_EXTEND, RobotMap.GEAR_INTAKE_PISTON_RETRACT);
		GearIntakeHallEffectSensor = new DigitalInput(RobotMap.GEAR_INTAKE_HALL_EFECT);
	}
	
	public static GearIntakeArm getInstance(){
		if(instance == null)
			instance = new GearIntakeArm();
		
		return instance;
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

