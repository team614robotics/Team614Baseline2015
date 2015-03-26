package org.usfirst.frc.team614.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousBarrel_CG extends CommandGroup {
	
	private final double Distance = 0.0;
	
    public  AutonomousBarrel_CG() {
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
    	/*addSequential(new DriveDistance(10000000, false, 1.0));
    	addSequential(new ExtendPiston(1.0));
    	//addSequential(new RetractPiston(1.0));
    	addSequential(new DriveDistance(10000000, true, 5.0));
    	addSequential(new DriveDistance(10000000, false, 0.5));
    	addSequential(new RetractPiston(1.0));*/
    	
    	/**
    	 * NOTE: We can care less about the actual distances traveled. Quantum Physics and
    	 * stuff will always give us problems. Instead, we need the relative time for each
    	 * action. Pay no mind to the 1000000, only the times at the end of the DriveDistance.
    	 */
    	
    	//Drive back to line up with the barrel
    	addSequential(new DriveDistance(1000000, false, 1.0));
    	
    	//Then lower down the barrel arm
    	addSequential(new ExtendPiston(3.0));
    	
    	//Drive forwards to the auto zone
    	addSequential(new DriveDistance(1000000, true, 5.0));
    	
    	//Drive a little back to pick up the arm
    	addSequential(new DriveDistance(1000000, false, 0.5));
    	
    	//Now pick up the arm
    	addSequential(new RetractPiston(3.0));
    }
}
