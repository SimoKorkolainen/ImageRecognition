/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions;

import imagerecognition.functions.ActivationFunction;

/**
 *
 * @author Simo
 */
public class LayerFunction {
    ActivationFunction function;
    private double weights[];

    public LayerFunction(ActivationFunction function, double[] weights) {
        this.function = function;
        this.weights = weights;
    }
    
    public double value(double[] input) {
        double activation = 0;
        for (int i = 0; i < weights.length; i++) {
            activation = weights[i] * input[i];
        }
        
        return function.value(activation);
    }
    
}
