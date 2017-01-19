package org.team2168.robot.subsystems;

import org.team2168.robot.RobotMap;
import org.team2168.robot.commands.DriveLiftWithJoystick;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Victor liftMotor1;
	private Victor liftMotor2;
	
	
	static Lift instance = null;

	
	public Lift(){
		
		liftMotor1 = new Victor(RobotMap.LIFT_MOTOR_1);
		liftMotor2 = new Victor(RobotMap.LIFT_MOTOR_2);
	}
	
	public static Lift getInstance(){
		
		if(instance == null)
			instance = new Lift();
		
		return instance;
	}
	
	public void drive(double speed){
		
		if(RobotMap.REVERSE_LIFT){
			speed = -speed;
			
		}
		
		liftMotor1.set(speed);
		liftMotor2.set(speed);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveLiftWithJoystick());
    }
}

