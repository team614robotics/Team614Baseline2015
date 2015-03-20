package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.MonitorAccelerometer;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 *
 */
public class Accelerometer extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final I2C.Port I2CPort = I2C.Port.kOnboard;
	private final edu.wpi.first.wpilibj.interfaces.Accelerometer.Range MeasuringRange 
	              = edu.wpi.first.wpilibj.interfaces.Accelerometer.Range.k16G;
	
	private ADXL345_I2C Accelerator;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new GetSpeed());
    	setDefaultCommand(new MonitorAccelerometer());
    }
    
    public Accelerometer(){ //constructor
    	
    	Accelerator = new ADXL345_I2C(I2CPort, MeasuringRange);
    }
    
    //returns the accelerometer's speed in g's
    
    public double getSpeedX(){  
    	return Accelerator.getX();
    }
    
    public double getSpeedY(){
    	return Accelerator.getY();
    }
    
    public double getSpeedZ(){
    	return Accelerator.getZ();
    }
   
    public void logSpeed(){
    	SmartDashboard.putNumber("Acceleration in X", getSpeedX()); //DOn'tknow why words are crossed out but it works. Sorry I failed you Jared
    	SmartDashboard.putNumber("Acceleration in Y", getSpeedY());
    	SmartDashboard.putNumber("Acceleration in Z", getSpeedZ());  	
    }
}

