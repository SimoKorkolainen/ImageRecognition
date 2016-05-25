/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

/**
 *
 * @author simokork
 */
public class ArrayUtil {
    
    public static double[][] vectTo2D(double[] vector) {
        
        double a[][] = new double[vector.length][1];
        
        for (int i = 0; i < vector.length; i++) {
            a[i][0] = vector[i];
        }
        
        return a;
        
    }
    
    
    public static double[] random(double min, double max, int size) {
        
        double[] res = new double[size];
        
        for (int i = 0; i < size; i++) {
        
            res[i] = min + Math.random() * (max - min);
        
        }
        
        return res;
    
    }

}
