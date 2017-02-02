package org.team2168.subsystems;

import javax.swing.text.Utilities;

import org.team2168.RobotMap;
import org.team2168.commands.turret.DriveTurretWithJoystick;
import org.team2168.utils.LinearInterpolator;

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
    
    private static LinearInterpolator turretInterpolator;
    //TODO get these values plez
    private double[][] turretRange = {{0.0,0.0},
    		 			              {1.0,100.0}};
    
    /**
     * Default constructor for Turret subsystem
     */
    private Turret() {
    	TurretMotor = new Spark(RobotMap.TURRET_MOTOR);
    	Potentiometer = new AnalogInput(RobotMap.TURRET_POTENTIOMETER);
    	LimitSwitch1 = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_1);
    	LimitSwitch2 = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_2);
    	turretInterpolator = new LinearInterpolator(turretRange);
    }
    
    /**
     * Returns turret singleton object
     * @return turret singleton object
     */
	public Turret getInstance() {
		if(instance == null)
			instance = new Turret();
		
		return instance;
	}
	
	/**
	 * Sets the speed of the motor
	 * @param speed (0-1)
	 */
	public void setSpeed(double speed) {
		TurretMotor.set(speed);
	}
	
	/**
	 * Returns the current position of the turret
	 * @param x is voltage
	 * @return Potentiometer position
	 */
	public double getPosition(double x) {
		return turretInterpolator.interpolate(x);
	}
	
	/**
	 * Returns status of limit switch
	 * @return true if pressed, false if unpressed
	 */
	public boolean isLimitSwitch1Active() {
		return LimitSwitch1.get();
	}
	
	/**
	 * Returns status of limit switch
	 * @return true if pressed, false if unpressed
	 */
	public boolean isLimitSwitch2Active() {
		return LimitSwitch2.get();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveTurretWithJoystick());
    }
}

