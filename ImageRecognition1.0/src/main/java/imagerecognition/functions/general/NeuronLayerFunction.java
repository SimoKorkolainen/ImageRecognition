/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions.general;

import imagerecognition.functions.differentiation.Differentiable;
import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;
import imagerecognition.functions.activation.ActivationFunction;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
import imagerecognition.training.Trainable;

/**
 *
 * NeuronLayerFunction luokka kuvaa neuroverkon kerrosten välistä funktiota.
 */
public class NeuronLayerFunction extends VectorFunction implements Differentiable, Trainable {
    
    private NetworkLayer layer;
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
    
    public NeuronLayerFunction(ActivationFunction function, Vector[] weights) {
        int outputSize = weights.length;
        this.functions = new NeuronFunction[outputSize]; 
        
        for (int i = 0; i < outputSize; i++) {
        
            functions[i] = new NeuronFunction(function, weights[i]);
        
        }
        
    
    }
    
    
    /**
     * Funktion arvo pisteessä x.
     * @param x piste
     * @return arvo
     */
    @Override
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
            
                jacob.set(i, k, jacobRow.get(0, k));
            
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

    @Override
    public void train(double learningRate) {
        for (int i = 0; i < outputSize(); i++) {
        
            trainFunction(i, learningRate);
        
        }
    }
    
    
    public void trainFunction(int i, double learningRate) {
    
        Matrix outputErrorGradient = layer.getOutputErrorGradient();
        
        int layerN = layer.getLayerNumber();
        
        NetworkLayer previous = layer.getNetwork().getPreviousLayer(layerN);
        
        Vector input = previous.getValue();
        
        NeuronFunction function = functions[i];
        
        Matrix jakob = function.parameterJacobian(input);
        
        Matrix parameterErrorGradient = jakob.times(outputErrorGradient.get(0, i));
        
        Matrix diff = parameterErrorGradient.transpose().times(-learningRate);
        
        Vector TrainedParameter = new Vector(function.getParameter().plus(diff).asArray());
    
        function.setParameter(TrainedParameter);
    }

    public void setLayer(NetworkLayer layer) {
        this.layer = layer;
    }

    public NetworkLayer getLayer() {
        return layer;
    }

    public NeuronFunction[] getFunctions() {
        return functions;
    }

    
    
    
    
}
