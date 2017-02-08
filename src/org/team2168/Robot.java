
package org.team2168;

import org.team2168.subsystems.Climber;
import org.team2168.subsystems.Conveyor;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.GearIntakeArm;
import org.team2168.subsystems.GearIntakeRoller;
import org.team2168.utils.PowerDistribution;
import org.team2168.subsystems.Turret;
import org.team2168.subsystems.ShooterIndexer;
import org.team2168.subsystems.BallElevator;
import org.team2168.subsystems.BallIntake;
import org.team2168.subsystems.ShooterHood;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static Climber climber;
	public static BallIntake ballIntake;
	public static Turret turret;
	public static ShooterIndexer shooterIndexer;
	public static BallElevator ballElevator;
	public static GearIntakeArm gearIntakeArm;
	public static GearIntakeRoller gearIntakeRoller;
	public static ShooterHood shooterhood;
	public static Conveyor conveyor;
	
	
	public static PowerDistribution pdp;

	public static OI oi;
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	ConsolePrinter.init();
    	ConsolePrinter.setRate(RobotMap.CONSOLE_PRINTER_LOG_RATE_MS);

    	// instantiate the commands used for the autonomous period
    	turret = Turret.getInstance();
        drivetrain = Drivetrain.getInstance();
        shooterIndexer = ShooterIndexer.getInstance();	
        ballIntake = BallIntake.getInstance();
		climber = Climber.getInstance();
        gearIntakeArm = GearIntakeArm.getInstance();
        gearIntakeRoller = GearIntakeRoller.getInstance();
        shooterhood = ShooterHood.getInstance();
        conveyor = Conveyor.getInstance();
        // instantiate the command used for the autonomous period


        oi = OI.getInstance();
		ConsolePrinter.startThread();
		
		pdp = new PowerDistribution(RobotMap.PDPThreadPeriod);
		pdp.startThread();
		
		System.out.println("Robot Finished Loading");
    }
	
	public void disabledPeriodic() {
		// Kill all active commands
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
