package org.team2168;

import org.team2168.commands.ballIntake.DriveIntakeWithConstant;
import org.team2168.commands.climber.DriveClimberWithConstant;
import org.team2168.commands.conveyor.DriveConveyorWithConstant;
import org.team2168.commands.elevator.DriveElevatorWithConstant;
import org.team2168.commands.gearintake.DriveGearIntakeRollerWithConstant;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.shooter.DriveShooterWithConstant;
import org.team2168.commands.shooter.SetHoodToAngle;
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
	
	public static F310 driverJoystick = new F310(0);
	public static F310 operatorJoystick = new F310(1);
	public static F310 testJoystick = new F310(4);

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
}

