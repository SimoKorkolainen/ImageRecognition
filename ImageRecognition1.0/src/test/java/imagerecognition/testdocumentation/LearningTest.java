/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.testdocumentation;

import imagerecognition.data.ClassifiedVector;
import imagerecognition.data.CifarDataset;
import imagerecognition.data.Dataset;
import imagerecognition.neuralnetwork.layers.NeuronLayerFunction;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetworkBuilder;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
import imagerecognition.neuralnetwork.training.ClassificationTesting;
import imagerecognition.neuralnetwork.training.NetworkTrainer;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simo
 */
public class LearningTest {
    
    public LearningTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //@Test
    public void onlySoftmaxLearning() {
        Dataset dataset = new CifarDataset(1000, 1000);
        
        double weightRandomness = 0.000000001;
        
        NeuralNetwork network = NeuralNetworkBuilder.onlySoftmax(dataset.getNumberOfDimensions(), 10, weightRandomness);
        
        
        NetworkTrainer trainer = new NetworkTrainer(network);
        
        ClassificationTesting testing = new ClassificationTesting(network);
        
        int times = 30;
        
        double[] trainingScore = new double[times];
        double[] testingScore = new double[times];
        
        for (int i = 0; i < times; i++) {
            
            double learningRate = 0.00000001 / (i + 1);

            trainer.trainWithDataset(dataset, 1, learningRate, true);

            trainingScore[i] = testing.testScore(dataset.getTrainingData());

            testingScore[i] = testing.testScore(dataset.getTestingData());
            
            System.out.println("-------training score---------");
            System.out.println(trainingScore[i]);
            System.out.println("---------testing score---------");
            System.out.println(testingScore[i]);
        }
        
        printTable(testingScore, "Testing score");
        printTable(trainingScore, "Training score");
    
    }
    
    public void printTable(double table[], String text) {
        System.out.println("--------------- " + text + " --------------------");
        
        for (int i = 0; i < table.length; i++) {
            System.out.println(table[i] + ",");
        }
        
        System.out.println("----------------------------------------------------------");
    }
    
    //@Test
    public void hiddenLayerSizeImpactOnTraining() {
        
        Dataset dataset = new CifarDataset(1000, 1000);
        

        
        double weightRandomness = 0.01;
        
        for (int j = 0; j < 2; j++) {

            NeuralNetwork network = NeuralNetworkBuilder.softplusAndSoftmax(dataset.getNumberOfDimensions(), 30 * (j + 1), 10, weightRandomness);


            NetworkTrainer trainer = new NetworkTrainer(network);

            ClassificationTesting testing = new ClassificationTesting(network);

            int times = 30;

            double[] trainingScore = new double[times];
            double[] testingScore = new double[times];
            double[] time = new double[times];
            
            double nt = System.nanoTime();

            for (int i = 0; i < times; i++) {

                double learningRate = 1e-5;

                trainer.trainWithDataset(dataset, 1, learningRate, true);

                trainingScore[i] = testing.testScore(dataset.getTrainingData());

                testingScore[i] = testing.testScore(dataset.getTestingData());

                System.out.println("-------training score---------");
                System.out.println(trainingScore[i]);
                System.out.println("---------testing score---------");
                System.out.println(testingScore[i]);
                
                time[i] = (System.nanoTime() - nt) * 1e-9;
                
            }

            printTable(testingScore, "Testing score");
            printTable(trainingScore, "Training score");
            printTable(time, "time");
        }
    }
    
    //@Test
    public void imageNumberImpactOnTraining() {
        
        
        

        
        double weightRandomness = 0.01;
        
        for (int j = 0; j < 2; j++) {
            Dataset dataset = new CifarDataset(100 + 4900 * j, 1000);
            NeuralNetwork network = NeuralNetworkBuilder.softplusAndSoftmax(dataset.getNumberOfDimensions(), 30, 10, weightRandomness);


            NetworkTrainer trainer = new NetworkTrainer(network);

            ClassificationTesting testing = new ClassificationTesting(network);

            int times = 150 - j * 130;

            double[] trainingScore = new double[times];
            double[] testingScore = new double[times];
            double[] time = new double[times];
            
            double nt = System.nanoTime();

            for (int i = 0; i < times; i++) {

                double learningRate = 1e-5;

                trainer.trainWithDataset(dataset, 1, learningRate, true);

                trainingScore[i] = testing.testScore(dataset.getTrainingData());

                testingScore[i] = testing.testScore(dataset.getTestingData());

                System.out.println("-------training score---------");
                System.out.println(trainingScore[i]);
                System.out.println("---------testing score---------");
                System.out.println(testingScore[i]);
                
                time[i] = (System.nanoTime() - nt) * 1e-9;
                
            }

            printTable(testingScore, "Testing score");
            printTable(trainingScore, "Training score");
            printTable(time, "time");
        }
    }
    
}
