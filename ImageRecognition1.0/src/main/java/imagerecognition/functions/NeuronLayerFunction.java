/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

import imagerecognition.functions.differentiation.Differentiable;
import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;
import imagerecognition.functions.activation.ActivationFunction;

/**
 *
 * NeuronLayerFunction luokka kuvaa neuroverkon kerrosten välistä funktiota.
 */
public class NeuronLayerFunction extends VectorFunction implements Differentiable {
    
    
    private NeuronFunction[] functions;
    
    /**
     * Funktion luova konstruktori.
     * @param function aktivaatiofunktio
     * @param weightRandomness parametripainojen satunnaisuus
     * @param inputSize syötteen dimensio
     * @param outputSize tulosteen dimensio
     */
    public NeuronLayerFunction(ActivationFunction function, double weightRandomness, int inputSize, int outputSize) {
        
        this.functions = new NeuronFunction[outputSize]; 
        
        for (int i = 0; i < outputSize; i++) {
        
            functions[i] = new NeuronFunction(function, weightRandomness, inputSize);
        
        }
        
    
    }
    
    
    /**
     * Funktion arvo pisteessä x.
     * @param x piste
     * @return arvo
     */
    public Vector value(Vector x) {
    
        Vector value = Vector.zero(outputSize());
        
        for (int i = 0; i < value.size(); i++) {
        
            value.set(i, functions[i].doubleValue(x));
            
        }
        
        return value;
    
    }
    
    /**
     * Funktion Jakobiaani syötteiden suhteen pisteessä x.
     * @param x piste x
     * @return Jakobiaani
     */
    @Override
    public Matrix jacobian(Vector x) {
    
        
        Matrix jacob = Matrix.zeros(outputSize(), x.size());
        
        for (int i = 0; i < outputSize(); i++) {
        
            Matrix jacobRow = functions[i].jacobian(x);
            
            for (int k = 0; k < jacobRow.getCols(); k++) {
            
                jacob.set(i, k, jacobRow.get(1, k));
            
            }
        
        }
        
        return jacob;
    
    }

    /**
     * 
     * @return syötteen dimensio
     */
    @Override
    public int inputSize() {
        return functions[0].inputSize();
    }

    /**
     * 
     * @return tulosteen dimensio
     */
    @Override
    public int outputSize() {
        return functions.length;
    }

    
    
    
    
}
