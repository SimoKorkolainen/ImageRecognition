/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.neuralnetwork;

import imagerecognition.neuralnetwork.layers.ParametricFunction;
import imagerecognition.util.ArrayUtil;
import imagerecognition.util.Vector;
import imagerecognition.util.Matrix;
import imagerecognition.neuralnetwork.activation.ActivationFunction;

/**
 *
 * Neuronia kuvaava luokka.
 * Neuroni on funktio, joka on palauttama arvo on muotoa f(a'x), missä a on parametrivektori,
 * f on aktivaatiofunktio ja x on syöte.
 */
public class NeuronFunction extends ParametricFunction {
    private boolean optimized;
    private Matrix jacobianMemory;
    private ActivationFunction activation;
    
    /**
     * Konstruktori luo neuronia kuvaavan funktion.
     * @param activation neuronin aktivaatiofunktion
     * @param weigthRandomness neuronin painojen valinnan satunnaisuus
     * @param inputSize syötteen koko (yleensä edeltävän kerroksen koko)
     */
    public NeuronFunction(ActivationFunction activation, double weigthRandomness, int inputSize) {
        
        this.activation = activation;
        
        Vector p = new Vector(ArrayUtil.random(-weigthRandomness / inputSize, weigthRandomness / inputSize, inputSize));

        super.setParameter(p);
        
        jacobianMemory = Matrix.zeros(1, inputSize());
        
        this.optimized = true;
        
    }
    
    /**
     * Metodi luo neuronia kuvaavan funktion,
     * jonka tulosteena on syöteen pistetulo painovektorin kanssa operoituna
     * aktivaatiofunktiolla.
     * @param activation aktivaatiofunktio
     * @param weight painovektori
     */
    public NeuronFunction(ActivationFunction activation, Vector weight) {
        
        this.activation = activation;
        
        super.setParameter(weight);
        
        jacobianMemory = Matrix.zeros(1, inputSize());
        
        this.optimized = true;
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
        return activation.value(x.dotProduct(getParameter()));
    }

    /**
     * Metodi palauttaa funktion Jakobiaanin syötteen suhteen pisteessä x.
     * @param x piste
     * @return Jakobiaani
     */
    @Override
    public Matrix jacobian(Vector x) {

        if (optimized) {
            return optimizedJacobian(x);
        }
        return unoptimizedJacobian(x);
    }

    private Matrix optimizedJacobian(Vector x) {

        double dx = activation.getDerivative(x.dotProduct(getParameter()));
        getParameter().transposeToDestination(jacobianMemory);
        jacobianMemory.timesToDestination(dx, jacobianMemory);
        return jacobianMemory;
    }
    
    private Matrix unoptimizedJacobian(Vector x) {
        
        double dx = activation.getDerivative(x.dotProduct(getParameter()));
        Matrix jacob = getParameter().transpose();
        return jacob.times(dx);
    }
    
    /**
     * Metodi palauttaa funktion Jakobiaanin parametrin suhteen pisteessä x.
     * @param x piste
     * @return Jakobiaani
     */
    @Override
    public Matrix parameterJacobian(Vector x) {
        double dx = activation.getDerivative(x.dotProduct(getParameter()));
        x.transposeToDestination(jacobianMemory);
        jacobianMemory.timesToDestination(dx, jacobianMemory);
        return jacobianMemory;
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
    
    public void setUnoptimized() {
        this.optimized = false;
    }
    
}
