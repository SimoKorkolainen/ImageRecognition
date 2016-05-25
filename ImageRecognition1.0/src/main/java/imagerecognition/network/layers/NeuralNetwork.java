/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.network.layers;

import imagerecognition.functions.Vector;

/**
 *
 * @author Simo
 */
public class NeuralNetwork {
    private NetworkLayer[] layers;

    public NeuralNetwork(int layerN, int inputLayerSize) {
        layers = new NetworkLayer[layerN];
        layers[0] = new NetworkLayer(inputLayerSize); //The first layer is the input layer
    }
    
    public void setLayer(int layerN, NetworkLayer layer) {
        layers[layerN] = layer;
    }
    
    
    public void setInput(double input[]) {
        layers[0].setValue(new Vector(input));
    }
    
    public void update(double input[]) {
        setInput(input);
        
        for (int i = 1; i < layers.length; i++) {
            layers[i].updateLayer(layers[i - 1]);
        }
    
    }
}
