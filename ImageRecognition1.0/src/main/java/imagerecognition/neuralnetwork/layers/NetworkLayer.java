/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.layers;

import imagerecognition.functions.general.NeuronLayerFunction;
import imagerecognition.math.Vector;

/**
 *
 * NetworkLayer on neuroverkon kerrosta kuvaava luokka.
 */
public class NetworkLayer {
    private NeuronLayerFunction function;
    private Vector value;

    /**
     * Syötekerroksen konstruktori.
     * @param size kerroksen koko
     */
    public NetworkLayer(int size) {
        value = Vector.zero(size);
    }
    
    /**
     * Normaalin kerroksen konstruktori.
     * @param function koko kerroksessa käytetty funktio
     */
    public NetworkLayer(NeuronLayerFunction function) {
        this(function.outputSize());
        this.function = function;
    }
    
    
    /**
     * Metodi päivittää kerroksen.
     * @param input syötekerros
     */
    public void updateLayer(NetworkLayer input) {
        if (function == null) {
            return;
        }
        
        value = function.value(input.getValue());
    }

    public Vector getValue() {
        return value;
    }

    public void setValue(Vector value) {
        this.value = value;
    }

    
}
