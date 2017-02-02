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
    private static DigitalInput LimitSwitchRight;
    private static DigitalInput LimitSwitchLeft;

    private static Turret instance = null;
    
    private static LinearInterpolator turretInterpolator;
    //TODO get these values plez
    private double[][] turretRange = {{-1.0,-100.0},
    		                          {0.0,0.0},
    		                          {1.0,100.0}};
    
    /**
     * Default constructor for Turret subsystem
     */
    private Turret() {
    	TurretMotor = new Spark(RobotMap.TURRET_MOTOR);
    	Potentiometer = new AnalogInput(RobotMap.TURRET_POTENTIOMETER);
    	LimitSwitchRight = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_1);
    	LimitSwitchLeft = new DigitalInput(RobotMap.TURRET_LIMIT_SWITCH_2);
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
	 * @param speed of -1.0 (left) to 1.0 (right)
	 */
	public void setSpeed(double speed) {
		if((speed > 0 && isLimitSwitchRightActive())||(speed < 0 && isLimitSwitchLeftActive())){
			TurretMotor.set(0);
		}
		else {
			TurretMotor.set(speed);
		}
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
	public boolean isLimitSwitchRightActive() {
		return LimitSwitchRight.get();
	}
	
	/**
	 * Returns status of limit switch
	 * @return true if pressed, false if unpressed
	 */
	public boolean isLimitSwitchLeftActive() {
		return LimitSwitchLeft.get();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveTurretWithJoystick());
    }
}

