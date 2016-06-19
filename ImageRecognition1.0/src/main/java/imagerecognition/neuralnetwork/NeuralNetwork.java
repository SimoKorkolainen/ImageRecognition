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

    /**
     * Konstruktori luo neuroverkon ilman alustettuja kerroksia.
     * @param layerN kerrosten lukumäärä
     * @param inputLayerSize neuroverkon syötteen koko
     */
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
        layer.setNeuralNetwork(this, layerN);
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
    
    
    /**
     * Metodi päivittää neuroverkon virhegradientit suhteessa kerrosten
     * tulosteisiin.
     * @param targetOutput neuroverkon tavoiteltu tuloste. 
     */
    public void updateGradients(Vector targetOutput) {

        updateOutputLayerGradients(targetOutput);
        
        for (int i = layers.length - 2; i > 0; i--) {
            layers[i].updateOutputErrorGradient();
        }
        
    
    }

    /**
     * Metodi päivittää neuroverkon painot yrittäen minimoida tehdyn virheen.
     * @param learningRate oppimisnopeuteen vaikuttava parametri.
     */
    @Override
    public void train(double learningRate) {
        for (int i = 1; i < layers.length; i++) {
            layers[i].train(learningRate);
        }
    }

    /**
     * Metodi palauttaa kerrosta edeltävän kerroksen;
     * @param layerN neuroverkon kerrosksen numero
     * @return edellinen kerros
     */
    public NetworkLayer getPreviousLayer(int layerN) {
        return layers[layerN - 1];
    }
    
    /**
     * Metodi palauttaa kerrosta seuraavan kerroksen;
     * @param layerN neuroverkon kerrosksen numero
     * @return seuraava kerros
     */
    public NetworkLayer getNextLayer(int layerN) {
        return layers[layerN + 1];
    }
    
    /**
     * Metodi palauttaa neuroverkon kerroksen;
     * @param layerN neuroverkon kerroksen numero
     * @return seuraava kerros
     */
    public NetworkLayer getLayer(int layerN) {
        return layers[layerN];
    }

    public void setErrorFunction(ErrorFunction errorFunction) {
        this.errorFunction = errorFunction;
    }
    
    
    /**
     * 
     * @param targetOutput 
     */
    private void updateOutputLayerGradients(Vector targetOutput) {
        
        errorFunction.setParameter(targetOutput);
        NetworkLayer outputLayer = layers[layers.length - 1];
        
        Matrix jacob = errorFunction.jacobian(outputLayer.getValue());
        
        layers[layers.length - 1].setOutputErrorGradient(new Vector(jacob.asArray()));
    
    }

    public ErrorFunction getErrorFunction() {
        return errorFunction;
    }
    
    public int getNumberOfLayer() {
        return layers.length;
    }
}
