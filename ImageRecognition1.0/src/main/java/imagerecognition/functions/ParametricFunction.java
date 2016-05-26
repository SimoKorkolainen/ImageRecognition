/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

import imagerecognition.functions.differentiation.ParametricDifferentiable;
import imagerecognition.functions.differentiation.Differentiable;
import imagerecognition.math.Vector;

/**
 *
 * ParametricFunction määrittelee abstraktin funktion, jolla on parametreja.
 * Funktiota on mahdollista derivoida sekä parametrien, että syötteiden 
 * suhteen.
 */
public abstract class ParametricFunction extends VectorFunction implements Differentiable, ParametricDifferentiable {
    
    
    private Vector parameter;
    
    public Vector getParameter() {
        return parameter;
    }
    
    
    public void setParameter(Vector parameter) {
    
        this.parameter = parameter;
    
    }
    
    
}
