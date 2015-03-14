package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AssignWinchSpeed extends Command {

	
	private double prevEncDist;
	private boolean firstTime;
	private double offsetMotorSpeed = 0.0;
	
    public AssignWinchSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	firstTime = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	if(firstTime){
    		prevEncDist = Robot.winch.getEncoderDistance();
    		firstTime = false;
    		
    	}
    	*/
    	
    	SmartDashboard.putNumber("Left Trigger Value: ", Robot.oi.getPrimaryJoystick().getRawAxis(2));
    	SmartDashboard.putNumber("Right Trigger Value: ", -Robot.oi.getPrimaryJoystick().getRawAxis(3));
    	
    	//double EncoderChange = Robot.winch.getEncoderDistance() - prevEncDist;
    	double WinchSpeed = -Robot.oi.getPrimaryJoystick().getRawAxis(3); //the speed of the winch if the controller's assigned button is being pressed
    	if(WinchSpeed == 0.0){ //if neither buttons are pressed or both are pressed fully
    		WinchSpeed = Robot.oi.getPrimaryJoystick().getRawAxis(2);
    	}
    	if(WinchSpeed == 0.0){
    		Robot.winch.setMotorSpeed(0.0);
    		/*
    		//commented out until the winch encoders are installed
	    	if(Math.abs(EncoderChange) > RobotMap.ENCODER_RANGE){ //if the winch shows a decrease in a noticeable distant, then start the motors
	    	 
				//now convert the encoder ticks into actual distance (not needed for now)
	    		offsetMotorSpeed += 0.05; //continue to increase the motor speed until the speed can adequately offset the extra weight
	    		offsetMotorSpeed = (offsetMotorSpeed > 1.0 ? 1.0 : offsetMotorSpeed); //if motor speed is > 1, set it = to 1. If not, don't modify it
				Robot.winch.setMotorSpeed(offsetMotorSpeed);
			}*/
    	}else{
    		Robot.winch.setMotorSpeed(WinchSpeed); //use input from the controller
    	}
    	
    	/*
    	 if(Math.abs(EncoderChange) < RobotMap.ENCODER_RANGE){
         	prevEncDist = Robot.winch.getEncoderDistance();  //record the current encDist to the previous for the next time this command is executed. 
         	Robot.winch.setMotorSpeed(0.0);
         }
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.winch.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted(){
    	Robot.winch.stopMotor();
    }
}
