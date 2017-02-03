package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Shooter Indexer
 * @author Wen Baid
 */
public class ShooterIndexer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Spark indexerMotor;
	private static DigitalInput sensor1;
	private static DigitalInput sensor2;
	
	private static ShooterIndexer instance = null;
	
	/**
	 * Default constructor for Shooter Indexer subsystem
	 */
	private ShooterIndexer() {
		indexerMotor = new Spark(RobotMap.INDEXER_WHEEL);
	}
	
	/**
	 * Singleton getter for ShooterIndexer
	 * @return ShooterIndexer singleton
	 */
	public ShooterIndexer getInstance(){
		if(instance == null)
			instance = new ShooterIndexer();
		
		return instance;
	}
	
	/**
	 * Sets the speed of the indexer motor
	 * @param speed
	 */
	public void setSpeed(double speed) {
		indexerMotor.set(speed);
	}
	
	/**
	 * Checks if sensor1 is activated
	 * @return if ball is present (true=present, false=not present)
	 */
	private boolean isSensor1Active() {
		return sensor1.get();
	}
	
	/**
	 * Checks if sensor2 is activated
	 * @return if ball is present (true=present, false=not present)
	 */
	private boolean isSensor2Active() {
		return sensor2.get();
	}
	
	/**
	 * Checks if ball is present
	 * @return if ball is present (true=present, false=not present)
	 */
	private boolean isBallPresent() {
		if(isSensor1Active() || isSensor2Active()) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if ball is absent
	 * @return if ball is absent (true=present, false=not present)
	 */
	private boolean isBallAbsent() {
		if(!isSensor1Active() && !isSensor2Active()) {
			return true;
		}
		else return false;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

