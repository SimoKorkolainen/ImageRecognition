/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions.error;

import imagerecognition.math.Matrix;
import imagerecognition.math.Vector;


public class LogLossErrorFunction extends ErrorFunction {

    private static final double EPSILON = 1e-8; 
    
    public LogLossErrorFunction() {
    }
    
 

    @Override
    public Vector value(Vector x) {
        
        return new Vector(new double[]{logloss(x)});
    }
    
    public double logloss(Vector x) {
        return Math.log(safeValue(x.dotProduct(getParameter())));
    }
    
    public double safeValue(double x) {
        
        return Math.max(x, EPSILON);
    
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
        Matrix j = Matrix.zeros(1, inputSize());
        
        for (int i = 0; i < inputSize(); i++) {
        
            j.set(0, i, getParameter().get(i) / x.get(i));
            
        }
        return j;
    }

    @Override
    public Matrix parameterJacobian(Vector x) {
        return Matrix.zeros(0, 0);
    }
    
}
