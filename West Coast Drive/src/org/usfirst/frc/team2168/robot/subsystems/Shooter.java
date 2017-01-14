package org.usfirst.frc.team2168.robot.subsystems;

import org.usfirst.frc.team2168.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Shooter
 */
public class Shooter extends Subsystem {

	Victor ShooterMotor;
	
	Encoder ShooterEncoder;
	
	private static Shooter instance = null;
	
	public Shooter() {
		
		ShooterMotor = new Victor(RobotMap.SHOOTER_MOTOR);

		ShooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A, RobotMap.SHOOTER_ENCODER_B);
		
	}
	
	public static Shooter getInstance(){
		if(instance == null)
			instance = new Shooter();
		
		return instance;
	}
	
	public void driveShooterMotor(double speed) {
		ShooterMotor.set(speed);
	}
	
	public double getShooterEncoder() {
		return ShooterEncoder.get();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

