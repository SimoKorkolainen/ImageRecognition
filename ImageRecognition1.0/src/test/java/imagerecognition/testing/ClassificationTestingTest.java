/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.testing;

import imagerecognition.data.datasets.CifarDataset;
import imagerecognition.data.datasets.Dataset;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetworkBuilder;
import imagerecognition.training.NetworkTrainer;
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
public class ClassificationTestingTest {
    private NeuralNetwork network;
    private Dataset dataset;
    private NetworkTrainer trainer;
    private ClassificationTesting testing;
    public ClassificationTestingTest() {
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
    
    public void setUpCifarWithOnlySoftmax() {
        network = NeuralNetworkBuilder.onlySoftmax(32 * 32 * 3, 10, 0.1);
        
        dataset = new CifarDataset(1);
        
        trainer = new NetworkTrainer(network);
        
        testing = new ClassificationTesting(network);
    }

    @Test
    public void cifarClassificationWorksWithOnlySoftmax() {
        setUpCifarWithOnlySoftmax();
        
        int times = 1;
        
        double learningRate = 1;
        
        trainer.trainWithDataset(dataset, times, learningRate, true);
        
        System.out.println(testing.testScore(dataset.getTrainingData()));
        
    }
}
