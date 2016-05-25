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
public class Matrix {
    private double matrix[][];

    
    public Matrix(double[][] matrix) {
        
        this.matrix = matrix;
    
    }
    
    
    public static Matrix zeros(int n, int m) {
        
        double[][] a = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = 0;
            }
        }
        
        return new Matrix(a);
    
    }
    
    
    public int getRows() {
        return matrix.length;
    }
    
    public int getCols() {
        return matrix[0].length;
    }
    
    public Matrix product(Matrix other) {
        
        
        Matrix result = zeros(getRows(), getCols());
        
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < other.getCols(); j++) {
                double dot = 0;
                for (int k = 0; k < other.getRows(); k++) {
                    dot += get(i, k) * other.get(k, j);
                }
                
                result.set(i, j, dot);

            }
        }
        
        return result;
    }
    
    public double get(int i, int j) {
        return matrix[i][j];
    }
    
    public void set(int i, int j, double value) {
        matrix[i][j] = value;
    }
    
    public double[][] asArray () {
    
        return matrix;
    
    }
    
    public Matrix times(double mult) {
        Matrix result = Matrix.zeros(getRows(), getCols());
    
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                result.set(i, j, mult * get(i, j));
                
            }
        }
    
        return result;
    
    }
    

    
    
    
    public Matrix transpose() {
        
        Matrix transpose = Matrix.zeros(getCols(), getRows());
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                transpose.set(j, i, get(i, j));
                
            }
        }
        
        return transpose;
    }
    
    
    
    
    public Matrix columnSum() {
        Matrix sum = Matrix.zeros(1, getRows());
        
        for (int j = 0; j < getCols(); j++) {
            
            double colSum = 0;
            
            for (int i = 0; i < getRows(); i++) {
            
                colSum += get(i, j);
                
            }
            
            sum.set(1, j, colSum);
        
        }
        
        return sum;
    
    }
    
    
    public Matrix plus(Matrix b) {
    
        Matrix result = Matrix.zeros(getRows(), getCols());
    
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                result.set(i, j, get(i, j) + b.get(i, j));
                
            }
        }
        
        return result;
    
    
    }

    
}
