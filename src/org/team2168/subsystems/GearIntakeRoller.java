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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Spark GearIntakeMotor;
	
	private AnalogInput GearIntakeMotorIRSensor;
	
	private static GearIntakeRoller instance = null;
	
	
	private GearIntakeRoller() {
		GearIntakeMotor  = new Spark(RobotMap.GEAR_INTAKE_MOTOR);
		GearIntakeMotorIRSensor = new AnalogInput(RobotMap.GEAR_INTAKE_ROLLER_IR);
	}
	
	public GearIntakeRoller getInstance(){
		if(instance == null)
			instance = new GearIntakeRoller();
		
		return instance;
	}
	
	public double getIRVoltage(){
		return GearIntakeMotorIRSensor.getVoltage();
	}
	
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

