package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;

import org.usfirst.frc.team614.robot.commands.GetDistances;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 *
 */
public class RangeFinder extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private double VoltageOutput = (5.09 / 1024.0);
	
	private AnalogInput FrontRangeFinder;
	private AnalogInput RearRangeFinder;
	private AnalogInput LeftRangeFinder;
	private AnalogInput RightRangeFinder;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GetDistances());
    }
    
    public RangeFinder(){
    	
    	FrontRangeFinder = new AnalogInput(RobotMap.FRONT_RANGEFINDER_AC);
    }
    
    public double getDistance(RobotMap.RANGEFINDER currRangeFinder){
    	double currDistance = 0.0;
    	
    	switch(currRangeFinder.ordinal()){
    		case 0: 	//FRONT RANGEFINDER
    			currDistance = FrontRangeFinder.getAverageVoltage()/VoltageOutput;
    			break;
    		case 1:     //REAR RANGEFINDER
    			currDistance = RearRangeFinder.getAverageVoltage()/VoltageOutput;
    			break; 
    		case 2:     //LEFT RANGEFINDER
    			currDistance = LeftRangeFinder.getAverageVoltage()/VoltageOutput;
    			break;
    		case 3:		//RIGHT RANGEFINDER
    			currDistance = RightRangeFinder.getAverageVoltage()/VoltageOutput;
    			break;
    	}
    	
    	currDistance /= 2.54;  //converting from cm to inches
    	
    	return currDistance;
    }
}

