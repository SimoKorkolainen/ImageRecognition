/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions.activation;

/**
 *
 * Rajapinta SimpleDifferentiable määritteleen yksi yhteen derivoituvan reaalifunktion.
 */
public interface SimpleDifferentiable {
    
    /**
     * Metodi palauttaa funktion derivaatan pisteessä x.
     * @param x piste
     * @return derivaatta
     */
    public double getDerivative(double x);
}
