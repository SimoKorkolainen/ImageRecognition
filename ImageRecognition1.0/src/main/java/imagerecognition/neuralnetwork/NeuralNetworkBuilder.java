/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork;

import imagerecognition.neuralnetwork.layers.NetworkLayer;
import imagerecognition.neuralnetwork.layers.NeuronLayerFunction;
import imagerecognition.neuralnetwork.layers.SoftmaxFunction;
import imagerecognition.neuralnetwork.activation.ActivationFunction;
import imagerecognition.neuralnetwork.activation.SoftplusFunction;
import imagerecognition.neuralnetwork.training.LogLossErrorFunction;

/**
 *
 * NeuralNetworkBuilder on neuroverkkoja rakentava luokka.
 */
public class NeuralNetworkBuilder {

    
    /**
     * Metodi palauttaa neuroverkon, joka koostuu vain syötekerroksesta ja 
     * softmax-kerroksesta.
     * @param inputSize syötteen koko
     * @param outputSize tulosteen koko
     * @param weightRandomness painojen alustussatunnaisuus
     * @return neuroverkko
     */
    public static NeuralNetwork onlySoftmax(int inputSize, int outputSize, double weightRandomness) {
        
        NeuralNetwork net = new NeuralNetwork(2, inputSize);
        
        net.setLayer(1, new NetworkLayer(new SoftmaxFunction(weightRandomness, inputSize, outputSize)));
        
        net.setErrorFunction(new LogLossErrorFunction());
        
        return net;
    }
    
    
    /**
     * Metodi palauttaa neuroverkon, joka koostuu syötekerroksesta,
     * softplus-kerroksesta ja softmax-kerroksesta.
     * @param inputSize syötteen koko
     * @param hiddenLayerSize softplus-kerroksen koko
     * @param outputSize tulosteen koko
     * @param weightRandomness painojen alustussatunnaisuus
     * @return neuroverkko
     */
    public static NeuralNetwork softplusAndSoftmax(int inputSize, int hiddenLayerSize, int outputSize, double weightRandomness) {
        
        NeuralNetwork net = new NeuralNetwork(3, inputSize);
        
        net.setLayer(1, new NetworkLayer(new NeuronLayerFunction(new SoftplusFunction(), weightRandomness, inputSize, hiddenLayerSize)));
        net.setLayer(2, new NetworkLayer(new SoftmaxFunction(weightRandomness, hiddenLayerSize, outputSize)));
        
        net.setErrorFunction(new LogLossErrorFunction());
        
        return net;
    }
    
    /**
     * Metodi palauttaa luokitteluun tarkoitetun neuroverkon.
     * @param inputSize syötteen koko
     * @param hiddenLayers piilokerrosten koko
     * @param outputSize tulosteen koko
     * @param weightRandomness painojen alustussatunnaisuus
     * @param activation aktivaatiofunktion
     * @return neuroverkko
     */
    public static NeuralNetwork createClassificationNetwork(int inputSize, int hiddenLayers[], int outputSize, double weightRandomness, ActivationFunction activation) {
        
        NeuralNetwork net = new NeuralNetwork(hiddenLayers.length + 2, inputSize);
        if (hiddenLayers.length > 0) {
            net.setLayer(1, new NetworkLayer(new NeuronLayerFunction(activation, weightRandomness, inputSize, hiddenLayers[0])));
        }
        for (int i = 2; i <= hiddenLayers.length; i++) {
            net.setLayer(i, new NetworkLayer(new NeuronLayerFunction(activation, weightRandomness, hiddenLayers[i - 2], hiddenLayers[i - 1])));
        }

        net.setLayer(hiddenLayers.length + 1, new NetworkLayer(new SoftmaxFunction(weightRandomness, hiddenLayers[hiddenLayers.length - 1], outputSize)));
        
        net.setErrorFunction(new LogLossErrorFunction());
        
        return net;
    }
    
}
