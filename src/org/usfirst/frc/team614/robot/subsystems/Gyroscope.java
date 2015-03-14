package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;

import org.usfirst.frc.team614.robot.commands.GetAngle;

/**
 *
 */
public class Gyroscope extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Gyro GyroScope;
	private BuiltInAccelerometer Accelerometer;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GetAngle());
    }
    
    public Gyroscope(){  //the constructor
    	
    	Accelerometer = new BuiltInAccelerometer();
    	GyroScope = new Gyro(RobotMap.GYROSCOPE_AC);
    	GyroScope.setSensitivity(5 / GyroScope.getRate());
    	//GyroScope.reset();    	GyroScope.initGyro();
    	
    }
    
    public double getAngle(){ //the methods
    	return GyroScope.getAngle();
    }
    
    public void setSensitivity(double Sensitivity){
    	GyroScope.setSensitivity(Sensitivity);
    }
    
    public void resetGyro(){
    	GyroScope.reset();
    	if(Accelerometer.getX() < RobotMap.ACCELEROMETER_RANGE && Accelerometer.getX() > -RobotMap.ACCELEROMETER_RANGE){
    		if(Accelerometer.getY() < RobotMap.ACCELEROMETER_RANGE && Accelerometer.getY() > -RobotMap.ACCELEROMETER_RANGE){
    			GyroScope.initGyro();
    		}
    	}
    }
}

