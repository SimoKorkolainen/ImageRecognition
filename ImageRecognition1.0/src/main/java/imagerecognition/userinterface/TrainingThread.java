/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.testing.ClassificationTesting;
import imagerecognition.training.NetworkTrainer;

/**
 *
 * @author Simo
 */
public class TrainingThread extends Thread {
    private int iterationsLeft;
    private double learningRate;
    private NetworkTrainer trainer;
    private UserInterface ui;

    public TrainingThread(int iterationsLeft, UserInterface ui, double learningRate) {
        this.iterationsLeft = iterationsLeft;
        this.ui = ui;
        this.learningRate = learningRate;
    }
    
    
    
    @Override
    public void run() {
        ClassificationTesting testing;
        double oldScore = 0;
        while (iterationsLeft > 0) {
            trainer = new NetworkTrainer(ui.getNetwork());

            testing = new ClassificationTesting(ui.getNetwork());
            ui.update();
            

            trainer.trainWithDataset(ui.getDataset(), 1, learningRate, true);
            System.out.println("-------training score---------");
            double trainingScore = testing.testScore(ui.getDataset().getTrainingData());
            
            ui.getTrainingScore().addPoint(trainingScore);
            
            System.out.println(trainingScore);
            System.out.println("---------testing score---------");
            double testingScore = testing.testScore(ui.getDataset().getTestingData());
            System.out.println(testingScore);
            
            ui.getTestingScore().addPoint(testingScore);
            /*
            if (score >= oldScore) {
                learningRate *= 1.1;
            } else {
                learningRate /= 1.1;
            }*/
            System.out.println("----------learning rate-------------");
            System.out.println(learningRate);
        } 
    
    }
    
    public void stopTraining() {
    
        iterationsLeft = 0;
    
    }
    
}
