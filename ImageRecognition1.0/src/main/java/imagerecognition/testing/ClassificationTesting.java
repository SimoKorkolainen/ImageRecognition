/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.testing;

import imagerecognition.data.classification.ClassifiedVector;
import imagerecognition.math.Vector;
import imagerecognition.neuralnetwork.NeuralNetwork;

/**
 * ClassificationTesting on luokittelun tuloksen laskemiseen käytettävä luokka.
 */
public class ClassificationTesting {
    
    private NeuralNetwork network;
    /**
     * Konstruktori luo luokittelijaolion
     * @param network luokiteltavia tulosteita antava neuroverkko
     */
    public ClassificationTesting(NeuralNetwork network) {
        this.network = network;
    }
   
    /**
     * Metodi palauttaa testidatan luokittelutuloksena oikein
     * luokiteltujen luokkien osuuden.
     * @param testingData testidata
     * @return luokittelutulos
     */
    public double testScore(ClassifiedVector[] testingData) {
        double correct = 0;
        
        for (int i = 0; i < testingData.length; i++) {
            if (classificationIsCorrect(testingData[i])) {
                correct += 1;
            }
        }
        
        return correct / testingData.length;
    }
    
    private boolean classificationIsCorrect(ClassifiedVector vector) {
        
        Vector out = network.updateValues(vector);
        
        int maxInd = 0;
        
        for (int i = 0; i < vector.getPointClassMax(); i++) {
            if (out.get(i) > out.get(maxInd)) {
                maxInd = i;
            }
        }
        return maxInd == vector.getPointClass();
    }
    

    
    
    
    
    
    

    
}
