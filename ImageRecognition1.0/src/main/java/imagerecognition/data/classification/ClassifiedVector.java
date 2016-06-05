/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data.classification;

import imagerecognition.math.Vector;


/**
 *
 * Vektori, joka sisältää vektorin luokan.
 */
public class ClassifiedVector extends Vector {
    private int pointClass;
    private int pointClassMax;

    /**
     * konstruktori luo Luokitellun vektorin.
     * @param data vektorin data
     * @param pointClass vektorin luokka
     */
    public ClassifiedVector(double[] data, int pointClass, int pointClassMax) {
        super(data);
        this.pointClass = pointClass;
        this.pointClassMax = pointClassMax;
    }

    public int getPointClass() {
        return pointClass;
    }
    
    
    public void setPointClass(int pointClass) {
        this.pointClass = pointClass;
    }
    
    public int getPointClassMax() {
        return pointClassMax;
    }
    
}
