/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.neuralnetwork.activation;

import imagerecognition.neuralnetwork.activation.ActivationFunction;

/**
 * Exponenttifunktio
 */
public class ExponentialFunction extends ActivationFunction {

    /**
     * Eksponenttifunktion luova konstruktori.
     */
    public ExponentialFunction() {
        super("Exponential");
    }
    
    
    /**
     * Metodi palauttaa luvun e ^ x pisteessä x.
     * @param x reaalilukupiste
     * @return e ^ x
     */
    @Override
    public double value(double x) {
        return Math.exp(x);
    }

    /**
     * Metodi palauttaa eksponenttifunktion derivaatan pisteessä.
     * @param x piste
     * @return derivaatta
     */
    @Override
    public double getDerivative(double x) {
        return value(x);
    }
    
}
