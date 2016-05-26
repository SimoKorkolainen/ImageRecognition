/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

import imagerecognition.math.Vector;

/**
 *
 * Kahden funktion yhdistetyn funktion kuvaamiseen tarkoitettu luokka.
 */
public class CompositionFunction extends VectorFunction {

    private VectorFunction first;
    private VectorFunction second;
    
    /**
     * Metodi palauttaa arvon f(s(x)) missä f = first ja s = second.
     * @param x piste
     * @return arvo pisteessä
     */
    @Override
    public Vector value(Vector x) {
        
        return first.value(second.value(x));
        
    }
    
    
    /**
     * 
     * @return kuvattavan alkion dimensio
     */
    @Override
    public int inputSize() {
        return second.inputSize();
    }
    /**
     * 
     * @return kuva-alkion dimensio
     */
    @Override
    public int outputSize() {
        return first.outputSize();
    }
    
    
    
}
