/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

/**
 *
 * @author Simo
 */
public class MovingGraph {
    private double y[];
    private int position;
    private int fill;

    public MovingGraph(int size) {
        this.y = new double[size];
        this.position = 0;
        this.fill = 0;
    }
    
    public void addPoint(double point) {
        
        y[fill % y.length] = point;
        fill++;
        if (fill > y.length) {
            position++;
        }
    }
    
    public double getY(int at) {
        
        return y[(position + at) % y.length];
    
    }
    
    public int getSize() {
        return y.length;
    }
    
    public int getReadySize() {
        return Math.min(fill, y.length);
    }
}
