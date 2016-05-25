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
public class NeuronFunction extends ParametricFunction {

    private ActivationFunction activation;
    
    public NeuronFunction(ActivationFunction activation, double weigthRandomness, int inputSize) {
        
        this.activation = activation;
        
        Vector p = new Vector(ArrayUtil.random(-weigthRandomness, weigthRandomness, inputSize));
        
        super.setParameter(p);
    
    }
    
    @Override
    public Vector value(Vector x) {

        return new Vector(new double[] {doubleValue(x)});
    
    }
    
    public double doubleValue(Vector x) {
        return activation.value(x.dotProduct(getParameter()));
    }

    @Override
    public Matrix jacobian(Vector x) {
        double dx = activation.getDerivative(x.dotProduct(getParameter()));
        return getParameter().transpose().times(dx);
    }

    @Override
    public Matrix parameterJacobian(Vector x) {
        double dx = activation.getDerivative(x.dotProduct(getParameter()));
        return x.transpose().times(dx);
    }

    @Override
    public int inputSize() {
        return getParameter().size();
    }

    @Override
    public int outputSize() {
        return 1;
    }
    
    
    
}
