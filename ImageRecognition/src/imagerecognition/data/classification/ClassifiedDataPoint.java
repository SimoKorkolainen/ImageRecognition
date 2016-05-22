/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data.classification;

import imagerecognition.data.DataPoint;

/**
 *
 * @author Simo
 */
public class ClassifiedDataPoint extends DataPoint {
    private int pointClass;

    public ClassifiedDataPoint(double[] data, int pointClass) {
        super(data);
        this.pointClass = pointClass;
    }

    public int getPointClass() {
        return pointClass;
    }

    public void setPointClass(int pointClass) {
        this.pointClass = pointClass;
    }
    
    
}
