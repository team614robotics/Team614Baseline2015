package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AssignWinchSpeed extends Command {

	private double prevEncValue;
	private double EncoderChange;
	private double WinchSpeed;
	private double offsetMotorSpeed;
	
    public AssignWinchSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	prevEncValue = Robot.winch.getEncoderDistance();
    	EncoderChange = 0.0;
    	WinchSpeed = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	
    	EncoderChange = 0.0;
    	
    	//Get the Winch Speed from the Right/Left triggers
    	WinchSpeed = -Robot.oi.getPrimaryJoystick().getRawAxis(3);
    	if(WinchSpeed == 0.0){
    		WinchSpeed = Robot.oi.getPrimaryJoystick().getRawAxis(2);
    	}
    	
    	/**
    	 * If we have a non-zero value for WinchSpeed, set the motors
    	 * to run at that speed. Otherwise, maintain the current
    	 * winch encoder value.
    	 */
    	if(WinchSpeed == 0.0){
    		/*EncoderChange = Robot.winch.getEncoderDistance() - prevEncValue;
    		if(Math.abs(EncoderChange) > RobotMap.ENCODER_RANGE){
    			offsetMotorSpeed = (offsetMotorSpeed + 0.05 > 1.0 ? 1.0 : offsetMotorSpeed + 0.05);
    			Robot.winch.setMotorSpeed(offsetMotorSpeed);
    		}else{
    			/**
    			 * Either set the motors to a stall speed, and risk
    			 * everything ******* up when there is no weight on
    			 * the winch, or set it to zero and have to continuodly
    			 * rev-up the motors.
    			 */
    			
    			//Robot.winch.stopMotor();
    		//}
    		Robot.winch.stopMotor();
    	}else{
    		SmartDashboard.putNumber("Winch Speed", WinchSpeed);
    		if(Robot.winch.getLimitStatus()){
	    		if(Robot.winch.getEncoderDistance() > Robot.winch.getWinchLimit()){
	    			Robot.winch.setMotorSpeed(WinchSpeed);
	    		}else{
	    			Robot.winch.stopMotor();
	    		}
    		}else{
    			Robot.winch.setMotorSpeed(WinchSpeed);
    		}
    	}
    	
    	Robot.winch.logEncoderData();
    	
    	/*if(Math.abs(EncoderChange) < RobotMap.ENCODER_RANGE){
    		prevEncValue = Robot.winch.getEncoderDistance();
    		offsetMotorSpeed = 0.0;
    		Robot.winch.stopMotor();
    	}*/	
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
