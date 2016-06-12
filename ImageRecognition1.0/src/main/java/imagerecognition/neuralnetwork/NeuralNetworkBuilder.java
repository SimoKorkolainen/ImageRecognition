/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork;

import imagerecognition.functions.activation.ActivationFunction;
import imagerecognition.functions.activation.SoftplusFunction;
import imagerecognition.functions.error.LogLossErrorFunction;
import imagerecognition.functions.general.NeuronLayerFunction;
import imagerecognition.functions.general.SoftmaxFunction;
import imagerecognition.neuralnetwork.layers.NetworkLayer;

/**
 *
 * @author Simo
 */
public class NeuralNetworkBuilder {

    
    
    public static NeuralNetwork onlySoftmax(int inputSize, int outputSize, double weightRandomness) {
        
        NeuralNetwork net = new NeuralNetwork(2, inputSize);
        
        net.setLayer(1, new NetworkLayer(new SoftmaxFunction(weightRandomness, inputSize, outputSize)));
        
        net.setErrorFunction(new LogLossErrorFunction());
        
        return net;
    }
    
    
    public static NeuralNetwork softplusAndSoftmax(int inputSize, int hiddenLayerSize, int outputSize, double weightRandomness) {
        
        NeuralNetwork net = new NeuralNetwork(3, inputSize);
        
        net.setLayer(1, new NetworkLayer(new NeuronLayerFunction(new SoftplusFunction(), weightRandomness, inputSize, hiddenLayerSize)));
        net.setLayer(2, new NetworkLayer(new SoftmaxFunction(weightRandomness, hiddenLayerSize, outputSize)));
        
        net.setErrorFunction(new LogLossErrorFunction());
        
        return net;
    }
}
