/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.training;

import imagerecognition.data.classification.ClassifiedVector;
import imagerecognition.data.datasets.Dataset;
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
    
    
    public void train(ClassifiedVector[] trainingData, double learningRate, boolean printInfo) {
    

        for (int j = 0; j < trainingData.length; j++) {
            
            if (printInfo && j % 1000 == 0) {
                System.out.println("Training... " + (100 * j / trainingData.length) + "% ready");
            }
            
            train(trainingData[j], learningRate);
        }

        
    }
    
    public void train(Vector trainingVector, Vector targetOutput, double learningRate) {

        network.updateValues(trainingVector);
       
        network.updateGradients(targetOutput);
        network.train(learningRate);
    
    }
    
    public void train(ClassifiedVector trainingVector, double learningRate) {
    

        Vector targetOutput = Vector.standardBasisVector(trainingVector.getPointClass() + 1, trainingVector.getPointClassMax());

        train(trainingVector, targetOutput, learningRate);
    }
    
    
    
    public void trainWithDataset(Dataset data, int times, double learningRate, boolean printInfo) {
        for (int i = 0; i < times; i++) {
            if (printInfo) {
                System.out.println("Training session " + (i + 1) + " out of " + times);
            }
            
            train(data.getTrainingData(), learningRate / (i + 1), printInfo);
        }
    }

    public void setNetwork(NeuralNetwork network) {
        this.network = network;
    }
    
    
    
    
}
