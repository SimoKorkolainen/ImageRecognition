/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

/**
 *
 * @author simokork
 */
public class NeuronLayerFunction extends VectorFunction implements  Differentiable {
    
    
    private NeuronFunction[] functions;
    
    
    public NeuronLayerFunction(ActivationFunction function, double weightRandomness, int inputSize, int outputSize) {
        
        this.functions = new NeuronFunction[outputSize]; 
        
        for (int i = 0; i < outputSize; i++) {
        
            functions[i] = new NeuronFunction(function, weightRandomness, inputSize);
        
        }
        
    
    }
    
    
    
    public Vector value(Vector x) {
    
        Vector value = Vector.zero(outputSize());
        
        for (int i = 0; i < value.size(); i++) {
        
            value.set(i, functions[i].doubleValue(x));
            
        }
        
        return value;
    
    }
    
    
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

    @Override
    public int inputSize() {
        return functions[0].inputSize();
    }

    @Override
    public int outputSize() {
        return functions.length;
    }

    
    
    
    
}
