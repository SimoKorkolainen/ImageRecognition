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
public class ExponentialFunction extends ActivationFunction {

    public ExponentialFunction() {
        super("Exponential");
    }

    @Override
    public double value(double x) {
        return Math.exp(x);
    }

    @Override
    public double getDerivative(double x) {
        return value(x);
    }
    
}
