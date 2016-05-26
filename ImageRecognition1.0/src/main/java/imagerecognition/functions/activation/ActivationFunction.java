/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions.activation;

import imagerecognition.functions.activation.SimpleDifferentiable;

/**
 *
 * Neuronin aktivaatiofunktion abstrahoimiseen käytetty luokka.
 * Aktivaatiofunktio on kuvaus R -> R.
 */
public abstract class ActivationFunction implements SimpleDifferentiable {
    private String name;

    /**
     * Aktivaatiofunktion konstruktori.
     * @param name funktion nimi
     */
    public ActivationFunction(String name) {
        this.name = name;
    }
    
    public abstract double value(double x);
    
    public String getName() {
        return name;
    }
}
