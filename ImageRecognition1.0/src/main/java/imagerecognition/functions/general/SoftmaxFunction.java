/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions.general;

import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;
import imagerecognition.functions.activation.ExponentialFunction;

/**
 * Softmax-funktiota käytettään luokitteluun. Softmax-funktio on yleensä 
 * luokitteluun käytettävän neuroverkon viimeinen kerros.
 * Softmax-funktion kuva-alkion komponenttien arvoden summa on yksi 
 * ja kaikkien komponenttien arvot ovat positiivia.
 */
public class SoftmaxFunction extends NeuronLayerFunction {

    
    
    public SoftmaxFunction(double weightRandomness, int inputSize, int outputSize) {
        super(new ExponentialFunction(), weightRandomness, inputSize, outputSize);
        
    
    }
    
    public SoftmaxFunction(Vector weights[]) {
        super(new ExponentialFunction(), weights);
        
    
    }
    
    @Override
    public Vector value(Vector x) {
    
        Vector value = super.value(x);
        
        return value.times(1.0 / value.sum());
    
    }
    
    @Override
    public Matrix jacobian(Vector x) {
        
        Vector superValue = super.value(x);
        Vector value = value(x);
        
        
        Matrix dy = super.jacobian(x);
        
       
        
        Matrix dySum = dy.columnSum();
        
        
        Matrix prod = value.product(dySum);
        
        prod = prod.times(-1);

        
        
        Matrix sum = prod.plus(dy);
        
        
        return sum.times(1.0 / superValue.sum()); //not ready?
    
    }
    

    
}
