/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.network.layers;

import imagerecognition.functions.LayerFunction;
import imagerecognition.functions.ActivationFunction;

/**
 *
 * @author Simo
 */
public abstract class NetworkLayer {
    private LayerFunction[] functions;
    private double values[];

    public NetworkLayer(int size) {
        functions = new LayerFunction[size];
        values = new double[size];
    }
    
    public void updateLayer(NetworkLayer input) {
        double in[] = input.getValues();
        for (int i = 0; i < functions.length; i++) {
            values[i] = functions[i].value(in);
        }
    
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    
    
    
}
