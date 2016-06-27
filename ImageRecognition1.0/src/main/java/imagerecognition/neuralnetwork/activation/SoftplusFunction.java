/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.activation;

/**
 *
 * SoftplusFunction on eräs aktivaatiofunktiotyyppi. 
 * Kts. https://en.wikipedia.org/wiki/Rectifier_(neural_networks)
 */
public class SoftplusFunction extends ActivationFunction {

    public SoftplusFunction() {
        super("Softplus");
    }
    
    /**
     * Funktio palauttaa numeerisesti tarkemman arvon funktiolle ln(e ^ x + 1).
     * @param x piste, jossa funktio evaluoidaan
     * @return funktion arvo pisteessä
     */
    @Override
    public double value(double x) {
        return Math.max(x, 0) + Math.log1p(Math.exp(-Math.abs(x)));
    }
   
    /**
     * Metodi palauttaa funktion derivaatan pisteessä
     * @param x piste
     * @return derivaatta pisteessä
     */
    @Override
    public double getDerivative(double x) {
        return 1.0 / (Math.exp(-x) + 1);
    }
    
}
