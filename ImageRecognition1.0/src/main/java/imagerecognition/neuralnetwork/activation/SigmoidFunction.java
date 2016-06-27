/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.activation;

/**
 *
 * @author Simo
 */
public class SigmoidFunction extends ActivationFunction {

    public SigmoidFunction() {
        super("Sigmoid");
    }

    /**
     * Metodi palauttaa funktion arvon pisteessä
     * @param x piste
     * @return arvo pisteessä
     */
    @Override
    public double value(double x) {
        return 1.0 / (Math.exp(-x) + 1);
    }
    /**
     * Metodi palauttaa funktion derivaatan pisteessä
     * @param x piste
     * @return derivaatta pisteessä
     */
    @Override
    public double getDerivative(double x) {
        double value = value(x);
        return value * (1 - value);
    }
    
}
