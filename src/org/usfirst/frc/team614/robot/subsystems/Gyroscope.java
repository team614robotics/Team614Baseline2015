package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Gyro;

import org.usfirst.frc.team614.robot.commands.GetAngle;

/**
 *
 */
public class Gyroscope extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Gyro GyroScope;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GetAngle());
    }
    
    public Gyroscope(){
    	
    	GyroScope = new Gyro(RobotMap.GYROSCOPE_AC);
    	GyroScope.setSensitivity(0.009);
    	//GyroScope.reset();
    	GyroScope.initGyro();
    }
    
    public double getAngle(){
    	return GyroScope.getAngle();
    }
    
    public void setSensitivity(double Sensitivity){
    	GyroScope.setSensitivity(Sensitivity);
    }
    
    public void resetGyro(){
    	GyroScope.reset();
    }
}

