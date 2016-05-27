/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions.general;

import imagerecognition.math.Matrix;
import imagerecognition.math.Vector;
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
public class SoftmaxFunctionTest {
    
    private SoftmaxFunction function;
    private static final double ERROR_MARGIN = 0.0001;
    
    public SoftmaxFunctionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

        
        Vector[] weights = new Vector[2];
        
        weights[0] = new Vector(new double[] {1, 0});
        weights[1] = new Vector(new double[] {0, 2});
 

        function = new SoftmaxFunction(weights);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void functionOutputSumsToOne() {
        
        for (int i = 0; i < 100; i++) {
            outputsSumToOne(getComplexOutput(i));
        }
    
    
    }
    
    @Test
    public void functionOutputsAreNotNegative() {
        
        for (int i = 0; i < 100; i++) {
            outputsAreNotNegative(getComplexOutput(i));
        }
    
    
    }
    
    private void outputsSumToOne(Vector output) {
        assertEquals(1, output.sum(), ERROR_MARGIN);
    }
    
    private void outputsAreNotNegative(Vector output) {
    
        assertFalse(output.get(0) < 0 || output.get(1) < 0);
    
    }
    
    
    private Vector getComplexOutput(int i) {
        
        Vector input = new Vector(new double[] {0.5 * i - 0.5, -Math.log(i + 1) * 2 - 0.5});
        
        return function.value(input);
    
    
    }
    
    
    @Test
    public void jacobianIsCorrect() {
        
        Vector input = new Vector(new double[] {1, -2});
    
        
        Matrix jacobian = function.jacobian(input);
        Matrix approx = approxJacobian(input);
       
        
        matrixAssertEquals(approx, jacobian);
    
    }
    
    
    private Matrix approxJacobian(Vector input) {
    
        Vector value = function.value(input);
        
        double h = 0.00001;
        
        Matrix jacobian = Matrix.zeros(2, 2);
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
            
                Vector eps = new Vector(new double[] {(1 - i) * h, i * h});
                    
                Vector epsInput = input.plus(eps);
                
                Vector epsValue = function.value(epsInput);
                
                double partialD = (epsValue.get(j) - value.get(j)) / h;
                
                jacobian.set(j, i, partialD);
                
            }
        
        }
        
    
        return jacobian;
    }
    
    private void matrixAssertEquals(Matrix a, Matrix b) {

        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(a.get(i, j), b.get(i, j), ERROR_MARGIN);
            }
        }
        
    }
    


}
