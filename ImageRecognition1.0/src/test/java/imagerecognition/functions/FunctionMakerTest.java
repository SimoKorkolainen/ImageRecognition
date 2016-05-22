/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions;

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
public class FunctionMakerTest {
    
    public FunctionMakerTest() {
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
    public void makingIdentityWorks() {
        assertEquals("Identity", FunctionMaker.make("Lol XD").getName());
    }
    
    @Test
    public void makingSigmoidWorks() {
        assertEquals("Sigmoid", FunctionMaker.make("Sigmoid").getName());
    }
    
    @Test
    public void makingSoftplusWorks() {
        assertEquals("Softplus", FunctionMaker.make("Softplus").getName());
    }
}
