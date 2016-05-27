/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data.datasets;

import imagerecognition.data.classification.ClassifiedVector;

/**
 *
 * Dataset on rajapinta eri dataseteille, jottei koodissa tarvitse ottaa kantaa
 * mitä datasettiä käytetään.
 * 
 */
public interface Dataset {
    
    /**
     * Metodi palauttaa datan ulottuvuuksien lukumäärän. Esim. mustavalkoisen 32x32-pikselisen
     * kuvan ulottuvuuksien lukumäärä on 32 * 32 = 1024.
     * @return datan dimension
     */
    public int getNumberOfDimensions();
    
    
    /**
     * Metodi palauttaa oppimisdatan pisteiden lukumäärään.
     * @return pisteiden määrä
     */
    public int getTrainingDataSize();
    
    /**
     * Metodi palauttaa testausdatan pisteiden lukumäärään.
     * @return pisteiden määrä
     */
    public int getTestingDataSize();
    
    /**
     * Metodi palauttaa oppimisdatan.
     * @return oppimisdata
     */
    public ClassifiedVector[] getTrainingData();
    
    /**
     * Metodi palauttaa testausdatan.
     * @return testausdata
     */
    public ClassifiedVector[] getTestingData();
}
