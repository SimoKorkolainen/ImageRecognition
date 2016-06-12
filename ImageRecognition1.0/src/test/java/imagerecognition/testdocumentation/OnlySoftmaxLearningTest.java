/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.testdocumentation;

import imagerecognition.data.classification.ClassifiedVector;
import imagerecognition.data.datasets.CifarDataset;
import imagerecognition.data.datasets.Dataset;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetworkBuilder;
import imagerecognition.testing.ClassificationTesting;
import imagerecognition.training.NetworkTrainer;
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
public class OnlySoftmaxLearningTest {
    
    public OnlySoftmaxLearningTest() {
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

    @Test
    public void onlySoftmaxLearning() {
        Dataset dataset = new CifarDataset(1);
        int[] count = new int[10];
        ClassifiedVector[] data = dataset.getTrainingData();
        for (int i = 0; i < data.length; i++) {
            count[data[i].getPointClass()]++;
        }

        System.out.println(Arrays.toString(count));
        
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
}
