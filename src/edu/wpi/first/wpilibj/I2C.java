/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Nathan
 */
public class I2C {
	public enum Port {kOnboard(0), kMXP(1);
        private int value;

        private Port(int value){
                this.value = value;
        }

        public int getValue(){
                return this.value;
        }
	};
	
	
	
    public I2C(Port port, int deviceAddress) {
    }
    
    public boolean readOnly(byte[] buffer, int count) {
    	
    	SmartDashboard.putString("I2C Data:", "C:0x" + Integer.toHexString((int)buffer[0]));
    	return true;
    }

    public synchronized boolean transaction(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize) {
        SmartDashboard.putString("I2C Data:", "C:0x" + Integer.toHexString((int)dataToSend[0]) + " D1:0x" +
                Integer.toHexString((int)dataToSend[1]) + " D2:0x" + Integer.toHexString((int)dataToSend[2]));
        return false;
    }
}
