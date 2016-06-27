/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data;

import imagerecognition.data.ClassTable;
import imagerecognition.data.ClassifiedVector;
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
public class CifarDataset implements Dataset {
    private ClassTable classTable;
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private static final int COLORS = 3;
    private static final int IMAGES_IN_BATCH = 10000;
    private static final String PATH = "data/cifar/";
    


    private int[] trainingClasses;
    private int[][][][] trainingRgbData;
    private int[] testingClasses;
    private int[][][][] testingRgbData;
    
    private ClassifiedVector[] testingData;
    private ClassifiedVector[] trainingData;
    
    
    public CifarDataset(int trainingImages, int testingImages) {
        trainingImages = Math.min(trainingImages, IMAGES_IN_BATCH);
        testingImages = Math.min(testingImages, IMAGES_IN_BATCH);
        trainingRgbData =  new int[trainingImages][0][0][0];
        trainingClasses = new int[trainingImages];
        testingRgbData =  new int[testingImages][0][0][0];
        testingClasses = new int[testingImages];
        loadImages("data_batch_1.bin", false);
        loadImages("test_batch.bin", true);
        initClassTable();

    }
    
    
    
    
    
    @Override
    public ClassifiedVector[] getTrainingData() {
        if (trainingData == null) {
             trainingData = getData(trainingRgbData, trainingClasses);
        }
        return trainingData;
    
    }
            
    
    
    private void loadImages(String filename, boolean testing) {
    
        filename = PATH + filename;

        Path path = FileSystems.getDefault().getPath(filename);
        
        try {

            byte[] data = Files.readAllBytes(path);
            if (testing) {
                updateRGBAndClassData(data, testingRgbData, testingClasses);
            } else {
                updateRGBAndClassData(data, trainingRgbData, trainingClasses);
            }
            
        } catch (IOException ex) {
            
        }
    
    }
    
    public int[] getClasses() {
    
        return trainingClasses;
    
    }
    
    private void updateRGBAndClassData(byte[] data, int[][][][] rgbDest, int[] classesDest) {
  
        for (int i = 0; i < rgbDest.length; i++) {
            int ind = i * (WIDTH * HEIGHT * COLORS + 1);
            classesDest[i] = data[ind];
            

            rgbDest[i] = getRGBData(ind + 1, data);

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
                                "car",
                                "bird",
                                "cat",
                                "deer",
                                "dog",
                                "frog",
                                "horse",
                                "ship",
                                "truck"};
        
        

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
        return ImageCreator.increaseSize(ImageCreator.create(trainingRgbData), 2);
    
    }

    /**
     * Metodi palauttaa datan dimensioiden lukumäärän.
     * @return dimensioiden lukumäärä
     */
    @Override
    public int getNumberOfDimensions() {
        return WIDTH * HEIGHT * COLORS;
    }

    
    /**
     * Metodi palauttaa opetusdatanäytteiden lukumäärän.
     * @return 
     */
    @Override
    public int getTrainingDataSize() {
        return getTrainingData().length;
    }

    /**
     * Metodi palauttaa testidatanäytteiden määrän.
     * @return 
     */
    @Override
    public int getTestingDataSize() {
        return getTestingData().length;
    }

    /**
     * Metodi muodostaa ja palauttaa testaamiseen käytettävän datan.
     * @return testidata
     */
    @Override
    public ClassifiedVector[] getTestingData() {
        
        if (testingData == null) {
            testingData = getData(testingRgbData, testingClasses);
        }
        
        
        return testingData;
    }
    
    
    private ClassifiedVector[] getData(int[][][][] rgbData, int[] classData) {
        ClassifiedVector[] vectors = new ClassifiedVector[rgbData.length];
        
        for (int i = 0; i < vectors.length; i++) {
            double[] data = ArrayUtil.int1DToDouble1D(ArrayUtil.reshape3DTo1D(rgbData[i]));
            vectors[i] = new ClassifiedVector(data, classData[i], classTable.getNumberOfClasses());
        
        }
        
        return vectors;
    }
    
    
    
}
