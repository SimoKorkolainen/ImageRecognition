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
public class Vector extends Matrix {

    public Vector(double[] vector) {
        super(ArrayUtil.vectTo2D(vector));
    }
    
    public Vector(double[][] vector) {
        super(vector);
    }
    
    public int size() {
        return getRows();
    }
    
    public double get(int i) {
    
        return get(i, 1);
    
    }
    
    
    public Vector concatenate(Vector a, Vector b) {
        
        double c[] = new double[a.size() + b.size()];
        
        for (int i = 0; i < a.size(); i++) {
            
            c[i] = a.get(i);
        
        }
        
        for (int i = 0; i < b.size(); i++) {
            
            c[i + a.size()] = b.get(i);
        
        }
        
        return new Vector(c);
    
    }
    
    
    public double dotProduct(Vector b) {
    
        return transpose().product(b).get(0, 0);
    
    }
    
    public double sum() {
        double sum = 0;
        for (int i = 0; i < size(); i++) {
            sum += get(i);
        }
        
        return sum;
    }
    
    public void set(int i, double value) {
        super.set(i, 1, value);
    }
    
   
    @Override
    public Vector times(double mult) {
        return new Vector(super.times(mult).asArray());
    }
    
    public static Vector zero(int n) {
        return new Vector(Matrix.zeros(n, 1).asArray());
    }
    
    
    
}
