/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.util;

import java.awt.image.BufferedImage;

/**
 *
 * @author simokork
 */
public class ImageCreator {
    public static BufferedImage create(int rgb[][][]) {
        int width = rgb[0][0].length;
        int height = rgb[0].length;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                
                int color = getColor(rgb[0][j][i], rgb[1][j][i], rgb[2][j][i]);
                
                bi.setRGB(i, j, color);
                
            }
        
        }
        
        return bi;
    
    }
    
    private static int getColor(int r, int g, int b) {
        
        return (255 << 24) | (r << 16) | (g << 8) | b;
    
    }
    
    public static BufferedImage[] create(int rgb[][][][]) {
    
        BufferedImage[] bis = new BufferedImage[rgb.length];
        
        for (int i = 0; i < rgb.length; i++) {
        
            bis[i] = create(rgb[i]);
            
        }
        
        return bis;
    
    }
}
