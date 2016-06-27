/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.neuralnetwork.layers;

import imagerecognition.neuralnetwork.NeuronFunction;
import imagerecognition.neuralnetwork.NeuronFunction;
import imagerecognition.util.Vector;
import imagerecognition.util.Matrix;
import imagerecognition.neuralnetwork.activation.ActivationFunction;
import imagerecognition.neuralnetwork.training.Trainable;

/**
 *
 * NeuronLayerFunction luokka kuvaa neuroverkon kerrosten välistä funktiota.
 */
public class NeuronLayerFunction extends VectorFunction implements Differentiable, Trainable {
    private boolean optimized;
    private NetworkLayer layer;
    private NeuronFunction[] functions;
    private Matrix jacobianMemory;
    private Vector valueMemory;
    private Matrix gradientMemory;
    private Matrix gradientTransposeMemory;
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
        
        this.jacobianMemory = Matrix.zeros(outputSize(), inputSize);
        this.valueMemory = Vector.zero(outputSize());
        this.gradientMemory = Matrix.zeros(1, inputSize());
        this.gradientTransposeMemory = Matrix.zeros(inputSize(), 1);
        this.optimized = true;
    }
    
    
    /**
     * Metodi luo neuroverkon kerrosta kuvaavan funktion,
     * jonka tulosteena on syöteen pistetulot painovektorien kanssa operoituna
     * aktivaatiofunktiolla.
     * @param function aktivaatiofunktio
     * @param weights painot taulukossa
     */
    public NeuronLayerFunction(ActivationFunction function, Vector[] weights) {
        int outputSize = weights.length;
        this.functions = new NeuronFunction[outputSize]; 
        
        for (int i = 0; i < outputSize; i++) {
        
            functions[i] = new NeuronFunction(function, weights[i]);
        
        }
        this.jacobianMemory = Matrix.zeros(outputSize, weights[0].size());
        this.valueMemory = Vector.zero(outputSize());
        this.gradientMemory = Matrix.zeros(1, inputSize());
        this.gradientTransposeMemory = Matrix.zeros(inputSize(), 1);
        this.optimized = true;
    }
    
    
    /**
     * Funktion arvo pisteessä x.
     * @param x piste
     * @return arvo
     */
    @Override
    public Vector value(Vector x) {
 
        if (optimized) {
            return optimizedValue(x);
        }
        return unoptimizedValue(x);
    }
    
    private Vector optimizedValue(Vector x) {
        for (int i = 0; i < valueMemory.size(); i++) {

            valueMemory.set(i, functions[i].doubleValue(x));
            
        }
        
        return valueMemory;
    }
    
    private Vector unoptimizedValue(Vector x) {
        
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
    
        if (optimized) {
            return optimizedJacobian(x);
        }
        return unoptimizedJacobian(x);
    
    }
    
    private Matrix optimizedJacobian(Vector x) {

        for (int i = 0; i < outputSize(); i++) {
        
            Matrix jacobRow = functions[i].jacobian(x);
            
            for (int k = 0; k < jacobRow.getCols(); k++) {
            
                jacobianMemory.set(i, k, jacobRow.get(0, k));
            
            }
        
        }
        
        return jacobianMemory;
    }
    private Matrix unoptimizedJacobian(Vector x) {
        Matrix jacobian = Matrix.zeros(outputSize(), inputSize());
        for (int i = 0; i < outputSize(); i++) {
        
            Matrix jacobRow = functions[i].jacobian(x);
            
            for (int k = 0; k < jacobRow.getCols(); k++) {
            
                jacobian.set(i, k, jacobRow.get(0, k));
            
            }
        
        }
        
        return jacobian;
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

    
    /**
     * Metodi päivittää funktion komponenttifunktioiden painot.
     * @param learningRate oppimisnopeutta säätelevä parametri
     */
    @Override
    public void train(double learningRate) {
        for (int i = 0; i < outputSize(); i++) {
        
            trainFunction(i, learningRate);
        
        }
    }
    
    protected void trainFunction(int i, double learningRate) {
        if (optimized) {
            optimizedTrainFunction(i, learningRate);
            return;
        }
        unoptimizedTrainFunction(i, learningRate);
    }
    
    private void unoptimizedTrainFunction(int i, double learningRate) {
    
        Matrix outputErrorGradient = layer.getOutputErrorGradient();
        
        int layerN = layer.getLayerNumber();
        
        NetworkLayer previous = layer.getNetwork().getPreviousLayer(layerN);
        
        Vector input = previous.getValue();
        
        NeuronFunction function = functions[i];
        
        
        Matrix jacob = function.parameterJacobian(input);
        

        Matrix grad = jacob.times(outputErrorGradient.get(0, i));
        
        grad = grad.transpose();
                
        grad = grad.times(-learningRate);
        
        function.setParameter(new Vector(function.getParameter().plus(grad).asArray()));

    }
    private void optimizedTrainFunction(int i, double learningRate) {
    
        Matrix outputErrorGradient = layer.getOutputErrorGradient();
        
        int layerN = layer.getLayerNumber();
        
        NetworkLayer previous = layer.getNetwork().getPreviousLayer(layerN);
        
        Vector input = previous.getValue();
        
        NeuronFunction function = functions[i];
        
        
        Matrix jacob = function.parameterJacobian(input);
        

        jacob.timesToDestination(outputErrorGradient.get(0, i), gradientMemory);
        
        gradientMemory.transposeToDestination(gradientTransposeMemory);
                
        gradientTransposeMemory.timesToDestination(-learningRate, gradientTransposeMemory);
        
        function.getParameter().plusToDestination(gradientTransposeMemory, function.getParameter());

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

    public void setUnoptimized() {
        optimized = false;
        for (int i = 0; i < functions.length; i++) {
            functions[i].setUnoptimized();
        }
    }
    
    
    
}
