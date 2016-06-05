/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.training;

import imagerecognition.data.classification.ClassifiedVector;
import imagerecognition.math.Vector;
import imagerecognition.neuralnetwork.NeuralNetwork;

/**
 *
 * @author Simo
 */
public class NetworkTrainer {
    
    private NeuralNetwork network;

    public NetworkTrainer(NeuralNetwork network) {
        this.network = network;
    }
    
    
    public void train(ClassifiedVector[] trainingData, double learningRate, int times) {
    
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < trainingData.length; j++) {
                train(trainingData[j], learningRate);
            }
        }
        
    }
    
    public void train(Vector trainingVector, Vector targetOutput, double learningRate) {

        network.updateValues(trainingVector);
       
        network.updateGradients(targetOutput);
        network.train(learningRate);
    
    }
    
    public void train(ClassifiedVector trainingVector, double learningRate) {
    

        Vector targetOutput = Vector.standardBasisVector(trainingVector.getPointClass(), trainingVector.getPointClassMax());

        train(trainingVector, targetOutput, learningRate);
    }
    
    
    
    
    
    
    
    
}
