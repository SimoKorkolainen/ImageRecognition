/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.activation;

import imagerecognition.neuralnetwork.activation.IdentityFunction;
import imagerecognition.neuralnetwork.activation.ActivationFunction;
import imagerecognition.neuralnetwork.activation.SigmoidFunction;
import imagerecognition.neuralnetwork.activation.SoftplusFunction;

/**
 *
 * Funktioiden luomiseen tarkoitettu luokka.
 */
public class FunctionMaker {
    
    /**
     * Metodi palauttaa nimeä vastaavan funktion.
     * @param functionName funktion nimi
     * @return nimeä vastaava funktio
     */
    public static ActivationFunction make(String functionName) {
        switch (functionName.toLowerCase()) {
            case "softplus": return new SoftplusFunction();
            case "sigmoid": return new SigmoidFunction();
            case "exponential": return new ExponentialFunction();
            default: return new IdentityFunction();
        }
    }
}
