/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions.error;

import imagerecognition.math.Matrix;
import imagerecognition.math.Vector;

/**
 *
 * QuadraticErrorFunction mittaa neliösummavirhettä.
 */
public class QuadraticErrorFunction extends ErrorFunction {
    /**
     * Virheen arvo pisteessä x.
     * @param x piste
     * @return arvo
     */
    @Override
    public Vector value(Vector x) {
        
        return new Vector(new double[]{quadraticError(x)});
    }
    
    private double quadraticError(Vector x) {
        Vector diff = getParameter().plus(x.times(-1));
        return diff.dotProduct(diff) / 2;
    }
    

    /**
     * Metodi palauttaa syötteen koon
     * @return syötteen koko
     */
    @Override
    public int inputSize() {
        return getParameter().size();
    }
    /**
     * Metodi palauttaa tulosteen koon
     * @return syötteen koko
     */
    @Override
    public int outputSize() {
        return 1;
    }
    /**
     * Metodi palauttaa virheen Jakobiaanin pisteessä x.
     * @param x piste
     * @return Jakobiaani
     */
    @Override
    public Matrix jacobian(Vector x) {
        return x.plus(getParameter().times(-1)).transpose();
    }
    /**
     * Metodi palauttaa 0x0 matriisin
     * @param x piste 
     * @return 0x0 matriisi
     */
    @Override
    public Matrix parameterJacobian(Vector x) {
        return Matrix.zeros(0, 0);
    }  
}
