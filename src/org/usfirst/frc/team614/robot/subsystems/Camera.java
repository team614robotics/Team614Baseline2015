package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;;

/**
 *
 */
public class Camera extends Subsystem {
    
	private AxisCamera RobotCamera;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Camera(){
    	
    	RobotCamera = new AxisCamera(RobotMap.CameraIP);
    }
}

