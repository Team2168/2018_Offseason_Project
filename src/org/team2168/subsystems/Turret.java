package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for Turret
 * @author Wen Baid
 */
public class Turret extends Subsystem {

    private static Spark TurretMotor;
    private static AnalogInput Potentiometer;
    private static DigitalInput LimitSwitch1;
    private static DigitalInput LimitSwitch2;

    private static Turret instance = null;
    
    private Turret() {
    	TurretMotor = new Spark(RobotMap.TURRET_MOTOR);
    	Potentiometer = new AnalogInput(RobotMap.TURRET_POTENTIOMETER);
    	LimitSwitch1 = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_1);
    	LimitSwitch2 = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_2);
    }
    
	public Turret getInstance() {
		if(instance == null)
			instance = new Turret();
		
		return instance;
	}
	
	public void setSpeed(double speed) {
		TurretMotor.set(speed);
	}
	
	public double getPosition() {
		return Potentiometer.getAverageVoltage();
	}
	
	public boolean isLimitSwitch1Active() {
		return LimitSwitch1.get();
	}
	
	public boolean isLimitSwitch2Active() {
		return LimitSwitch2.get();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

