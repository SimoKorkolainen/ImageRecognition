/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data;

/**
 *
 * @author Simo
 */
public interface Dataset {
    public int getNumberOfDimensions();
    public int getTrainingDataSize();
    public int getTestingDataSize();
    public DataPoint[] getTrainingData();
    public DataPoint[] getTestingData();
    public boolean isClassified();
}
