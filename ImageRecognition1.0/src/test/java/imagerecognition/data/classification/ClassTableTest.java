package imagerecognition.data.classification;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ClassTableTest {
    private ClassTable table;
    public ClassTableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        table = new ClassTable(2);
        table.setClassName("Dog", 0);
    }
    
    
    @Test
    public void initializationWorks() {
        assertEquals("Class 1", table.getClassName(1));
    }
    
    @Test
    public void classNameSettingWorks() {
        table.setClassName("Cat", 1);
        assertEquals("Cat", table.getClassName(1));
    }
    @Test
    public void classNameSearchingDogWorks() {
        assertEquals(0, table.findClassNumber("Dog"));
    }
    @Test
    public void classNameSearchingCatWorks() {
        table.setClassName("Cat", 1);
        assertEquals(1, table.findClassNumber("Cat"));
    }
    
    @Test
    public void numberOfClassesIsCorrect() {
        assertEquals(2, table.getNumberOfClasses());
    }
    @Test
    public void classNameSearchingWorksWhenClassDoesntExist() {
        assertEquals(-1, table.findClassNumber("Hello world"));
    }
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
