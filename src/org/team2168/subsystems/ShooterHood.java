package org.team2168.subsystems;

import org.team2168.commands.shooterHood.DriveHoodWithJoystick;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The 2017 turret shooter hood subsystem
 * @author Peter Dentch
 */
public class ShooterHood extends Subsystem {
    
    private static Servo hoodServo;
    
    private static ShooterHood instance = null;
    
    private static double startAngle;
    private static double endAngle;
    private static double currentAngle;
    private static double startTime;
    private static double currentTime;
    private static final double DEGREES_PER_SECOND = 90;
    
    //SERVO Parameters from https://s3.amazonaws.com/actuonix/Actuonix+L16+Datasheet.pdf
    private static final double MAX_SERVO_PWM = 2.0; //ms
    private static final double MIN_SERVO_PWM = 1.0; //ms
    private static final double CENTER_SERVO_PWM = 1.5; //ms
    private static final double SERVO_DEADBAND = 0.0; //ms - no deadband
    
    /**
     * Default constructor for the subsystem 
     */
    private ShooterHood(){
    	hoodServo = new Servo(RobotMap.SHOOTER_HOOD_SERVO);
    	hoodServo.setBounds(MAX_SERVO_PWM, CENTER_SERVO_PWM + SERVO_DEADBAND, 
    			CENTER_SERVO_PWM, CENTER_SERVO_PWM - SERVO_DEADBAND, MIN_SERVO_PWM);
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
		
		startAngle = hoodServo.getAngle();
		startTime = Timer.getFPGATimestamp();
		
		hoodServo.setAngle(degrees);
	}
	
	/**
	 * Returns the current angle of the servo by taking the angle it was last set to
	 * with the time before the movement begins and after that is called the current
	 * time and angle the servo is moving to is taken and the current angle is estimated
	 * @return the estimated current angle of the servo in degrees
	 */
	public double getAngle(){
		
		endAngle = hoodServo.getAngle();
		double angleDifference = endAngle - startAngle;
		double timeDifference = Timer.getFPGATimestamp() - startTime;
		
		if(angleDifference > 0)
			currentAngle = (startAngle + (timeDifference * DEGREES_PER_SECOND));
		
		else if(angleDifference < 0)
			currentAngle = (startAngle - (timeDifference * DEGREES_PER_SECOND));
		
		else if(angleDifference == 0)
			currentAngle = endAngle;
		
		return currentAngle;
		
	}
	
	/**
	 * Sets the default command of the subsystem
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveHoodWithJoystick());
    }
}

