/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.testing;

import imagerecognition.data.classification.ClassifiedVector;
import imagerecognition.data.datasets.CifarDataset;
import imagerecognition.data.datasets.Dataset;
import imagerecognition.math.Vector;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.training.NetworkTrainer;

/**
 *
 * @author Simo
 */
public class ClassificationTesting {
    
    private NeuralNetwork network;

    public ClassificationTesting(NeuralNetwork network) {
        this.network = network;
    }
   
    public double testScore(ClassifiedVector[] testingData) {
        double correct = 0;
        
        for (int i = 0; i < testingData.length; i++) {
            if (classificationIsCorrect(testingData[i])) {
                correct += 1;
            }
        }
        
        return correct / testingData.length;
    }
    
    public boolean classificationIsCorrect(ClassifiedVector vector) {
        
        Vector out = network.updateValues(vector);
        
        int maxInd = 0;
        
        for (int i = 0; i < vector.getPointClassMax(); i++) {
            if (out.get(i) > out.get(maxInd)) {
                maxInd = i;
            }
        }
        return maxInd == vector.getPointClass();
    }
    
    
    
    
    

    
}
