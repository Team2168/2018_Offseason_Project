package org.team2168.subsystems;

import org.team2168.commands.shooterHood.DriveHoodWithJoystick;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The 2017 turret shooter hood subsystem
 * @author Peter Dentch
 */
public class ShooterHood extends Subsystem {
    
    private Servo hoodServo;
    
    private static ShooterHood instance = null;
    
    /**
     * Default constructor for the subsystem 
     */
    private ShooterHood(){
    	hoodServo = new Servo(RobotMap.SHOOTER_HOOD_SERVO);    // define these when
    	hoodServo.setBounds(0, 0, 0, 0, 0);                    // implementing the hardware
    	
    }
	
	/**
	 * Returns the shooter hood singleton object
	 * @return is the current shooter hood object
	 */
    public static ShooterHood getInstance(){
    	if(instance == null)
    		instance = new ShooterHood();
    	
    	return instance;
    }
	
    /**
     * Takes a given angle and rotates the servo motor to that angle
     * @param degrees the angle limited by the min and max values defined in RobotMap
     */
	public void setAngle(double degrees){
		
		if(degrees <= RobotMap.MIN_HOOD_VALUE)
			degrees = RobotMap.MIN_HOOD_VALUE;
		
		if(degrees >= RobotMap.MAX_HOOD_VALUE)
			degrees = RobotMap.MAX_HOOD_VALUE;
		
		hoodServo.setAngle(degrees);
	}
	
	/**
	 * Returns the last angle the motor was set to, note that this can be
	 * different from the actual positional angle of the motor at a given moment.
	 * This method shouldn't be used to retrieve the angle value of the servo motor
	 * like that which would be given by an encoder or other sensor
	 * @return the angle in degrees that servo motor was last commanded to
	 */
	public double getAngle(){
		return hoodServo.getAngle();
	}
	
	/**
	 * Sets the default command of the subsystem
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveHoodWithJoystick());
    }
}

