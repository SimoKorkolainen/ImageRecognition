/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.training;

import imagerecognition.util.Matrix;
import imagerecognition.util.Vector;

/**
 * LogLossErrorFunction mittaa luokitteluvirhettä.
 * @author Simo
 */
public class LogLossErrorFunction extends ErrorFunction {

    private static final double EPSILON = 1e-8; 
    
    public LogLossErrorFunction() {
    }
    
 
    /**
     * Virheen arvo pisteessä x.
     * @param x piste
     * @return arvo
     */
    @Override
    public Vector value(Vector x) {
        
        return new Vector(new double[]{logloss(x)});
    }
    
    
    private double logloss(Vector x) {
        return -Math.log(safeValue(x.dotProduct(getParameter())));
    }
    
    private double safeValue(double x) {
        
        return Math.max(x, EPSILON);
    
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
        Matrix j = Matrix.zeros(1, inputSize());
        
        for (int i = 0; i < inputSize(); i++) {
        
            j.set(0, i, -getParameter().get(i) / x.get(i));
            
        }
        return j;
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
