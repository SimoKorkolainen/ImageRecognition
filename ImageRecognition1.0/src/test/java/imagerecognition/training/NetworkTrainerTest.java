/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.training;

import imagerecognition.data.classification.ClassifiedVector;
import imagerecognition.functions.activation.SigmoidFunction;
import imagerecognition.functions.activation.SoftplusFunction;
import imagerecognition.functions.error.QuadraticErrorFunction;
import imagerecognition.functions.general.NeuronLayerFunction;
import imagerecognition.functions.general.SoftmaxFunction;
import imagerecognition.math.Vector;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
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
public class NetworkTrainerTest {
    private static final double ERROR_MARGIN = 0.00001;
    private NeuralNetwork network;
    private NetworkTrainer trainer;
    private ClassifiedVector[] data;
    
    
    public NetworkTrainerTest() {
        
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
    
    public void simpleInitialization() {
    
        network = new NeuralNetwork(2, 1);
        network.setLayer(1, new NetworkLayer(new NeuronLayerFunction(new SigmoidFunction(), 0.0001, 1, 1)));
        
        network.setErrorFunction(new QuadraticErrorFunction());

        trainer = new NetworkTrainer(network);
        
    }
   
    @Test
    public void outputConvergesCorrectlyWithSimpleInitialization() {
        simpleInitialization();
        
        Vector in = new Vector(new double[]{1});
        
        Vector out = new Vector(new double[]{0.75});
        for (int i = 0; i < 5000; i++) {

            trainer.train(in, out, 10 / Math.sqrt(i + 1));
        }
        assertEquals(0.75, network.updateValues(in).get(0), ERROR_MARGIN);
    
    }
    
    public void secondInitialization() {
    
        network = new NeuralNetwork(3, 1);
        network.setLayer(1, new NetworkLayer(new NeuronLayerFunction(new SoftplusFunction(), 0.0001, 1, 5)));
        network.setLayer(2, new NetworkLayer(new NeuronLayerFunction(new SoftplusFunction(), 0.0001, 5, 1)));
        

        network.setErrorFunction(new QuadraticErrorFunction());

        trainer = new NetworkTrainer(network);
        
    }
    
    @Test
    public void outputConvergesCorrectlyWithSecondInitialization() {
        secondInitialization();
        

        Vector[] in = {new Vector(new double[]{1}), new Vector(new double[]{2})};
        
        Vector[] out = {new Vector(new double[]{1.5}), new Vector(new double[]{-1.10})};
        for (int i = 0; i < 150000; i++) {
        System.out.println(network.getLayer(2).getFunction().getFunctions()[0].getParameter().size());
        network.getLayer(1).getFunction().getFunctions()[0].getParameter().print();
        
            trainer.train(in[i % 2], out[i % 2], 4.0 / Math.sqrt(i + 1));
        }
        assertEquals(1.5, network.updateValues(in[0]).get(0), ERROR_MARGIN);
        assertEquals(-1.10, network.updateValues(in[1]).get(0), ERROR_MARGIN);
    
    }
    
}
