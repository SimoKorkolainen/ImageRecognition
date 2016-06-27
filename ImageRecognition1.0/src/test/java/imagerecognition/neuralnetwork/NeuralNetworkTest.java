/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.neuralnetwork;

import imagerecognition.neuralnetwork.layers.SoftmaxFunction;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
import imagerecognition.neuralnetwork.layers.NeuronLayerFunction;
import imagerecognition.neuralnetwork.activation.SigmoidFunction;
import imagerecognition.neuralnetwork.activation.SoftplusFunction;
import imagerecognition.neuralnetwork.training.ErrorFunction;
import imagerecognition.neuralnetwork.training.QuadraticErrorFunction;
import imagerecognition.util.Matrix;
import imagerecognition.util.Vector;
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
        
        Vector value = network.updateValues(new Vector(new double[]{42, Math.PI}));
        
        assertEquals(Math.log(2), value.get(0), ERROR_MARGIN);
        
    
    }
    
    
    @Test
    public void outputIsCorrectWithPlusMinusWeights() {
        initializeToPlusMinusOneWeighted();
        
        Vector value = network.updateValues(new Vector(new double[]{3, Math.PI}));
        
        double expected = 1.0 / (1 + Math.exp(Math.PI - 3));
        
        assertEquals(expected, value.get(0), ERROR_MARGIN);
    
    }
    
    
    @Test
    public void outputIsCorrectWithSoftmax() {
        initializeToSoftmax();
        
        Vector value = network.updateValues(new Vector(new double[]{-2, 1}));
        
        double a = Math.exp(-2 * 2 - 5 * 1);
        double b = Math.exp(2 * 5 - 1 * 1);
    
        double firstExpected = a / (a + b);
        
        assertEquals(firstExpected, value.get(0), ERROR_MARGIN);
    
    }
    
    @Test
    public void outputSumsToOneWithSoftmax() {
        initializeToSoftmax();
        
        
        Vector value = network.updateValues(new Vector(new double[]{-0.5, Math.sqrt(42)}));

        
        assertEquals(1, value.get(0) + value.get(1), ERROR_MARGIN);
    
    }
    
    

    public void initializeToTestGradients() {
    
        network = new NeuralNetwork(3, 2);
        
        Vector[] weights = new Vector[2];
        
        weights[0] = new Vector(new double[] {2, -4});
        weights[1] = new Vector(new double[] {-3, -1});
        
        Vector[] secondWeights = new Vector[1];
        
        secondWeights[0] = new Vector(new double[] {-1, 1});

        NeuronLayerFunction first = new NeuronLayerFunction(new SoftplusFunction(), weights);
        
        NeuronLayerFunction second = new NeuronLayerFunction(new SoftplusFunction(), secondWeights);
 
        
        network.setLayer(1, new NetworkLayer(first));
        network.setLayer(2, new NetworkLayer(second));
        
        
    
    }
    
    public void updateNetworkToTestGradients() {
        network.setErrorFunction(new QuadraticErrorFunction());
        
        network.updateValues(new Vector(new double[]{-1, 1}));
        
        network.updateGradients(new Vector(new double[] {2}));
    }
    
    @Test
    public void outputIsCorrectInGradientTest() {
        initializeToTestGradients();
        updateNetworkToTestGradients();
        assertEquals(Math.log((Math.exp(2) + 1) / (Math.exp(-6) + 1) + 1), network.getLayer(2).getValue().get(0), ERROR_MARGIN);
    }
    
    
    @Test
    public void errorIsCorrectInGradientTest() {
        initializeToTestGradients();
        updateNetworkToTestGradients();
        assertEquals(Math.pow(Math.log((Math.exp(2) + 1) / (Math.exp(-6) + 1) + 1) - 2, 2) / 2, network.getErrorFunction().value(network.getLayer(2).getValue()).get(0), ERROR_MARGIN);
    }
    
    @Test
    public void outputErrorGradientIsCorrectInFirstLayer() {
        initializeToTestGradients();
        updateNetworkToTestGradients();
              
        Matrix grad = network.getLayer(1).getOutputErrorGradient();
        
        double err = Math.log((Math.exp(2) + 1) / (Math.exp(-6) + 1) + 1) - 2;
        double firstGrad = -1 / (Math.exp(Math.log(Math.exp(-6) + 1) - Math.log(Math.exp(2) + 1)) + 1) * err;
        double secondGrad = -firstGrad;
        
        assertEquals(firstGrad, grad.get(0, 0), ERROR_MARGIN);
        assertEquals(secondGrad, grad.get(0, 1), ERROR_MARGIN);
    }
   
    @Test
    public void outputErrorGradientIsCorrectInSecondLayer() {
        initializeToTestGradients();
        updateNetworkToTestGradients();
        
        Matrix grad = network.getLayer(2).getOutputErrorGradient();
        
        double err = Math.log((Math.exp(2) + 1) / (Math.exp(-6) + 1) + 1) - 2;
        
        assertEquals(err, grad.get(0, 0), ERROR_MARGIN);
    }
}
