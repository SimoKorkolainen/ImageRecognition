/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simokork
 */
public class VectorTest {
    
    public VectorTest() {
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
    public void concatenationWorks() {
    
        Vector a = new Vector(new double[] {1, 2, 3});
        Vector b = new Vector(new double[] {4, 5});
        
        Vector c = Vector.concatenate(a, b);
        
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 1, c.get(i), 1e-5);
        }
    }
}
