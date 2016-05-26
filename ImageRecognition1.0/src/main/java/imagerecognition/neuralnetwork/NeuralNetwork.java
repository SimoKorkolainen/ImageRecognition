/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork;

import imagerecognition.math.Vector;
import imagerecognition.neuralnetwork.layers.NetworkLayer;

/**
 *
 * Neuroverkon kuvaamiseen käytetty luokka.
 */
public class NeuralNetwork {
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
    }
    
    /**
     * Metodi asettaa neuroverkolle syötteen.
     * @param input syöte
     */
    public void setInput(double input[]) {
        layers[0].setValue(new Vector(input));
    }
    
    /**
     * Metodi päivittää neuroverkon ja palauttaa viimeisen kerroksen arvon.
     * @param input syöte
     * @return viimeisen kerroksen arvo
     */
    public Vector update(double input[]) {
        setInput(input);
        
        for (int i = 1; i < layers.length; i++) {
            layers[i].updateLayer(layers[i - 1]);
        }
        
        return layers[layers.length - 1].getValue();
    
    }
}
