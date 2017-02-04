package org.team2168.subsystems;
 
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Subsystem for the Gear Intake
 *@author Elijah Reeds
 */
public class GearIntakeRoller extends Subsystem {
	
	private static Spark GearIntakeMotor;
	private static AnalogInput GearIntakeMotorIRSensor;
	
	private static GearIntakeRoller instance = null;
	
	private GearIntakeRoller() {
		GearIntakeMotor  = new Spark(RobotMap.GEAR_INTAKE_MOTOR);
		GearIntakeMotorIRSensor = new AnalogInput(RobotMap.GEAR_INTAKE_ROLLER_IR);
	}
	
	public static GearIntakeRoller getInstance(){
		if(instance == null)
			instance = new GearIntakeRoller();
		
		return instance;
	}
	
	/**
	 * 
	 * @return the raw voltage from the gear presence sensor
	 */
	public double getIRVoltage(){
		return GearIntakeMotorIRSensor.getVoltage();
	}
	
	/**
	 * 
	 * @return true if a gear is present within the gear intake
	 */
	public boolean isGearPresent(){
		return (GearIntakeMotorIRSensor.getVoltage() >= RobotMap.GEAR_INTAKE_IR_THRESHOLD);
	}
	
	public void setMotorSpeed(double speed){
		GearIntakeMotor.set(speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

