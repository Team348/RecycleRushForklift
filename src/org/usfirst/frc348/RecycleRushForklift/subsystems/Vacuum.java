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

import java.awt.Robot;

import org.usfirst.frc348.RecycleRushForklift.RobotMap;
import org.usfirst.frc348.RecycleRushForklift.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Vacuum extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController vacuumPumpMotor = RobotMap.vacuumVacuumPumpMotor;
    AnalogInput vacuumSensor = RobotMap.vacuumVacuumSensor;
    Servo vacuumForkValveServo = RobotMap.vacuumVacuumForkValveServo;
    Servo vacuumFrontValveServo = RobotMap.vacuumVacuumFrontValveServo;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    Timer lastModeCommandedTimer = new Timer();
    private boolean inForkMode = false;
    private boolean inFrontMode = false;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void ActivatePump() {
    	double pumpSpeed = RobotMap.prefs.getDouble("Vacuum_OpenLoopPumpSpeedCommand", 0.7);
    	
    	vacuumPumpMotor.set(pumpSpeed);
    }
    
    public void DeactivatePump() {
    	vacuumPumpMotor.set(0);
    }
    
    public void ActivateForkVacuumMode() {
    	ActivatePump();
    	CloseFrontValve();
    	inForkMode = true;
    	inFrontMode = false;
    	lastModeCommandedTimer.reset();
    }
    
    public void ActivateFrontVacuumMode() {
    	ActivatePump();
    	CloseForkValve();
    	inFrontMode = true;
    	inForkMode = false;
    	lastModeCommandedTimer.reset();
    }
    
    public void ActivateOffMode() {
    	DeactivatePump();
    	CloseForkValve();
    	CloseFrontValve();
    	inFrontMode = false;
    	inForkMode = false;
    }
    
    public void OpenForkValve()
    {
    	double forkOpenPosition = RobotMap.prefs.getDouble("Vacuum_ForkOpenPosition", 0);
    	vacuumForkValveServo.setPosition(forkOpenPosition);
    }
    
    public void CloseForkValve()
    {
    	double forkClosedPosition = RobotMap.prefs.getDouble("Vacuum_ForkClosedPosition", 0);
    	vacuumForkValveServo.setPosition(forkClosedPosition);
    }
    
    public void OpenFrontValve()
    {
    	double frontOpenPosition = RobotMap.prefs.getDouble("Vacuum_FrontOpenPosition", 0);
    	vacuumFrontValveServo.setPosition(frontOpenPosition);
    }
    
    public void CloseFrontValve()
    {
    	double frontClosedPosition = RobotMap.prefs.getDouble("Vacuum_FrontClosedPosition", 0);
    	vacuumFrontValveServo.setPosition(frontClosedPosition);
    }
    
    public void InitializeTimer()
    {
    	lastModeCommandedTimer.start();
    }
    
    public void CheckIfTimeToTurnOff()
    {
    	if(lastModeCommandedTimer.get() > 5)
    	{
    		ActivateOffMode();
    	}
    }
    
    public void DropPart(){
    	CloseFrontValve();
    	CloseForkValve();
    }
  
    public void GrabPart(){
    	if(inFrontMode)
    	{
    		OpenFrontValve();
    		CloseForkValve();
    	}
    	else if (inForkMode)
    	{
    		OpenForkValve();
    		CloseFrontValve();
    	}
    	else {
    		CloseForkValve();
    		CloseFrontValve();
    	}
    }
}

