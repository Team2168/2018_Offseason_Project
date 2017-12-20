package org.team2168;

import org.team2168.commands.drivetrain.PIDCommands.DriveSraightXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.PivotArm.PivotArmWithConstant;
import org.team2168.commands.TelescopicArm.OperateTelescopicArmWithConstant;
import org.team2168.commands.drivetrain.DriveWithJoystickEmergencyJoystick;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.ShiftLow;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.utils.F310;
import org.team2168.utils.consoleprinter.ConsolePrinter;


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
	
	public static F310 driverOperatorEJoystick = new F310(RobotMap.DRIVER_OPERATOR_E_BACKUP);
	
	public static F310 testJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);
	public static F310 pidTestJoystick = new F310(RobotMap.PID_TEST_JOYSTICK);

	/**
	 * Private constructor for singleton class which instantiates the OI object
	 */
	private OI() {
		
		
		//////////////Driver Joystick//////////////
		driverJoystick.ButtonStart().whenPressed(new ShiftLow());
		driverJoystick.ButtonA().whenPressed(new ShiftHigh());
		
		//////////////Operator Joystick//////////////

		//Telescopic
		operatorJoystick.ButtonDownDPad().whileHeld(new OperateTelescopicArmWithConstant(-1));
		operatorJoystick.ButtonUpDPad().whileHeld(new OperateTelescopicArmWithConstant(1));
		operatorJoystick.ButtonRightDPad().whileHeld(new PivotArmWithConstant(1));
		operatorJoystick.ButtonLeftDPad().whileHeld(new PivotArmWithConstant(-1));
		


	
	
		
		ConsolePrinter.putNumber("Left Stick Raw Value", () -> {return Robot.oi.driverJoystick.getLeftStickRaw_Y();}, true, false);
		ConsolePrinter.putNumber("Right Stick Raw Value", () -> {return Robot.oi.driverJoystick.getRightStickRaw_Y();}, true, false);

		ConsolePrinter.putNumber("Left Trigger Raw Value", () -> {return Robot.oi.driverJoystick.getLeftTriggerAxisRaw();}, true, false);
		ConsolePrinter.putNumber("Right Trigger Raw Value", () -> {return Robot.oi.driverJoystick.getRightTriggerAxisRaw();}, true, false);

		ConsolePrinter.putNumber("Operator Left Stick Raw Value", () -> {return Robot.oi.operatorJoystick.getLeftStickRaw_Y();}, true, false);
		ConsolePrinter.putNumber("Operator Right Stick Raw Value", () -> {return Robot.oi.operatorJoystick.getRightStickRaw_Y();}, true, false);

		ConsolePrinter.putNumber("Operator Left Trigger Raw Value", () -> {return Robot.oi.operatorJoystick.getLeftTriggerAxisRaw();}, true, false);
		ConsolePrinter.putNumber("Operator Right Trigger Raw Value", () -> {return Robot.oi.operatorJoystick.getRightTriggerAxisRaw();}, true, false);

		
	
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
		return operatorJoystick.getLeftStickRaw_Y();
	}
	
	public static double getDriveGearIntakeRollerJoystick() {
		return operatorJoystick.getLeftStickRaw_Y();
	}
	
	public static double getDriveConveryorJoystick() {
		return operatorJoystick.getLeftStickRaw_Y();
	}
	
	public static double getDriveBallIntakeJoystick() {
		return operatorJoystick.getLeftStickRaw_Y();
	}
	
	public static double getDriveShooterIndexerJoystick() {
		return operatorJoystick.getLeftStickRaw_Y();
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
	
	public static double getDriveTurretJoystick(){
		return pidTestJoystick.getLeftStickRaw_Y();
	}
	
	public static double  getDriveAgitatorJoystick(){
		return 0;
	}
}

