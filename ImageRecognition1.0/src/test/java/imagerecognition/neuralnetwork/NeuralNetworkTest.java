/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.neuralnetwork;

import imagerecognition.functions.general.NeuronLayerFunction;
import imagerecognition.functions.general.SoftmaxFunction;
import imagerecognition.functions.activation.SigmoidFunction;
import imagerecognition.functions.activation.SoftplusFunction;
import imagerecognition.math.Vector;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author simokork
 */
public class NeuralNetworkTest {
    private static final double ERROR_MARGIN = 0.00001;
    private NeuralNetwork network;
    
    public NeuralNetworkTest() {
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
    
    public void initializeToZeroWeighted() {
        network = new NeuralNetwork(2, 1);
        
        NeuronLayerFunction f = new NeuronLayerFunction(new SoftplusFunction(), 0, 2, 1);
        
        network.setLayer(1, new NetworkLayer(f));
    }
    
    
    
    public void initializeToPlusMinusOneWeighted() {
        network = new NeuralNetwork(2, 2);
        
        Vector[] weights = new Vector[1];
        
        weights[0] = new Vector(new double[] {1, -1});
        
        NeuronLayerFunction f = new NeuronLayerFunction(new SigmoidFunction(), weights);
        
        
        
        network.setLayer(1, new NetworkLayer(f));
    }
    
    public void initializeToSoftmax() {
        network = new NeuralNetwork(2, 2);
        
        Vector[] weights = new Vector[2];
        
        weights[0] = new Vector(new double[] {2, -5});
        weights[1] = new Vector(new double[] {-5, -1});
        

        NeuronLayerFunction f = new SoftmaxFunction(weights);
        
        
        
        network.setLayer(1, new NetworkLayer(f));
    }
        

    @Test
    public void outputIsCorrectWhenWeightsAreZero() {

        
        initializeToZeroWeighted();
        
        Vector value = network.update(new double[]{42, Math.PI});
        
        assertEquals(Math.log(2), value.get(0), ERROR_MARGIN);
        
    
    }
    
    
    @Test
    public void outputIsCorrectWithPlusMinusWeights() {
        initializeToPlusMinusOneWeighted();
        
        Vector value = network.update(new double[]{3, Math.PI});
        
        double expected = 1.0 / (1 + Math.exp(Math.PI - 3));
        
        assertEquals(expected, value.get(0), ERROR_MARGIN);
    
    }
    
    
    @Test
    public void outputIsCorrectWithSoftmax() {
        initializeToSoftmax();
        
        Vector value = network.update(new double[]{-2, 1});
        
        double a = Math.exp(-2 * 2 - 5 * 1);
        double b = Math.exp(2 * 5 - 1 * 1);
    
        double firstExpected = a / (a + b);
        
        assertEquals(firstExpected, value.get(0), ERROR_MARGIN);
    
    }
    
    @Test
    public void outputSumsToOneWithSoftmax() {
        initializeToSoftmax();
        
        
        Vector value = network.update(new double[]{-0.5, Math.sqrt(42)});

        
        assertEquals(1, value.get(0) + value.get(1), ERROR_MARGIN);
    
    }
    
}
