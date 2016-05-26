/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

import imagerecognition.util.ArrayUtil;
import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;
import imagerecognition.functions.activation.ActivationFunction;

/**
 *
 * Neuronia kuvaava luokka.
 * Neuroni on funktio, joka on palauttama arvo on muotoa f(a'x), missä a on parametrivektori,
 * f on aktivaatiofunktio ja x on syöte.
 */
public class NeuronFunction extends ParametricFunction {

    private ActivationFunction activation;
    
    public NeuronFunction(ActivationFunction activation, double weigthRandomness, int inputSize) {
        
        this.activation = activation;
        
        Vector p = new Vector(ArrayUtil.random(-weigthRandomness, weigthRandomness, inputSize));

        super.setParameter(p);
    
    }
    
    /**
     * Metodi palauttaa neuronin arvo syötteellä x.
     * @param x syöte
     * @return arvon sisältävä yksipaikkainen vektori
     */
    @Override
    public Vector value(Vector x) {

        return new Vector(new double[] {doubleValue(x)});
    
    }
   /**
     * Metodi palauttaa neuronin arvo syötteellä x.
     * @param x syöte
     * @return arvo liukulukuna.
     */
    public double doubleValue(Vector x) {
        System.out.println(x.get(0) + " " + x.get(1) + " " + x.dotProduct(getParameter()) +  " " + activation.value(x.dotProduct(getParameter())));
        return activation.value(x.dotProduct(getParameter()));
    }

    /**
     * Metodi palauttaa funktion Jakobiaanin syötteen suhteen pisteessä x.
     * @param x piste
     * @return Jakobiaani
     */
    @Override
    public Matrix jacobian(Vector x) {
        double dx = activation.getDerivative(x.dotProduct(getParameter()));
        return getParameter().transpose().times(dx);
    }
    
    

    /**
     * Metodi palauttaa funktion Jakobiaanin parametrin suhteen pisteessä x.
     * @param x piste
     * @return Jakobiaani
     */
    @Override
    public Matrix parameterJacobian(Vector x) {
        double dx = activation.getDerivative(x.dotProduct(getParameter()));
        return x.transpose().times(dx);
    }

    /**
     * 
     * @return syötteen dimensio 
     */
    @Override
    public int inputSize() {
        return getParameter().size();
    }

    /**
     * 
     * @return tulosteen dimensio
     */
    @Override
    public int outputSize() {
        return 1;
    }
    
    
    
}
