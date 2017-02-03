package org.team2168.subsystems;

import org.team2168.RobotMap;

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
	
	private static ShooterIndexer instance = null;
	
	private ShooterIndexer() {
		indexerMotor = new Spark(RobotMap.INDEXER_WHEEL);
	}
	
	public ShooterIndexer getInstance(){
		if(instance == null)
			instance = new ShooterIndexer();
		
		return instance;
	}
	
	private void setSpeed(double speed) {
		indexerMotor.set(speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

