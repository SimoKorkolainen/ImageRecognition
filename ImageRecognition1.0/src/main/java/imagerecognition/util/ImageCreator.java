/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.util;

import java.awt.image.BufferedImage;

/**
 * ImageCreator on kuvien luomiseen tarkoitettu luokka.
 */
public class ImageCreator {
    
    /**
     * Metodi luo värikuvan taulukon perusteella.
     * @param rgb väritaulukko
     * @return kuva
     */
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
    /**
     * Metodi luo kuvataulukon väritaulukon perusteella.
     * @param rgb väritaulukko
     * @return kuvataulukko
     */
    public static BufferedImage[] create(int rgb[][][][]) {
    
        BufferedImage[] bis = new BufferedImage[rgb.length];
        
        for (int i = 0; i < rgb.length; i++) {
        
            bis[i] = create(rgb[i]);
            
        }
        
        return bis;
    
    }
    
    /**
     * Metodi kasvattaa kuvan kokoa times-kertaiseksi.
     * @param bi kuva
     * @param times suurennuskerroin
     * @return suurennettu kuva
     */
    public static BufferedImage increaseSize(BufferedImage bi, int times) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage big = new BufferedImage(width * times, height * times, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                
                int col = bi.getRGB(i, j);
                
                for (int k = 0; k < times; k++) {
                    for (int l = 0; l < times; l++) {
                    
                        int x = i * times + k;
                        int y = j * times + l;
                        big.setRGB(x, y, col);
                    
                    }
                }
            
            }
        }
        return big;
    }
    /**
     * Metodi kasvattaa kuvien kokoa times-kertaiseksi.
     * @param bi kuvat
     * @param times suurennuskerroin
     * @return suurennettut kuvat
     */
    public static BufferedImage[] increaseSize(BufferedImage bi[], int times) {
        BufferedImage big[] = new BufferedImage[bi.length];
        for (int i = 0; i < bi.length; i++) {
            big[i] = increaseSize(bi[i], times);
        }
        return big;
    }
}
