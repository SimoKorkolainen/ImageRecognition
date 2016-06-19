/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.data.datasets.CifarDataset;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetworkBuilder;
import imagerecognition.testing.ClassificationTesting;
import imagerecognition.training.NetworkTrainer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Simo
 */
public class ImageRecognition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        NeuralNetwork network = NeuralNetworkBuilder.softplusAndSoftmax(32 * 32 * 3, 15, 10, 1.0);
        
        CifarDataset dataset = new CifarDataset(1000, 1000);
        
        UserInterface ui = new UserInterface(network, dataset);
        SwingUtilities.invokeLater(ui);

        
        /*
        NetworkTrainer trainer = new NetworkTrainer(network);
        
        ClassificationTesting testing;
        double oldScore = 0;
        double learningRate = 0.00000001;
        int times = 500;
        for (int i = 0; i < times; i++) {
            trainer.setNetwork(ui.getNetwork());
            testing = new ClassificationTesting(ui.getNetwork());
            ui.update();
            

            trainer.trainWithDataset(dataset, 1, learningRate / (i + 1), true);
            System.out.println("-------training score---------");
            double score = testing.testScore(dataset.getTrainingData());
            System.out.println(score);
            System.out.println("---------testing score---------");
            System.out.println(testing.testScore(dataset.getTestingData()));
            
            if (score >= oldScore) {
                learningRate *= 1.1;
            } else {
                learningRate /= 1.1;
            }
            System.out.println("----------learning rate-------------");
            System.out.println(learningRate);
        }
        */
    }
    
}
