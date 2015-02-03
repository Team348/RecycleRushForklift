// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc348.RecycleRushForklift.subsystems;

import org.usfirst.frc348.RecycleRushForklift.RobotMap;
import org.usfirst.frc348.RecycleRushForklift.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Elevator extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Encoder elevatorEncoder = RobotMap.elevatorElevatorEncoder;
    SpeedController elevatorMotor1 = RobotMap.elevatorElevatorMotor1;
    SpeedController elevatorMotor2 = RobotMap.elevatorElevatorMotor2;
    DigitalInput elevatorBottomLimitSwitch = RobotMap.elevatorElevatorBottomLimitSwitch;
    DigitalInput elevatorTopLimitSwitch = RobotMap.elevatorElevatorTopLimitSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Initialize your subsystem here
    public Elevator() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Elevator", 0.08, 0.004, 0.0);
        setAbsoluteTolerance(0.25);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Elevator", "PIDSubsystem Controller", getPIDController());
        getPIDController().setOutputRange(-1.0, 1.0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
       
        double p = RobotMap.prefs.getDouble("Elevator_P", 1.0);
        double i = RobotMap.prefs.getDouble("Elevator_I", 0.0);
        double d = RobotMap.prefs.getDouble("Elevator_D", 0.0);

        double distPerPulse = RobotMap.prefs.getDouble("Elevator_DistancePerPulse", 0.025);
        
        elevatorEncoder.setDistancePerPulse(distPerPulse);
        
       getPIDController().setPID(p, i, d);
       //enable();
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ManualElevatorCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        return elevatorEncoder.pidGet();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        elevatorMotor1.pidWrite(output);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
      elevatorMotor2.pidWrite(output);

    }
    
    public boolean isAtLowerLimit(){
    	return (elevatorBottomLimitSwitch.get());
    	
    }
    
    public boolean isAtUpperLimit(){
    	return (elevatorTopLimitSwitch.get());  	
    }
    
    public void resetEncoder(){
    	elevatorEncoder.reset();
    }
    
    public void driveDownSlowly(){
    	setSpeed(-0.1);
    }
    
    public void setSpeed(double speed){
    	elevatorMotor1.set(speed);
    	elevatorMotor2.set(speed);
    }
    
    public void stop(){
    	setSpeed(0);
    }
    
    
}
