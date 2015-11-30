package edu.wpi.first.wpilibj;



public class PowerDistributionPanel {
	
	public PowerDistributionPanel() {
    }
	public double getVoltage() {
		return 11.25;
	}
	public double getTemperature() {
		return 27.2;
	}
	public double getCurrent(int channel) {
		return 2.34;
	}
	public double getTotalCurrent(){
		return 5.8;
	}
	
}
