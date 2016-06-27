package imagerecognition.neuralnetwork.activation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import imagerecognition.neuralnetwork.activation.ExponentialFunction;
import imagerecognition.neuralnetwork.activation.IdentityFunction;
import imagerecognition.neuralnetwork.activation.ActivationFunction;
import imagerecognition.neuralnetwork.activation.SigmoidFunction;
import imagerecognition.neuralnetwork.activation.SoftplusFunction;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Simo
 */
public class SimpleDifferentiableTest {
    private static final double ERROR_MARGIN = 0.0001;
    private static final double EPSILON = 0.000001;
    public SimpleDifferentiableTest() {
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
    public void identityDerivativeIsCorrect() {
        testDerivativeIsCorrect(new IdentityFunction());
    }
    @Test
    public void sigmoidDerivativeIsCorrect() {
        testDerivativeIsCorrect(new SigmoidFunction());
    }
    @Test
    public void softplusDerivativeIsCorrect() {
        testDerivativeIsCorrect(new SoftplusFunction());
    }
    
    @Test
    public void exponentialDerivativeIsCorrect() {
        testDerivativeIsCorrect(new ExponentialFunction());
    }
    
    private double approxDerivative(double x, ActivationFunction f, double h) {
    
        return (f.value(x + h) - f.value(x)) / h;
    
    }
    
    private void testDerivativeIsCorrect(ActivationFunction f) {
        int k = 100;
        for (int i = 0; i < k; i++) {
            double x = -5 + 0.1 * i;
            
            derivativeIsCorrect(x, f);
        }
    
    }
    
    private void derivativeIsCorrect(double x, ActivationFunction f) {
    
        double d = f.getDerivative(x);
        double approxD = approxDerivative(x, f, EPSILON);
    
        assertEquals(approxD, d, ERROR_MARGIN);
    }
    
    
}
