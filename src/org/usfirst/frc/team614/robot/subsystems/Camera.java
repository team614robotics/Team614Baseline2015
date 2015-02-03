package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.MoveCamera;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class Camera extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private AxisCamera Camera;
	private Servo H_Servo;
	private Servo V_Servo;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MoveCamera());
    }
    
    public Camera(){
    	
    	Camera = new AxisCamera(RobotMap.CAMERA_IP);
    	
    	H_Servo = new Servo(RobotMap.HORIZONTAL_SERVO_PWM);
    	V_Servo = new Servo(RobotMap.VERTICAL_SERVO_PWM);
    }
    
    public void setServoPosition(double V_Value, double H_Value){
    	H_Servo.set(H_Value);
    	V_Servo.set(V_Value);
    }
}

