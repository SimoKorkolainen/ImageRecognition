/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.training;

import imagerecognition.neuralnetwork.training.QuadraticErrorFunction;
import imagerecognition.neuralnetwork.training.LogLossErrorFunction;
import imagerecognition.data.ClassifiedVector;
import imagerecognition.neuralnetwork.activation.SigmoidFunction;
import imagerecognition.neuralnetwork.activation.SoftplusFunction;
import imagerecognition.neuralnetwork.NeuronFunction;
import imagerecognition.neuralnetwork.layers.NeuronLayerFunction;
import imagerecognition.neuralnetwork.layers.SoftmaxFunction;
import imagerecognition.util.Matrix;
import imagerecognition.util.Vector;
import imagerecognition.neuralnetwork.NeuralNetwork;
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
public class NetworkTrainerTest {
    private static final double ERROR_MARGIN = 0.00001;
    private NeuralNetwork network;
    private NetworkTrainer trainer;

    
    
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
        
        Vector[] weights = new Vector[2];
        
        weights[0] = new Vector(new double[] {1});
        weights[1] = new Vector(new double[] {-2});
        
        Vector[] secondWeights = new Vector[1];
        
        secondWeights[0] = new Vector(new double[] {-1, 2});
        
        network.setLayer(1, new NetworkLayer(new NeuronLayerFunction(new SoftplusFunction(), weights)));
        network.setLayer(2, new NetworkLayer(new NeuronLayerFunction(new SoftplusFunction(), secondWeights)));
        

        network.setErrorFunction(new QuadraticErrorFunction());

        trainer = new NetworkTrainer(network);
        
    }
    
    @Test 
    public void parametersHaveBeenChangedCorrectlyInSecondLayerTraining() {
        secondInitialization();
        
        Vector in = new Vector(new double[]{0});
        
        Vector out = new Vector(new double[]{1});
        
        double alpha = 0.1;
        
        double err = Math.log(3) - 1;
        double grad = Math.log(2) / (Math.exp(-Math.log(2)) + 1) * err;
        
        double firstPar = -1 - grad * alpha;
        double secondPar = 2 - grad * alpha;
        trainer.train(in, out, alpha);
        
        NeuronFunction f = network.getLayer(2).getFunction().getFunctions()[0];
        
        assertEquals(firstPar, f.getParameter().get(0), ERROR_MARGIN);
        assertEquals(secondPar, f.getParameter().get(1), ERROR_MARGIN);
        
    }
    
    @Test 
    public void parametersHaveBeenChangedCorrectlyInFirstLayerTraining() {
        secondInitialization();
        
        Vector in = new Vector(new double[]{-Math.log(2)});
        
        Vector out = new Vector(new double[]{1});
        
        
        double err = Math.log(53.0 / 3) - 1;
        
        double firstErrGrad = -err * 1.0 / (Math.exp(Math.log(3.0 / 2) - 2 * Math.log(5)) + 1);
        
        
        
        double firstParGrad = - firstErrGrad / (Math.exp(Math.log(2)) + 1) * Math.log(2); 
        
        double alpha = 0.1;
        double trainedPar = 1 - alpha * firstParGrad;
        
        trainer.train(in, out, alpha);
        
        
        NetworkLayer l = network.getLayer(1);
        NeuronFunction f = l.getFunction().getFunctions()[0];
        
        assertEquals(trainedPar, f.getParameter().get(0), ERROR_MARGIN);
        
    }
    
    @Test
    public void outputConvergesCorrectlyWithSecondInitialization() {
        secondInitialization();
        

        Vector[] in = {new Vector(new double[]{0}), new Vector(new double[]{2})};
        
        Vector[] out = {new Vector(new double[]{1.1}), new Vector(new double[]{2.3})};
        for (int i = 0; i < 10000; i++) {

            trainer.train(in[i % 2], out[i % 2], 1.0 / Math.sqrt(i + 1));
        }
        assertEquals(1.1, network.updateValues(in[0]).get(0), ERROR_MARGIN);
        assertEquals(2.3, network.updateValues(in[1]).get(0), ERROR_MARGIN);
    
    }
    
    
    
    public void softmaxInitialization() {
        
    
        network = new NeuralNetwork(2, 2);
        
        Vector[] weights = new Vector[2];
        
        weights[0] = new Vector(new double[] {1, 1});
        weights[1] = new Vector(new double[] {2, -1});
        
        
        network.setLayer(1, new NetworkLayer(new SoftmaxFunction(weights)));

        network.setErrorFunction(new LogLossErrorFunction());
    
    }
    
    @Test
    public void functionTrainingJacobiansAreCorrect() {
        softmaxInitialization();
        Vector in = new Vector(new double[] {-1, 1});
        Vector out = new Vector(new double[] {0.9, 0.1});
        
        network.updateValues(in);
       
        network.updateGradients(out);

        
        SoftmaxFunction s = (SoftmaxFunction) network.getLayer(1).getFunction();
        
        Matrix firstJacob = s.getFunctionTrainingJacobian(0);
        
        System.out.println("----firstJacob-----");
        firstJacob.print();
        System.out.println("-----------");
        
        //double firstOutputErr = Math.exp(-3) + 1;
        
        double parGrad00 = -Math.exp(-3) / Math.pow(Math.exp(-3) + 1, 2);
        double parGrad01 = -parGrad00;
        double parGrad10 = -parGrad00;
        double parGrad11 = parGrad00;
        
        
        assertEquals(parGrad00, firstJacob.get(0, 0), ERROR_MARGIN);
        assertEquals(parGrad01, firstJacob.get(0, 1), ERROR_MARGIN);
        assertEquals(parGrad10, firstJacob.get(1, 0), ERROR_MARGIN);
        assertEquals(parGrad11, firstJacob.get(1, 1), ERROR_MARGIN);
    }
    
    
    @Test
    public void softmaxConvergesToCorrectConstantOutput() {
        softmaxInitialization();
        trainer = new NetworkTrainer(network);
        
        Vector in = new Vector(new double[]{1, 1});
        
        Vector[] out = {new Vector(new double[]{0, 1}), new Vector(new double[]{0, 1}),
                        new Vector(new double[]{1, 0}), new Vector(new double[]{0, 1})};
        
        int iters = 10000;
        for (int i = 0; i < iters; i++) {

            trainer.train(in, out[i % 4], 0.1 * (double) (iters - i) / iters);

        }
        assertEquals(0.25, network.updateValues(in).get(0), 0.001);
        assertEquals(0.75, network.updateValues(in).get(1), 0.001);
    
    }
}
