package org.usfirst.frc.team614.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousTote_CG extends CommandGroup {
    
	private final double F_ToteEncoder = 0.0; //First Tote Encoder	
	private final double S_ToteEncoder = 0.0; //Second
	private final double T_ToteEncoder = 0.0; //Third
	private final double DriveDistance = 0.0;
	private final double Timeout = 0.0;
	
    public  AutonomousTote_CG() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//Pickup the first tote and drive to the second
    	addSequential(new MoveWinch(F_ToteEncoder, true));
    	addSequential(new DriveDistance(DriveDistance, true, Timeout));
    	addSequential(new MoveWinch(F_ToteEncoder, false));
    	
    	//Pick up the two totes and drive to the third
    	addSequential(new MoveWinch(S_ToteEncoder, true));
    	addSequential(new DriveDistance(DriveDistance, true, Timeout));
    	addSequential(new MoveWinch(S_ToteEncoder, false));
    	
    	//Pick up the three totes
    	addSequential(new MoveWinch(T_ToteEncoder, true));
    }
}
