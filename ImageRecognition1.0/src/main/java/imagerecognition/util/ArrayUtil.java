/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.util;

/**
 *
 * ArrayUtil on taulukkojen käsittelyyn tarkoitettu luokka
 */
public class ArrayUtil {
    
    /**
     * Metodi palauttaa 2D-version 1D-taulukosta.
     * @param vector 1D-taulukko
     * @return 2D-taulukko
     */
    public static double[][] vectTo2D(double[] vector) {
        
        double a[][] = new double[vector.length][1];
        
        for (int i = 0; i < vector.length; i++) {
            a[i][0] = vector[i];
        }
        
        return a;
        
    }
    
    /**
     * Metodi palauttaa satunnaisia lukuja tasajakaumasta [min, max] sisältävän taulukon.
     * @param min minimi
     * @param max maksimi
     * @param size taulukon koko
     * @return satunnaistaulukko
     */
    public static double[] random(double min, double max, int size) {
        
        double[] res = new double[size];
        
        for (int i = 0; i < size; i++) {
        
            res[i] = min + Math.random() * (max - min);
        
        }
        
        return res;
    
    }
    
    /**
     * Metodi muuttaa 3D-taulukon 1D taulukoksi.
     * @param a muunnettava taulukko
     * @return muunnettu taulukko
     */
    public static int[] reshape3DTo1D(int[][][] a) {
        
        int b[] = new int[a.length * a[0].length * a[0][0].length];
        int ind = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = 0; k <  a[0][0].length; k++) {
                    b[ind] = a[i][j][k];
                    ind++;
                }
                
                
            }
        
        }
        
        return b;
    
    
    }
    /**
     * Metodi muuttaa int-taulukon double-taulukoksi.
     * @param a muunnettava taulukko
     * @return muunnettu taulukko
     */
    public static double[] int1DToDouble1D(int[] a) {
        
        double[] b = new double[a.length];
        
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        
        return b;
    
    }

}
