/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.math;

/**
 *
 * Matriisin kuvaamiseen käytetty luokka.
 */
public class Matrix {
    
    private double matrix[][];

    /**
     * Matriisin luova konstruktori.
     * @param matrix matriisi taulukkona
     */
    public Matrix(double[][] matrix) {
        
        this.matrix = matrix;
    
    }
    
    /**
     * Metodi palauttaa nollamatriisin, jossa on n riviä ja m saraketta.
     * @param n rivien määrä
     * @param m sarakkeiden määrä
     * @return nollamatriisi
     */
    public static Matrix zeros(int n, int m) {
        
        double[][] a = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = 0;
            }
        }
        
        return new Matrix(a);
    
    }
    
    /**
     * 
     * @return rivien määrä
     */
    public int getRows() {
        return matrix.length;
    }
    
    /**
     * 
     * @return sarakkeiden määrä
     */
    public int getCols() {
        return matrix[0].length;
    }
    
    
    /**
     * Metodi palauttaa matriisin A ja matriisin B matriisitulon AB.
     * @param other matriisi B
     * @return tulo AB
     */
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
    
    /**
     * Metodi palauttaa matriisin solun A(i, j) arvon.
     * @param i rivi
     * @param j sarake
     * @return 
     */
    
    public double get(int i, int j) {
        return matrix[i][j];
    }
    /**
     * Metodi asettaa matriisin solun A(i, j) arvoksi annetun arvon.
     * @param i rivi
     * @param j sarake
     * @param value asetettava arvo
     */
    public void set(int i, int j, double value) {
        matrix[i][j] = value;
    }
    
    /**
     * 
     * @return matriisi taulukkona
     */
    public double[][] asArray () {
    
        return matrix;
    
    }
    
    /**
     * Metodi palauttaa matriisin kerrottuna skalaarilla.
     * @param mult skalaari
     * @return uusi matriisi
     */
    public Matrix times(double mult) {
        Matrix result = Matrix.zeros(getRows(), getCols());
    
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                result.set(i, j, mult * get(i, j));
                
            }
        }
    
        return result;
    
    }
    

    
    
    /**
     * Metodi palauttaa matriisin transponoituna.
     * @return transpoosi
     */
    public Matrix transpose() {
        
        Matrix transpose = Matrix.zeros(getCols(), getRows());
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                transpose.set(j, i, get(i, j));
                
            }
        }
        
        return transpose;
    }
    
    
    
    /**
     * Metodi palauttaa sarakkeittain lasketun summan matriisin alkioista.
     * @return sarakesummamatriisi
     */
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
    
    /**
     * Metodi laskee matriisien A ja B summan.
     * @param b yhteenlaskettava matriisi
     * @return summa
     */
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
