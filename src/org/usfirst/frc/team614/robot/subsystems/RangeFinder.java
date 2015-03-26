package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.GetRangefinderDistances;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class RangeFinder extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private double VoltageOutput = (5.09 / 1024.0);
	
	//private AnalogInput FrontRangeFinder;
	private AnalogInput RangeFinder;
	


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GetRangefinderDistances());
    }
    
    public RangeFinder(){
    	
    	//FrontRangeFinder = new AnalogInput(RobotMap.FRONT_RANGEFINDER_AC);
    	RangeFinder = new AnalogInput(RobotMap.RANGEFINDER_AC);
    }
    
    public double getDistance(){
    	double currDistance = 0.0;
    	
    	currDistance = RangeFinder.getAverageVoltage() / VoltageOutput;
    	
    	currDistance /= 2.54;  //converting from cm to inches
    	
    	//System.out.println(currDistance);
    	
    	return currDistance;
    }
}

