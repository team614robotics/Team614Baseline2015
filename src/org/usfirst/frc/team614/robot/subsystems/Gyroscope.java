package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;

import org.usfirst.frc.team614.robot.commands.UpdateAngle;

/**
 *
 */
public class Gyroscope extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Gyro GyroScope;
	private double GyroAngle;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new UpdateAngle());
    }
    
    public Gyroscope(){  //the constructor
    	
    	GyroScope = new Gyro(RobotMap.GYROSCOPE_AC);
    	GyroScope.initGyro();
    	
    	GyroAngle = GyroScope.getAngle() % 360;
    }
    
    public void setSensitivity(double Sensitivity){
    	GyroScope.setSensitivity(Sensitivity);
    }
    
    public double getGyroAngle(){
    	return GyroAngle;
    }
    
    public void setGyroAngle(double Angle){
    	GyroAngle = (Angle % 360);
    }
    
    public double getGyroRate(){
    	return GyroScope.getRate();
    }
    
    public void resetGyro(){
    	GyroScope.reset();
    }
}

