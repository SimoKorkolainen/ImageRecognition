/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.math;

import imagerecognition.util.ArrayUtil;

/**
 *
 * Vektor on vektorin kuvaamiseen käytetty luokka.
 * 
 */
public class Vector extends Matrix {

    /**
     * Konstruktori luo uuden vektorin.
     * @param vector vektori taulukkomuodossa
     */
    public Vector(double[] vector) {
        super(ArrayUtil.vectTo2D(vector));
    }
    /**
     *  Konstruktori luo uuden vektorin.
     * @param vector vektori 2D-taulukkomuodossa
     */
    public Vector(double[][] vector) {
        super(vector);
    }
    /**
     * 
     * @return vektorin koko
     */
    public int size() {
        return getRows();
    }
    
    /**
     * Metodi palauttaa vektorin komponentin i arvon.
     * @param i komponentti
     * @return arvo
     */
    public double get(int i) {
    
        return get(i, 0);
    
    }
    
    /**
     * Metodi yhdistää kaksi vektoria uudeksi vektoriksi.
     * Esim. kahdesta vektorista (1, 2) ja (3) tulee yhdistettynä 
     * (1, 2, 3).
     * @param a vektori
     * @param b vektori
     * @return ketjutettu vektori
     */
    public static Vector concatenate(Vector a, Vector b) {
        
        double c[] = new double[a.size() + b.size()];
        
        for (int i = 0; i < a.size(); i++) {
            
            c[i] = a.get(i);
        
        }
        
        for (int i = 0; i < b.size(); i++) {
            
            c[i + a.size()] = b.get(i);
        
        }
        
        return new Vector(c);
    
    }
    
    /**
     * Metodi palauttaa kahden vektorin pistetulon.
     * @param b vektori
     * @return pistetulo
     */
    public double dotProduct(Vector b) {
    
        return transpose().product(b).get(0, 0);
    
    }
    
    /**
     * Metodi laskee vektorin komponenttien summan.
     * @return summa
     */
    public double sum() {
        return super.columnSum().get(0, 0);
    }
    /**
     * Metodi asettaa vektorin komponentin i arvoksi halutun arvon.
     * @param i komponentti
     * @param value arvo
     */
    public void set(int i, double value) {
        super.set(i, 0, value);
    }
    
    /**
     * Metodi palauttaa vektorin kerrottuna skalaarilla.
     * @param mult skalaari
     * @return uusi vektori
     */
    @Override
    public Vector times(double mult) {
        return new Vector(super.times(mult).asArray());
    }
    
    /**
     * Metodi palauttaa nollavektorin.
     * @param n vektorin koko
     * @return nollavektori
     */
    public static Vector zero(int n) {
        return new Vector(Matrix.zeros(n, 1).asArray());
    }
    
    public Vector plus(Vector other) {
        return new Vector(super.plus(other).asArray());
    }
    
    public static Vector standardBasisVector(int n, int size) {
        Vector vect = Vector.zero(size);
        vect.set(n - 1, 1);
        
        return vect;
    }
    
}
