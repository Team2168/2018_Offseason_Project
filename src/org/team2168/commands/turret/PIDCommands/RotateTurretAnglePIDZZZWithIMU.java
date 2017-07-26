
package org.team2168.commands.turret.PIDCommands;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Vittorio
 */
public class RotateTurretAnglePIDZZZWithIMU extends Command {

	private double setPoint;
	private double maxSpeed;
	private double minSpeed;
	private double error = 0.5;  // Rotational degree error, default 0 never ends.
	private boolean absolute = false;
	
    public RotateTurretAnglePIDZZZWithIMU() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.turret);
    	this.setPoint = Robot.turret.rotateTurretPOTController.getSetPoint();
    	this.maxSpeed = 1;
    	this.minSpeed = 0;
    }

    public RotateTurretAnglePIDZZZWithIMU(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
    }

    public RotateTurretAnglePIDZZZWithIMU(double setPoint, double maxSpeed){
  	   this(setPoint);
  	   this.maxSpeed = maxSpeed;
     }
    
    public RotateTurretAnglePIDZZZWithIMU(double setPoint, double maxSpeed, double minSpeed){
   	   this(setPoint, maxSpeed);
   	   this.minSpeed = minSpeed;
      }    

    public RotateTurretAnglePIDZZZWithIMU(double setPoint, double maxSpeed, double minSpeed, double error) {
    	this(setPoint, maxSpeed, minSpeed);
    	this.error = error;
    	this.absolute = false;
    }
    
    public RotateTurretAnglePIDZZZWithIMU(double setPoint, double maxSpeed, double minSpeed, double error, boolean absolute) {
    	this(setPoint, maxSpeed, minSpeed, error);
    	this.absolute = absolute;
    }
    // Called just before this Command runs the first time
    
	protected void initialize() {
		double sp = 0;
		if (!absolute)
			sp = this.setPoint + Robot.turret.getPosition();
		else
			sp = this.setPoint;
		Robot.turret.rotateTurretPOTController.reset();

//		Robot.turret.rotateTurretPOTController.setpGain(RobotMap.ROTATE_POSITION_P);
//		Robot.turret.rotateTurretPOTController.setiGain(RobotMap.ROTATE_POSITION_I);
//		Robot.turret.rotateTurretPOTController.setdGain(RobotMap.ROTATE_POSITION_D);
		Robot.turret.rotateTurretPOTController.setSetPoint(sp);
		Robot.turret.rotateTurretPOTController.setMaxPosOutput(maxSpeed);
		Robot.turret.rotateTurretPOTController.setMaxNegOutput(-maxSpeed);
		Robot.turret.rotateTurretPOTController.setMinPosOutput(minSpeed);
		Robot.turret.rotateTurretPOTController.setMinNegOutput(-minSpeed);
		Robot.turret.rotateTurretPOTController.setAcceptErrorDiff(error);
		//Robot.drivetrain.gyroSPI.reset();
		Robot.turret.rotateTurretPOTController.Enable();
		
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		
		Robot.turret.rotateTurretPOTController.setSetPoint(Robot.dtIMU.getTurretAngleToBoiler());
		Robot.turret.setSpeed(Robot.turret.rotateTurretPOTController.getControlOutput());
		
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.turret.rotateTurretPOTController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}