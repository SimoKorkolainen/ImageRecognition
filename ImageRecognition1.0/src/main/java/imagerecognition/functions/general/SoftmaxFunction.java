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
    
    private Matrix jacobianMemory; //for avoiding costly allocations
    private Matrix gradientMemory;
    private Matrix gradientTransposeMemory;
    
    public SoftmaxFunction(double weightRandomness, int inputSize, int outputSize) {
        super(new ExponentialFunction(), weightRandomness, inputSize, outputSize);
        jacobianMemory = Matrix.zeros(outputSize(), inputSize());
        gradientMemory = Matrix.zeros(1, inputSize());
        gradientTransposeMemory = Matrix.zeros(inputSize(), 1);
    }
    
    public SoftmaxFunction(Vector weights[]) {
        super(new ExponentialFunction(), weights);
        jacobianMemory = Matrix.zeros(outputSize(), inputSize());
        gradientMemory = Matrix.zeros(1, inputSize());
        gradientTransposeMemory = Matrix.zeros(inputSize(), 1);
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
        
        
        value.productToDestination(dySum, jacobianMemory);
        
        jacobianMemory.timesToDestination(-1, jacobianMemory);

        
        
        jacobianMemory.plusToDestination(dy, jacobianMemory);
        
        jacobianMemory.timesToDestination(1.0 / superValue.sum(), jacobianMemory);
        
        return jacobianMemory;
    
    }
    @Override
    public void train(double learningRate) {
        double nanoTime = System.nanoTime();
        for (int i = 0; i < outputSize(); i++) {
        
            trainFunction(i, learningRate);
        
        }
        
        //System.out.println("training took " + ((System.nanoTime() - nanoTime) * 1e-9) + " seconds");
    }
    

    @Override
    public void trainFunction(int i, double learningRate) {

        Matrix outputErrorGradient = getLayer().getOutputErrorGradient();
        
        
        NeuronFunction function = getFunctions()[i];
        
        double nanoTime = System.nanoTime();
        Matrix jacob = getFunctionTrainingJacobian(i);
        //System.out.println("calculating training jacobian took " + ((System.nanoTime() - nanoTime) * 1e-9) + " seconds");
                

        outputErrorGradient.productToDestination(jacob, gradientMemory);
        

        gradientMemory.transposeToDestination(gradientTransposeMemory);
        
        gradientTransposeMemory.timesToDestination(-learningRate, gradientTransposeMemory);
        
    
        function.getParameter().plusToDestination(gradientTransposeMemory, function.getParameter());

    }
    
    
    
    
    public Matrix getFunctionTrainingJacobian(int i) {

        int layerN = getLayer().getLayerNumber();
        
        NetworkLayer previous = getLayer().getNetwork().getPreviousLayer(layerN);
        
        Vector input = previous.getValue();
        
        Vector superVal = super.value(input);
        
        double nanoTime = System.nanoTime();
        double superValSum = superVal.sum();
        //System.out.println("calculating sum took " + ((System.nanoTime() - nanoTime) * 1e-9) + " seconds");

        double superValSumSqr = superValSum * superValSum;
        
        superVal.timesToDestination(- 1.0 / superValSumSqr, superVal);
        
        superVal.set(i, 0, superVal.get(i, 0) + 1.0 / superValSum);
        
        Matrix superParGrad = getFunctions()[i].parameterJacobian(input);
        
        nanoTime = System.nanoTime();
        
        superVal.productToDestination(superParGrad, jacobianMemory);
        
        //System.out.println("calculating product took " + ((System.nanoTime() - nanoTime) * 1e-9) + " seconds");
                
        
        return jacobianMemory;
    }
    
}
