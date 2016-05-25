/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.network.layers;

import imagerecognition.functions.ActivationFunction;
import imagerecognition.functions.NeuronLayerFunction;
import imagerecognition.functions.Vector;

/**
 *
 * @author Simo
 */
public class NetworkLayer {
    private NeuronLayerFunction function;
    private Vector value;

    public NetworkLayer(int size) {
        value = Vector.zero(size);
    }
    
    
    public NetworkLayer(NeuronLayerFunction function) {
        this(function.outputSize());
        this.function = function;
    }
    
    
    
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
