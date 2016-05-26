/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.util;

import java.awt.image.BufferedImage;
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
public class ImageCreatorTest {
    
    BufferedImage imgs[];
    public ImageCreatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        int[][][][] rgb = new int[1][3][1][1];
        rgb[0][0][0][0] = 124;
        rgb[0][1][0][0] = 160;
        rgb[0][2][0][0] = 0;
        imgs = ImageCreator.create(rgb);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void singlePixelImageRedColorIsCorrect() {
    
        assertEquals(124, imgs[0].getRGB(0, 0) & 0x00ff0000 >> 16);
    
    }
}
