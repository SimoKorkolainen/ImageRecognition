/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.training.ClassificationTesting;
import imagerecognition.neuralnetwork.training.NetworkTrainer;

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
        
        NeuralNetwork net = ui.getNetwork();
        



        
        ClassificationTesting testing;

        while (iterationsLeft > 0) {
            try {
                Thread.sleep(300);
            } catch (Exception e) {}
            trainer = new NetworkTrainer(net);

            testing = new ClassificationTesting(net);
            ui.update();
            
            
            while (net.isInUse()) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {}

                System.out.println("Waiting...");
            }
            net.setInUse(true);
            trainer.trainWithDataset(ui.getDataset(), 1, learningRate, true);
            
            net.setInUse(false);
            
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
        net.setInUse(false);
    }
    
    public void stopTraining() {
    
        iterationsLeft = 0;
    
    }
    
}
