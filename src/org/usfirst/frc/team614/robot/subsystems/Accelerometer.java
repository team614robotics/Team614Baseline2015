package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.commands.GetAngle;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

/**
 *
 */
public class Accelerometer extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private BuiltInAccelerometer Accelerometer;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new GetSpeed());
    }
    
    public Accelerometer(){ //constructor
    	Accelerometer = new BuiltInAccelerometer();
    }
    
    //returns the accelerometer's speed in g's
    
    public double getSpeedX(){  
    	return Accelerometer.getX();
    }
    
    public double getSpeedY(){
    	return Accelerometer.getY();
    }
    
    public double getSpeedZ(){
    	return Accelerometer.getZ();
    }
   
    public void logSpeed(){
    	SmartDashboard.putNumber("Acceleration in X", getSpeedX()); //DOn'tknow why words are crossed out but it works. Sorry I failed you Jared
    	SmartDashboard.putNumber("Acceleration in Y", getSpeedY());
    	SmartDashboard.putNumber("Acceleration in Z", getSpeedZ());
    
    	
    	
}
}

