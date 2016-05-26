/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions.differentiation;

import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;

/**
 *
 * Rajapinta, joka mahdollistaa funktion derivoimisen parametrien suhteen.
 */
public interface ParametricDifferentiable {
    
    /**
     * Metodi palautaa Jakobiaanin parametrien suhteen pisteessä x.
     * @param x piste
     * @return Jakobiaani
     */
    public Matrix parameterJacobian(Vector x);
}
