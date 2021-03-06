package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private Compressor PneumaticCompressor;
	private Solenoid Piston;
	private boolean pistonState;
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public Pneumatics(){
        
		PneumaticCompressor = new Compressor(RobotMap.PCM_ID);
		PneumaticCompressor.setClosedLoopControl(true);
		
		Piston = new Solenoid(RobotMap.PISTON_ID);
		
        pistonState = false;
	}
    
    public void extendPiston(){
    	pistonState = true;
        Piston.set(pistonState);
    }

    public void retractPiston(){
    	pistonState = false;
        Piston.set(pistonState);
    }
    
    public boolean getPistonState(){
        return pistonState;
    }
    
    public void setPistonState(boolean newState){
        pistonState = newState;
    }
}

