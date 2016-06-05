/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions.general;

import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;
import imagerecognition.functions.activation.ExponentialFunction;
import imagerecognition.neuralnetwork.layers.NetworkLayer;

/**
 * Softmax-funktiota kÃ¤ytettÃ¤Ã¤n luokitteluun. Softmax-funktio on yleensÃ¤ 
 * luokitteluun kÃ¤ytettÃ¤vÃ¤n neuroverkon viimeinen kerros.
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
    @Override
    public void train(double learningRate) {
        for (int i = 0; i < outputSize(); i++) {
        
            trainFunction(i, learningRate);
        
        }
    }
    

    public void trainFunction(int i, double learningRate) {

        Matrix outputErrorGradient = getLayer().getOutputErrorGradient();
        
        int layerN = getLayer().getLayerNumber();
        
        NetworkLayer previous = getLayer().getNetwork().getPreviousLayer(layerN);
        
        Vector input = previous.getValue();
        
        Vector superValue = super.value(input);
        
        NeuronFunction function = getFunctions()[i];
        
        Matrix jacob = function.parameterJacobian(input);
        
                
        Matrix parameterErrorGradient = outputErrorGradient.product(jacob);
        
        Matrix diff = parameterErrorGradient.transpose().times(-learningRate);
        
        Vector TrainedParameter = new Vector(function.getParameter().plus(diff).asArray());
    
        function.setParameter(TrainedParameter);
    }
    
    
    
    
    public Matrix getFunctionTrainingJacobian(int i) {
        
                
        int layerN = getLayer().getLayerNumber();
        
        NetworkLayer previous = getLayer().getNetwork().getPreviousLayer(layerN);
        
        Vector input = previous.getValue();
        
        NeuronFunction function = getFunctions()[i];
        
        
        
        Matrix parJacob = Matrix.zeros(function.getParameter().size(), super.outputSize());
        
        for (int j = 0; j < super.outputSize(); j++) {
            
            Matrix jacob = function.parameterJacobian(input);

            for (int k = 0; k < super.outputSize(); k++) {
            
            
            }
        
        }
        
        return parJacob;
    }
    
}
