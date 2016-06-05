/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork;

import imagerecognition.functions.error.ErrorFunction;
import imagerecognition.math.Matrix;
import imagerecognition.math.Vector;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
import imagerecognition.training.Trainable;

/**
 *
 * Neuroverkon kuvaamiseen käytetty luokka.
 */
public class NeuralNetwork implements Trainable {
    private ErrorFunction errorFunction;
    private NetworkLayer[] layers;

    public NeuralNetwork(int layerN, int inputLayerSize) {
        layers = new NetworkLayer[layerN];
        layers[0] = new NetworkLayer(inputLayerSize); //The first layer is the input layer
    }
    
    /**
     * Metodi asettaa kerroksen neuroverkkoon.
     * @param layerN kerroksen numero
     * @param layer kerros
     */
    public void setLayer(int layerN, NetworkLayer layer) {
        layers[layerN] = layer;
        layer.initialize(this, layerN);
    }
    

    
    /**
     * Metodi asettaa neuroverkolle syötteen.
     * @param input syöte
     */
    public void setInput(Vector input) {
        layers[0].setValue(input);
    }
    

    
    /**
     * Metodi päivittää neuroverkon ja palauttaa viimeisen kerroksen arvon.
     * @param input syöte
     * @return viimeisen kerroksen arvo
     */
    public Vector updateValues(Vector input) {
        setInput(input);
        
        for (int i = 1; i < layers.length; i++) {
            layers[i].updateLayer();
        }
        
        return layers[layers.length - 1].getValue();
    
    }
    
    public void updateGradients(Vector targetOutput) {

        updateOutputLayerGradients(targetOutput);
        
        for (int i = layers.length - 2; i > 0; i--) {
            layers[i].updateOutputErrorGradient();
        }
        
    
    }

    @Override
    public void train(double learningRate) {
        for (int i = 1; i < layers.length; i++) {
            layers[i].train(learningRate);
        }
    }

    
    public NetworkLayer getPreviousLayer(int layerN) {
        return layers[layerN - 1];
    }
    public NetworkLayer getNextLayer(int layerN) {
        return layers[layerN + 1];
    }
    public NetworkLayer getLayer(int layerN) {
        return layers[layerN];
    }

    public void setErrorFunction(ErrorFunction errorFunction) {
        this.errorFunction = errorFunction;
    }
    
    
    
    private void updateOutputLayerGradients(Vector targetOutput) {
        
        errorFunction.setParameter(targetOutput);
        NetworkLayer outputLayer = layers[layers.length - 1];
        
        Matrix jacob = errorFunction.jacobian(outputLayer.getValue());
        
        layers[layers.length - 1].setOutputErrorGradient(new Vector(jacob.asArray()));
    
    }
}
