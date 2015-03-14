package org.usfirst.frc.team614.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team614.robot.commands.MonitorPowerDistribution;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 *
 */
public class PowerPanel extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private PowerDistributionPanel Power;
	
	private ArrayList<Double> MaxCurrent = new ArrayList<Double>();

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MonitorPowerDistribution());
    }
    
    public PowerPanel(){
    	
    	Power = new PowerDistributionPanel();
    	for(int i = 0; i < 16; i++){
    		MaxCurrent.add(Power.getCurrent(i));
    	}
    }
    
    /**
     * This method is used to query to PowerDistribution for the input voltage draw
     * and current running through each of the channels (0-15). Additionally,
     * it will record and output the max current draw from each channel.
     * 
     * @author Jered Tupik, Cassandra Beem
     * @since 2/14/2015
     */
    public void logPowerData(){
    	System.out.println("Total Input Voltage: " + Power.getVoltage());
    	for(int i = 0; i < 16; i++){
    		double currCurrent = Power.getCurrent(i);
    		//SmartDashboard.putNumber("Current " + i + ": ", currCurrent);
    		System.out.println("Current " + i + ": " + currCurrent);
    		if(currCurrent > MaxCurrent.get(i)){
    			MaxCurrent.set(i, Power.getCurrent(i));
    		}
    		System.out.println("Max Current at " + i + ": " + MaxCurrent.get(i));
    		System.out.println();
    	}
    	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}

