package imagerecognition.data.datasets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import imagerecognition.data.classification.ClassTable;
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
public class CifarDatasetTest {
    
    private CifarDataset dataset;
    
    public CifarDatasetTest() {
       
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dataset = new CifarDataset(1);
    }
    
    @Test
    public void imagesAreReturned() {
        assertTrue(dataset.getImages().length > 0);
    }
    
    @Test
    public void containsData() {
    
        assertTrue(dataset.getTrainingData().length > 0);
    
    }
    
    @Test
    public void sameNumberOfClassesAndImages() {
    
        assertTrue(dataset.getClasses().length == dataset.getImages().length);
    
    }
    
    @Test
    public void firstIsFrog() {
        ClassTable table = dataset.getClassTable();
        int firstClass = dataset.getTrainingData()[0].getPointClass();
        assertEquals("frog", table.getClassName(firstClass));
    }
    
    @Test
    public void classNumberTenIsCat() {
        assertEquals("cat", dataset.getClassTable().getClassName(10));
    }
    
}
