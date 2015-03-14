package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveWinch extends Command {

	private double WinchDistance;
	private boolean MoveDirection; //(TRUE - UP, FALSE - DOWN)
	
	private boolean isDone;
	private double Timeout;
	
    public MoveWinch(double Distance, boolean Direction, double TimeLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    	WinchDistance = Distance;
    	MoveDirection = Direction;
    	Timeout = TimeLimit;
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	isDone = false;
    	setTimeout(Timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.winch.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winch.stopMotor();
    }
}
