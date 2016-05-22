/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data;

/**
 *
 * @author Simo
 */
public abstract class DataPoint {
    private double[] point;

    public DataPoint(double[] dataPoint) {
        this.point = dataPoint;
    }
    
    

    public double[] getData() {
        return point;
    }
    
    
    
}
