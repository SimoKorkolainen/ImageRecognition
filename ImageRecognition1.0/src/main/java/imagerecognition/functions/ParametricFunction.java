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
public abstract class ParametricFunction extends VectorFunction implements Differentiable, ParametricDifferentiable {
    
    
    private Vector parameter;
    
    public Vector getParameter() {
        return parameter;
    }
    
    
    public void setParameter(Vector parameter) {
    
        this.parameter = parameter;
    
    }
    
    
}
