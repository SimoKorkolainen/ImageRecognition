/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data;

import imagerecognition.data.classification.ClassTable;
import imagerecognition.data.classification.ClassifiedVector;
import imagerecognition.util.ArrayUtil;
import imagerecognition.util.ImageCreator;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CifarDataset on luokka, joka lataa kuvia ja luokkia tiedostosta.
 * 
 */
public class CifarDataset {
    private ClassTable classTable;
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private static final int COLORS = 3;
    private static final int IMAGES_IN_BATCH = 10000;
    private static final String PATH = "data/cifar/";
    


    private int classes[];
    private int[][][][] rgbData;
    
    public CifarDataset(int batches) {
        int images = IMAGES_IN_BATCH;
        rgbData =  new int[images][0][0][0];
        classes = new int[images];
        
        loadImages("data_batch_1.bin");
        initClassTable();
        
    }
    
    
    
    
    
    public ClassifiedVector[] getTrainingData() {
        ClassifiedVector[] vectors = new ClassifiedVector[rgbData.length];
        
        for (int i = 0; i < vectors.length; i++) {
            double[] data = ArrayUtil.int1DToDouble1D(ArrayUtil.reshape3DTo1D(rgbData[i]));
            vectors[i] = new ClassifiedVector(data, classes[i]);
        
        }
        
        return vectors;
    
    }
            
    
    
    private void loadImages(String filename) {
    
        filename = PATH + filename;

        Path path = FileSystems.getDefault().getPath(filename);
        
        try {

            byte[] data = Files.readAllBytes(path);
            
            updateRGBAndClassData(data);
            
        } catch (IOException ex) {
            
        }
    
    }
    
    public int[] getClasses() {
    
        return classes;
    
    }
    
    private void updateRGBAndClassData(byte[] data) {
  
        for (int i = 0; i < rgbData.length; i++) {
            int ind = i * (WIDTH * HEIGHT * COLORS + 1);
            classes[i] = data[ind];
            

            rgbData[i] = getRGBData(ind + 1, data);

        }
    }
    
    
    private int[][][] getRGBData(int ind, byte[] data) {
        
        int[][][] rgb = new int[COLORS][HEIGHT][WIDTH];
        
        for (int l = 0; l < COLORS; l++) {
            for (int k = 0; k < HEIGHT; k++) {
                for (int j = 0; j < WIDTH; j++) {
                


                    rgb[l][k][j] = data[ind] & 0xFF;

                    ind++;

                }
            }
        } 
        
        return rgb;
    
    }
    
    
    private void initClassTable() {
        String[] classNames  = {"airplane", 
                                "automobile",
                                "bird",
                                "cat",
                                "deer",
                                "dog",
                                "frog",
                                "horse",
                                "ship",
                                "truck",
                                "cat"};
        
        

        classTable = new ClassTable(classNames);
    

    }
    
    
    public ClassTable getClassTable() {
    
        return classTable;
    
    }
    
    /**
     * Metodi palauttaa kuvat.
     * @return kuvat
     */
    public BufferedImage[] getImages() {
        return ImageCreator.create(rgbData);
    
    }
    
    
    
    
}
