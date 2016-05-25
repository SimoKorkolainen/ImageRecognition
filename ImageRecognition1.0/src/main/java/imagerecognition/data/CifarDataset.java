/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data;

import imagerecognition.data.classification.ClassTable;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simo
 */
public class CifarDataset {
    private ClassTable classTable;
    private BufferedImage imgs[];
    private int classes[];
    
    public CifarDataset(int batches) {
        imgs = new BufferedImage[100];
        classes = new int[100];
        String filename = "data/cifar/data_batch_1.bin";

        Path path = FileSystems.getDefault().getPath(filename);
        try {
            int ind = 0;
            byte[] data = Files.readAllBytes(path);
            for (int i = 0; i < 100; i++) {
                classes[i] = data[ind];
                ind++;
                int[][][] rgbData = new int[32][32][3];
                for (int l = 0; l < 3; l++) {
                    for (int j = 0; j < 32; j++) {
                        for (int k = 0; k < 32; k++) {


                            rgbData[k][j][l] = data[ind] & 0xFF;

                            ind++;

                        }
                    }
                }
                
                imgs[i] = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR_PRE);
                
                for (int j = 0; j < 32; j++) {
                    for (int k = 0; k < 32; k++) {
                        
                        int r = rgbData[j][k][0];
                        int g = rgbData[j][k][1];
                        int b = rgbData[j][k][2];
                        
                        
                        
                        int col = (255 << 24) | (r << 16) | (g << 8) | b;
                        
                        imgs[i].setRGB(j, k, col);
                        
                    }
                
                }
                
            }
            
        } catch (IOException ex) {
            
        }
        
    }
    
    
    public BufferedImage[] getImages() {
    
        return imgs;
    
    }
    
    public int[] getClasses() {
    
        return classes;
    
    }
    
    
}
