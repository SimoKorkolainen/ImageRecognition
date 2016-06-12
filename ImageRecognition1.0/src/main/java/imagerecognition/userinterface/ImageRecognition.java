/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.data.datasets.CifarDataset;
import imagerecognition.data.datasets.Dataset;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetworkBuilder;
import imagerecognition.testing.ClassificationTesting;
import imagerecognition.training.NetworkTrainer;
import javax.swing.SwingUtilities;

/**
 *
 * @author Simo
 */
public class ImageRecognition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NeuralNetwork network = NeuralNetworkBuilder.softplusAndSoftmax(32 * 32 * 3, 40, 10, 0.0001);
        
        CifarDataset dataset = new CifarDataset(1);
        
        UserInterface ui = new UserInterface(network, dataset);
        SwingUtilities.invokeLater(ui);

        NetworkTrainer trainer = new NetworkTrainer(network);
        
        ClassificationTesting testing = new ClassificationTesting(network);
        
        int times = 100;
        for (int i = 0; i < times; i++) {
            
            ui.update();
            
            double learningRate = 0.00001 / (i + 1);

            trainer.trainWithDataset(dataset, 1, learningRate, true);
            System.out.println("-------training score---------");
            System.out.println(testing.testScore(dataset.getTrainingData()));
            System.out.println("---------testing score---------");
            System.out.println(testing.testScore(dataset.getTestingData()));
            
        }
    }
    
}
