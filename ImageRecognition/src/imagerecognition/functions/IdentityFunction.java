/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions;

/**
 *
 * @author Simo
 */
public class IdentityFunction extends ActivationFunction {

    public IdentityFunction() {
        super("Identity");
    }

    @Override
    public double value(double x) {
        return x;
    }

    @Override
    public double getDerivative(double x) {
        return 1;
    }
    
}
