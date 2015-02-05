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
	//private Relay Piston;
	private boolean pistonState;
	
	
	
	public Pneumatics()
    {
         //compressor = new Compressor(RobotMap.PCM_ID);
        //compressor = new Compressor(RobotMap.PRESSURE_SWITCH_DIO, RobotMap.COMPRESSOR_SPIKE_RELAY);
//EDIT
        
		PneumaticCompressor = new Compressor(RobotMap.PCM_ID);
		PneumaticCompressor.setClosedLoopControl(true);
		
		Piston = new Solenoid(RobotMap.PISTON_ID);
		//Piston = new Relay(RobotMap.PISTON_SPIKE_RELAY, Relay.Direction.kBoth);
		
        pistonState = true;

      //compressor.start();
    }

    public boolean getPistonState()
    {
        return pistonState;
    }

    public void setPistonState(boolean newState)
    {
        pistonState = newState;
    }

    public void extendPiston()
    {
        Piston.set(true);
        pistonState = true;
        SmartDashboard.putBoolean("Piston State: ", pistonState);
    }

    public void retractPiston()
    {
        Piston.set(false);
        pistonState = false;
        SmartDashboard.putBoolean("Piston State: ", pistonState);
    }
    
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

