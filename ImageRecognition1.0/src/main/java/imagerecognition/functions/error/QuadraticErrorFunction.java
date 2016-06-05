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
 * @author Simo
 */
public class QuadraticErrorFunction extends ErrorFunction {

    @Override
    public Vector value(Vector x) {
        
        return new Vector(new double[]{quadraticError(x)});
    }
    
    public double quadraticError(Vector x) {
        Vector diff = getParameter().plus(x.times(-1));
        return diff.dotProduct(diff) / 2;
    }
    


    @Override
    public int inputSize() {
        return getParameter().size();
    }

    @Override
    public int outputSize() {
        return 1;
    }

    @Override
    public Matrix jacobian(Vector x) {
        return x.plus(getParameter().times(-1)).transpose();
    }

    @Override
    public Matrix parameterJacobian(Vector x) {
        return Matrix.zeros(0, 0);
    }  
}
