/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

import imagerecognition.math.Vector;

/**
 *
 * VectorFunction on vektorifunktiota kuvaava abstrakti luokka.
 */
public abstract class VectorFunction {
    /**
     * Metodi palauttaa arvon f(x) missä f on olion määrittelemä kuvaus.
     * @param x piste
     * @return arvo pisteessä
     */
    public abstract Vector value(Vector x);
    
    
        
    /**
     * 
     * @return kuvattavan alkion dimensio
     */
    public abstract int inputSize();
    
    /**
     * 
     * @return kuva-alkion dimensio
     */
    public abstract int outputSize();
}
