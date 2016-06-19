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
        //double nanoTime = System.nanoTime();

        //System.out.println(getRows() + " x " + getCols() + " times " + other.getRows() + " x " + other.getCols());

        Matrix result = Matrix.zeros(getRows(), other.getCols());
        
        productToDestination(other, result);
        
        return result;
    }
    
    /**
     * Metodi laskee matriisin this ja matriisin other tulon
     * ja tallentaa sen matriisiin destMatrix
     * @param other toinen matriisi
     * @param destMatrix tulomatriisi
     */
    public void productToDestination(Matrix other, Matrix destMatrix) {

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < other.getCols(); j++) {
                double dot = 0;
                for (int k = 0; k < other.getRows(); k++) {
                    dot += get(i, k) * other.get(k, j);
                }
                
                destMatrix.set(i, j, dot);

            }
        }

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
    
        timesToDestination(mult, result);
        
        return result;
    
    }
    

    /**
     * Metodi kertoo matriisin skalaarilla.
     * @param mult skalaari
     * @param destMatrix tulosmatriisi
     *
     */
    public void timesToDestination(double mult, Matrix destMatrix) {

        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                destMatrix.set(i, j, mult * get(i, j));
                
            }
        }
    
    
    } 
    
    /**
     * Metodi palauttaa matriisin transponoituna.
     * @return transpoosi
     */
    public Matrix transpose() {
        
        Matrix transpose = Matrix.zeros(getCols(), getRows());
        
        transposeToDestination(transpose);
        
        return transpose;
    }
    
    /**
     * Metodi laskee matriisin transpoosin.
     * 
     * @param destination tulosmatriisi
     */
    public void transposeToDestination(Matrix destination) {
 
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                destination.set(j, i, get(i, j));
                
            }
        }

    }
    
    
    /**
     * Metodi palauttaa sarakkeittain lasketun summan matriisin alkioista.
     * @return sarakesummamatriisi
     */
    public Matrix columnSum() {
        Matrix sum = Matrix.zeros(1, getCols());
        
        for (int j = 0; j < getCols(); j++) {
            
            double colSum = 0;
            
            for (int i = 0; i < getRows(); i++) {
            
                colSum += get(i, j);
                
            }
            
            sum.set(0, j, colSum);
        
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
    
        
        plusToDestination(b, result);
        
        return result;
    
    
    }
    /**
     * Metodi laskee matriisien A ja B summan.
     * @param destination  tulosmatriisi
     * @param b yhteenlaskettava matriisi
     *
     */
    public void plusToDestination(Matrix b, Matrix destination) {
    
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
            
                destination.set(i, j, get(i, j) + b.get(i, j));
                
            }
        }

    
    
    }
    
    /**
     * Metodi tulostaa matriisin.
     */
    public void print() {
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                
                System.out.print(get(i, j) + " ");
                
            }
            System.out.println("");
        
        }  
    
    }

    
}
