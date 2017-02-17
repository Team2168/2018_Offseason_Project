package org.team2168;

import org.team2168.commands.ballIntake.DriveIntakeWithConstant;
import org.team2168.commands.climber.DriveClimberWithConstant;
import org.team2168.commands.conveyor.DriveConveyorWithConstant;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.elevator.DriveElevatorWithConstant;
import org.team2168.commands.gearintake.DriveGearIntakeRollerWithConstant;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.shooter.DriveShooterWithConstant;
import org.team2168.commands.shooter.SetHoodToAngle;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.turret.DriveTurretWithConstant;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	private static OI instance = null;
	
	public static F310 driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
	public static F310 operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
	
	public static F310 testJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);
	public static F310 pidTestJoystick = new F310(RobotMap.PID_TEST_JOYSTICK);

	/**
	 * Private constructor for singleton class which instantiates the OI object
	 */
	private OI() {
		
		//////////////Operator Joystick//////////////
		operatorJoystick.ButtonA().whenPressed(new SetHoodToAngle(0));
		operatorJoystick.ButtonB().whenPressed(new SetHoodToAngle(180));
		operatorJoystick.ButtonX().whileHeld(new DriveShooterWithConstant(.5));
		
		//////////////Test Joystick//////////////
		testJoystick.ButtonA().whenPressed(new DriveIntakeWithConstant(0.3));
		testJoystick.ButtonB().whenPressed(new DriveIntakeWithConstant(-0.3));
		testJoystick.ButtonX().whenPressed(new DriveClimberWithConstant(0.3));
		testJoystick.ButtonY().whenPressed(new DriveClimberWithConstant(-0.3));
		testJoystick.ButtonUpDPad().whenPressed(new DriveConveyorWithConstant(0.3));
		testJoystick.ButtonDownDPad().whenPressed(new DriveConveyorWithConstant(-0.3));
		testJoystick.ButtonLeftDPad().whenPressed(new DriveElevatorWithConstant(0.3));
		testJoystick.ButtonRightDPad().whenPressed(new DriveElevatorWithConstant(-0.3));
		testJoystick.ButtonRightBumper().whenPressed(new DriveGearIntakeRollerWithConstant(0.3));
		testJoystick.ButtonLeftBumper().whenPressed(new DriveGearIntakeRollerWithConstant(-0.3));
		testJoystick.ButtonStart().whenPressed(new DriveIndexerWithConstant(0.3));
		testJoystick.ButtonBack().whenPressed(new DriveIndexerWithConstant(-0.3));
		testJoystick.ButtonLeftBumper().whenPressed(new DriveShooterWithConstant(0.3));
		testJoystick.ButtonRightBumper().whenPressed(new DriveShooterWithConstant(-0.3));
		testJoystick.ButtonLeftStick().whenPressed(new DriveTurretWithConstant(0.3));
		testJoystick.ButtonRightStick().whenPressed(new DriveTurretWithConstant(-0.3));
		
		//PIDJoystick
		//Shoot Far Preset (Y)
		pidTestJoystick.ButtonY().whenPressed(new DriveShooterPIDSpeed(6700));

		//Shoot Close Preset (X)
		pidTestJoystick.ButtonX().whenPressed(new DriveShooterPIDSpeed(4000));
		
		pidTestJoystick.ButtonA().whenPressed(new DriveShooterPIDSpeed());
		//Camera Shot Align (Start Button)
		//operatorJoystick.ButtonStart().whenPressed(new RotateXDistancePIDZZZCameraWithGyro(0, 0.4, 0.22, 0.5));
		pidTestJoystick.ButtonStart().whenPressed(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
		
	}

	/**
	 * Returns an instance of the Operator Interface.
	 * @return is the current OI object
	 */
	public static OI getInstance(){
		if(instance == null)
			instance = new OI();

		return instance;
	}

	/**
	 *  Method that sets that Left side of the drive train so that it drives with LeftStick Y
	 *  @author Krystina
	 */
	public static double getDriveTrainLeftJoystick () {
		return driverJoystick.getLeftStickRaw_Y();
	}

	/**
	 *  Method that sets that Right side of the drive train so that it drives with RightStick Y
	 *  @author Krystina
	 */
	public static double getDriveTrainRightJoystick () {
		return driverJoystick.getRightStickRaw_Y();
	}
	
	/**
	 *  Method that sets that Left side of the drive train so that it drives with Operator RightStick Y
	 *  @author Krystina
	 */
	public static double getDriveShooterJoystick() {
		return operatorJoystick.getRightStickRaw_Y();
	}
	
	public static double getDriveElevatorJoystick() {
		return operatorJoystick.getRightStickRaw_Y();
	}
	
	public static double getDriveGearIntakeRollerJoystick() {
		return operatorJoystick.getLeftStickRaw_Y();
	}
	
	public static double getDriveConveryorJoystick() {
		return operatorJoystick.getRightStickRaw_Y();
	}
	
	public static double getDriveBallIntakeJoystick() {
		return operatorJoystick.getLeftStickRaw_Y();
	}
	
	public static double getDriveShooterIndexerJoystick() {
		return operatorJoystick.getRightStickRaw_Y();
	}
	
	public static double getDriveShooterPIDTestJoystick(){
		return pidTestJoystick.getRightStickRaw_Y();
	}
	
	public static double getDriveElevatorPIDTestJoystick(){
		return pidTestJoystick.getLeftStickRaw_Y();
	}
		
	public static double getDriveIndexerPIDTestJoystick(){
			return (pidTestJoystick.getRightTriggerAxisRaw() + pidTestJoystick.getLeftTriggerAxisRaw());	
	}
}

