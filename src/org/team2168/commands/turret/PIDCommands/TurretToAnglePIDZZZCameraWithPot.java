
package org.team2168.commands.turret.PIDCommands;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Wen Baid
 */
public class TurretToAnglePIDZZZCameraWithPot extends Command {

	private double setPoint;
	private double maxSpeed;
	private double minSpeed;
	private double error = 0.5;  // Rotational degree error, default 0 never ends.
	private boolean absolute = false;
	
    public TurretToAnglePIDZZZCameraWithPot() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.turret);
    	this.setPoint = Robot.turret.rotateTurretCameraController.getSetPoint();
    	this.maxSpeed = 1;
    	this.minSpeed = 0;
    }

    public TurretToAnglePIDZZZCameraWithPot(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
    }

    public TurretToAnglePIDZZZCameraWithPot(double setPoint, double maxSpeed){
  	   this(setPoint);
  	   this.maxSpeed = maxSpeed;
     }
    
    public TurretToAnglePIDZZZCameraWithPot(double setPoint, double maxSpeed, double minSpeed){
   	   this(setPoint, maxSpeed);
   	   this.minSpeed = minSpeed;
      }    

    public TurretToAnglePIDZZZCameraWithPot(double setPoint, double maxSpeed, double minSpeed, double error) {
    	this(setPoint, maxSpeed, minSpeed);
    	this.error = error;
    }
    
    public TurretToAnglePIDZZZCameraWithPot(double setPoint, double maxSpeed, double minSpeed, double error, boolean absolute) {
    	this(setPoint, maxSpeed, minSpeed, error);
    	this.absolute = absolute;
    }
    // Called just before this Command runs the first time
    
	protected void initialize() {
		if (!absolute)
			this.setPoint = Robot.drivetrain.getHeading() - Robot.drivetrain.tcpCamSensor.getRotationAngle() + RobotMap.CAMERA_OFFSET_ANGLE;
		Robot.turret.rotateTurretCameraController.reset();
		Robot.turret.rotateTurretCameraController.setSetPoint(setPoint);
		Robot.turret.rotateTurretCameraController.setMaxPosOutput(maxSpeed);
		Robot.turret.rotateTurretCameraController.setMaxNegOutput(-maxSpeed);
		Robot.turret.rotateTurretCameraController.setMinPosOutput(minSpeed);
		Robot.turret.rotateTurretCameraController.setMinNegOutput(-minSpeed);
		//Robot.drivetrain.rotateController.setAcceptErrorDiff(error);
		//Robot.drivetrain.gyroSPI.reset();
		Robot.turret.rotateTurretCameraController.Enable();
		
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		
		//keep robot moving until we are at center
		Robot.turret.rotateTurretCameraController.setSetPoint(Robot.turret.getPosition() - Robot.turret.tcpCamSensor.getRotationAngle());
		Robot.turret.setSpeed(Robot.turret.rotateTurretCameraController.getControlOutput());
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
		//TODO Should the command be stopped????????!?!?!?!?!? after PID is tuned
//    	if( Robot.drivetrain.rotateController.isFinished())
//    	{
//    		setPoint = Robot.drivetrain.getHeading() - Robot.tcpCamSensor.getRotationAngle();
//    		Robot.drivetrain.rotateController.setSetPoint(setPoint);
//    	}
//    	
//		
		
//		return Robot.drivetrain.rotateController.isFinished() || Math.abs(Robot.drivetrain.tcpCamSensor.getPos()) < error;
		return Math.abs(Robot.turret.tcpCamSensor.getPos()) < error;
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.turret.rotateTurretCameraController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}