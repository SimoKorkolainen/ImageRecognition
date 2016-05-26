/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions.activation;

import imagerecognition.functions.activation.ActivationFunction;

/**
 *
 * Identiteettifunktio.
 */
public class IdentityFunction extends ActivationFunction {

    /**
     * Identiteettifunktion konstruktori.
     */
    public IdentityFunction() {
        super("Identity");
    }
    
    
    /**
     * Metodi palauttaa funktion arvon pisteessä
     * @param x piste
     * @return arvo pisteessä
     */
    @Override
    public double value(double x) {
        return x;
    }
    
    /**
     * Metodi palauttaa funktion derivaatan pisteessä
     * @param x piste
     * @return derivaatta pisteessä
     */
    @Override
    public double getDerivative(double x) {
        return 1;
    }
    
}
