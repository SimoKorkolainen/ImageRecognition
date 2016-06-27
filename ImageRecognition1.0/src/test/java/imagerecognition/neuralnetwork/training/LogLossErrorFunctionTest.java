/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.training;

import imagerecognition.neuralnetwork.training.LogLossErrorFunction;
import imagerecognition.util.Matrix;
import imagerecognition.util.Vector;
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
public class LogLossErrorFunctionTest {
    
    public static final double ERROR_MARGIN = 0.0001;
    
    public LogLossErrorFunctionTest() {
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
    public void valueIsCorrectWithFirstOne() {
        LogLossErrorFunction error = new LogLossErrorFunction();
        error.setParameter(new Vector(new double[] {1, 0, 0}));
        assertEquals(-Math.log(0.9), error.value(new Vector(new double[] {0.9, 0.05, 0.05})).get(0), ERROR_MARGIN);
    } 
    
    @Test
    public void valueIsCorrectWithLastOne() {
        LogLossErrorFunction error = new LogLossErrorFunction();
        error.setParameter(Vector.standardBasisVector(5, 5));
        assertEquals(-Math.log(0.15), error.value(new Vector(new double[] {0.5, 0.05, 0.05, 0.25, 0.15})).get(0), ERROR_MARGIN);
    }
    
    @Test
    public void gradientIsCorrectWithFirstOne() {
        LogLossErrorFunction error = new LogLossErrorFunction();
        error.setParameter(Vector.standardBasisVector(1, 3));
        
        double[] d = {0.9, 0.05, 0.05};
        
        Matrix grad = error.jacobian(new Vector(d));
        
        for (int i = 0; i < d.length; i++) {
            
            assertEquals(-1.0 / d[i] * error.getParameter().get(i), grad.get(0, i), ERROR_MARGIN);
            
        }

    } 
    
    @Test
    public void gradientIsCorrectWithLastOne() {
        LogLossErrorFunction error = new LogLossErrorFunction();
        error.setParameter(Vector.standardBasisVector(5, 5));
        
        double[] d = {0.8, 0.05, 0.05, 0, 0.1};
        
        Matrix grad = error.jacobian(new Vector(d));
        
        for (int i = 0; i < d.length; i++) {
            
            assertEquals(-1.0 / d[i] * error.getParameter().get(i), grad.get(0, i), ERROR_MARGIN);
            
        }

    } 
    
}
