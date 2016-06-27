/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.testdocumentation;

import imagerecognition.data.CifarDataset;
import imagerecognition.data.Dataset;
import imagerecognition.neuralnetwork.layers.NeuronLayerFunction;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetworkBuilder;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
import imagerecognition.neuralnetwork.training.NetworkTrainer;
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
public class OptimatizationTest {
    
    public OptimatizationTest() {
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
    public void optimizationTest() {
        Dataset dataset = new CifarDataset(100, 100);

        for (int j = 0; j < 2; j++) {
            double weightRandomness = 0;
            
            NeuralNetwork network = NeuralNetworkBuilder.softplusAndSoftmax(dataset.getNumberOfDimensions(), 200, 10, weightRandomness);

            if (j == 1) {
                setNetworkUnoptimized(network);
            }
            
            double nt = System.nanoTime();
            

            trainNetwork(network, dataset);


            nt = System.nanoTime() - nt;
            System.out.println("it took " + (nt * 1e-9) + " seconds");

        }

    
    }
    
    private void setNetworkUnoptimized(NeuralNetwork network) {
        for (int i = 0; i < network.getNumberOfLayer(); i++) {
            NetworkLayer layer = network.getLayer(i);
            NeuronLayerFunction f = layer.getFunction();
            if (f != null) {
                f.setUnoptimized();
            }

        }
    } 
    
    private void trainNetwork(NeuralNetwork network, Dataset dataset) {
        NetworkTrainer trainer = new NetworkTrainer(network);


        int times = 10;

        for (int i = 0; i < times; i++) {

            double learningRate = 0.00000001 / (i + 1);

            trainer.trainWithDataset(dataset, 1, learningRate, false);

        }    
    }
}
