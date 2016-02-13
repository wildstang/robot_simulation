/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 *
 * @author Nathan
 */
public class Encoder {
    int count;
    public Encoder(int i, int j, boolean t, EncodingType e) {
       System.out.println("Stub Encoder");
        //Do nothing
    }
    public int get() {
        return count;
    }
    public void set(int count) {
        this.count = count;
//        System.out.println("Encoder count: " + count);
    }
    public void reset() {
        count = 0;
    }
    
    public double getRate() {
       return count / 20;
    }
    
    public void setMaxPeriod(double p)
    {
    }
    
    public void setMinRate(double rate)
    {
       
    }
    public void setDistancePerPulse(double dist)
    {
       
    }
    public void setReverseDirection(boolean dir)
    {
       
    }
    public void setSamplesToAverage(Integer samples)
    {
       
    }
}
