// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc348.RecycleRushForklift.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.apache.commons.math3.geometry.euclidean.threed.*;
import org.usfirst.frc348.RecycleRushForklift.Robot;

/**
 *
 */
public class  UpdateAHRSEstimateCommand extends Command {

    public UpdateAHRSEstimateCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    	requires(Robot.attitudeAndHeadingReferenceSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.attitudeAndHeadingReferenceSystem.InitializeSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Vector3D mag = Robot.attitudeAndHeadingReferenceSystem.UpdateAHRSEstimate();
    	Vector3D rot = Robot.attitudeAndHeadingReferenceSystem.ReadAngularVelocity();
    	    	
    	if(mag != null)
    	{
    		SmartDashboard.putNumber("Mag X", mag.getX());
    		SmartDashboard.putNumber("Mag Y", mag.getY());
    	}
    	if(rot != null)
    	{
    		SmartDashboard.putNumber("Angular Velocity Z (rad/s)", rot.getZ());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
