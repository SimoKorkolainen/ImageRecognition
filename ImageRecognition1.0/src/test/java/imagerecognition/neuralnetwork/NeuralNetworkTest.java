/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.neuralnetwork;

import imagerecognition.functions.NeuronLayerFunction;
import imagerecognition.functions.SoftmaxFunction;
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
        network = new NeuralNetwork(2, 1);
        
        NeuronLayerFunction f = new NeuronLayerFunction(new SoftplusFunction(), 0, 2, 1);
        
        network.setLayer(1, new NetworkLayer(f));
        
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void outputIsCorrectWhenWeightsAreZero() {
        
        Vector value = network.update(new double[]{42, Math.PI});
        
        assertEquals(Math.log(2), value.get(0), ERROR_MARGIN);
        
    
    }
    
}
