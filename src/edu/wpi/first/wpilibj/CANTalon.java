/*
 *  This file is part of frcjcss.
 *
 *  frcjcss is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  frcjcss is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with frcjcss.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.wpi.first.wpilibj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A CANTalon speed controller emulation for FRC.
 * @author Nick DiRienzo, Patrick Jameson
 * @version 11.12.2010.3
 */
public class CANTalon implements ComponentListener, ActionListener {

    private double speed;
    private boolean isGraphRunning;
    private long startTime;

    private JFrame frame;
    private JButton startStop;
    
    private SpeedGrapher graph;
    private JLabel talonSpeed;
    
    
    
    



    
    private void initGraph(int devNumber) {
       frame = new JFrame("Talon Emulator: " + devNumber);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //frame.setResizable(false);
       frame.setLocation(510, 0);
       frame.setLayout(new BorderLayout());
       frame.setPreferredSize(new Dimension(300, 320));
       
       //tells the current speed of the victor in % above the graph.
       talonSpeed = new JLabel("Current Speed: " + (speed*100) + "%");
       frame.add(talonSpeed, BorderLayout.NORTH);
       
       //allows user to stop the movement of the graph. button located under the graph.
       startStop = new JButton("Stop Graph");
       startStop.addActionListener(this);
       frame.add(startStop, BorderLayout.SOUTH);
       
       //makes the actual graph.
       graph = new SpeedGrapher(300, 300);
       frame.add(graph, BorderLayout.CENTER);
       
       startTime = 0;
       isGraphRunning = true;
       
       frame.addComponentListener(this);

       frame.pack();
       frame.setVisible(true);          
    }
    
    /**
     * Creates a new CANTalon speed controller.
     * @param channel The device number of the talon.
     */
    public CANTalon(int devNumber) {
       initGraph(devNumber);
    }

    public CANTalon(int devNumber, int controlPeriodMs) {
       initGraph(devNumber);
    }
    
    public CANTalon(int devNumber, int controlPeriodMs, int enablePeriodMs) {
       initGraph(devNumber);
    }

    public enum TalonControlMode implements CANSpeedController.ControlMode {
       PercentVbus(0), Position(1), Speed(2), Current(3), Voltage(4), Follower(5), MotionProfile(6), Disabled(15);

       public final int value;

       public static TalonControlMode valueOf(int value) {
         for (TalonControlMode mode : values()) {
           if (mode.value == value) {
             return mode;
           }
         }

         return null;
       }

       private TalonControlMode(int value) {
         this.value = value;
       }

       @Override
       public boolean isPID() {
           return this == Current || this == Speed || this == Position;
       }

       @Override
       public int getValue() {
           return value;
       }
     }
     public enum FeedbackDevice {
       QuadEncoder(0), AnalogPot(2), AnalogEncoder(3), EncRising(4), EncFalling(5), CtreMagEncoder_Relative(6), CtreMagEncoder_Absolute(7), PulseWidth(8);

       public int value;

       public static FeedbackDevice valueOf(int value) {
         for (FeedbackDevice mode : values()) {
           if (mode.value == value) {
             return mode;
           }
         }

         return null;
       }

       private FeedbackDevice(int value) {
         this.value = value;
       }
     }

    
	/**
     * Sets the value of the CANTalon using a value between -1.0 and +1.0.
     * @param speed The speed value of the CANTalon between -1.0 and +1.0.
     */
    public void set(double speed) {
        this.speed = speed;

    }

    /**
     * Gets the most recent value of the CANTalon.
     * @return The most recent value of the CANTalon from -1.0 and +1.0.
     */
    public double get() {
        return speed;
    }
    
    public void changeControlMode(CANTalon.TalonControlMode controlMode) {
       
    }
    
    public void setFeedbackDevice(FeedbackDevice device) {
       
    }
    
    public void configNominalOutputVoltage(double forwardVoltage,double reverseVoltage) {
    
    }
    
    public void configPeakOutputVoltage(double forwardVoltage,double reverseVoltage) {
    
    }
    
    public void configEncoderCodesPerRev(int codesPerRev) {
    
    }
    
    public void reverseSensor(boolean flip) {
       
    }
    
    public void reverseOutput(boolean flip) {
       
    }
    
    public void setProfile(int profile) {
       
    }
    
    public void setF(double f) {
       
    }
    
    public void setP(double p) {
       
    }
    
    public void enableControl() {
       
    }
    //add pidWrite method?
    
	public void componentResized(ComponentEvent e) {
		graph.setGraphSize(frame.getWidth(), frame.getHeight());
		graph.repaint();
	}
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startStop) {
			startStop.setText((isGraphRunning ? "Start" : "Stop") + " Graph");
			isGraphRunning = !isGraphRunning;
		}
	}
	
	//extra stuffs
	public void componentShown(ComponentEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}

}