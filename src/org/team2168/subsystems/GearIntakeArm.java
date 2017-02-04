package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Subsystem code for the Gear Intake Arm Subsystem.
 *@author Elijah Reeds
 */
public class GearIntakeArm extends Subsystem {
	
	private DoubleSolenoid GearIntakeArmPiston;
	private DigitalInput GearIntakeHallEffectSensor;
	
	private static GearIntakeArm instance = null;
	
	/**
	 * WARNING! CONSTRUCTION AHEAD!
	 * @author Elijah Reeds
	 */
	private GearIntakeArm() {
		GearIntakeArmPiston = new DoubleSolenoid(RobotMap.GEAR_INTAKE_PISTON_EXTEND, RobotMap.GEAR_INTAKE_PISTON_RETRACT);
		GearIntakeHallEffectSensor = new DigitalInput(RobotMap.GEAR_INTAKE_HALL_EFECT);
	}
	
	/**
	 * Gets the instance of the GearIntakeArm.
	 * @return Returns the current instance of the Gear Intake Arm subsystem.
	 * @author Elijah Reeds
	 */
	public static GearIntakeArm getInstance(){
		if(instance == null)
			instance = new GearIntakeArm();
		
		return instance;
	}
	
	/**
	 * Extends the arm of the Gear Intake Subsystem.
	 * @author Elijah Reeds
	 */
	public void ExtendArm(){
		GearIntakeArmPiston.set(Value.kForward);
	}
	
	/**
	 * Retracts the arm of the Gear Intake Subsystem.
	 * @author Elijah Reeds
	 */
	public void RetractArm(){
		GearIntakeArmPiston.set(Value.kReverse);
	}
	
	/**
	 * Gets the whether the arm is extended or not.
	 * @return Returns true if the arm of the Gear Intake is extended based on the Hall Effect Sensor.
	 * @author Elijah Reeds
	 */
	public boolean isArmExtended(){
		return GearIntakeHallEffectSensor.get();
	}
	
	/**
	 * Gets the whether the arm is retracted or not.
	 * @return Returns true if the arm of the Gear Intake is retracted based on the Hall Effect Sensor.
	 * @author Elijah Reeds
	 */
	public boolean isArmRetracted(){
		return !GearIntakeHallEffectSensor.get();
	}
	
	/**
	 * Gets whether the DoubleSolenoid is set to Value.kForward or not.
	 * @return Returns true if the DoubleSolenoid is set to Value.kForward.
	 * @author Elijah Reeds
	 */
	public boolean isSolenoidExtended(){
		return (GearIntakeArmPiston.get() == Value.kForward);
	}
	
	/**
	 * Gets whether the DoubleSolenoid is set to Value.kReverse or not.
	 * @return Returns true if the DoubleSolenoid is set to Value.kReverse.
	 * @author Elijah Reeds
	 */
	public boolean isSolenoidRetracted(){
		return (GearIntakeArmPiston.get() == Value.kReverse);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

