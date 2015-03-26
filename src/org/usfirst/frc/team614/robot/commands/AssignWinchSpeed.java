package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AssignWinchSpeed extends Command {
	
	private static final double DOWN_REDUCTION = 7.0;
	
	private double WinchSpeed;
	
    public AssignWinchSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	WinchSpeed = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	
    	//Get the Winch Speed from the Right/Left triggers
    	WinchSpeed = Robot.oi.getPrimaryJoystick().getRawAxis(3);
    	if(WinchSpeed == 0.0){
    		WinchSpeed = -(Robot.oi.getPrimaryJoystick().getRawAxis(2) / DOWN_REDUCTION);
    	}

    	Robot.winch.setMotorSpeed(WinchSpeed);
    	Robot.winch.logEncoderData();
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
