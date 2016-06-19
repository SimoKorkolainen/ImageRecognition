/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions.general;

import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;
import imagerecognition.functions.activation.ExponentialFunction;
import imagerecognition.neuralnetwork.layers.NetworkLayer;

/**
 * Softmax-funktiota kÃ¤ytettÃ¤Ã¤n luokitteluun. Softmax-funktio on yleensÃ¤ 
 * luokitteluun kÃ¤ytettÃ¤vÃ¤n neuroverkon viimeinen kerros.
 * Softmax-funktion kuva-alkion komponenttien arvoden summa on yksi 
 * ja kaikkien komponenttien arvot ovat positiivia.
 */
public class SoftmaxFunction extends NeuronLayerFunction {
    
    private Matrix jacobianMemory; //for avoiding costly allocations
    private Matrix gradientMemory;
    private Matrix gradientTransposeMemory;
    
    /**
     * Konstruktori luo luokitteluun tarkoitetun funktion, jota
     * käytetään yleensä syötteitä luokittelevan neuroverkon 
     * viimeisenä kerroksena
     * @param weightRandomness painojen alustuksen satunnaisuus
     * @param inputSize syötteen koko (ts. kuinka monta piirrettä syötteinä)
     * @param outputSize tulosteen koko (ts. kuinka monta luokkaa luokiteltavana)
     */
    public SoftmaxFunction(double weightRandomness, int inputSize, int outputSize) {
        super(new ExponentialFunction(), weightRandomness, inputSize, outputSize);
        jacobianMemory = Matrix.zeros(outputSize(), inputSize());
        gradientMemory = Matrix.zeros(1, inputSize());
        gradientTransposeMemory = Matrix.zeros(inputSize(), 1);
    }
    /**
     * Konstruktori luo luokitteluun tarkoitetun funktion, jota
     * käytetään yleensä syötteitä luokittelevan neuroverkon 
     * viimeisenä kerroksena.
     * @param weights painovektorit
     */
    public SoftmaxFunction(Vector weights[]) {
        super(new ExponentialFunction(), weights);
        jacobianMemory = Matrix.zeros(outputSize(), inputSize());
        gradientMemory = Matrix.zeros(1, inputSize());
        gradientTransposeMemory = Matrix.zeros(inputSize(), 1);
    }
    /**
     * Metodi palautaa luokittelutuloksen syötteellä x.
     * @param x syöte
     * @return tuloste
     */
    @Override
    public Vector value(Vector x) {
    
        Vector value = super.value(x);

        return value.times(1.0 / value.sum());
    
    }
    /**
     * Funktion Jakobiaani syötteiden suhteen pisteessä x.
     * @param x piste x
     * @return Jakobiaani
     */
    @Override
    public Matrix jacobian(Vector x) {

        Vector superValue = super.value(x);
        Vector value = value(x);
        
        
        Matrix dy = super.jacobian(x);
        
       
        
        Matrix dySum = dy.columnSum();
        
        
        value.productToDestination(dySum, jacobianMemory);
        
        jacobianMemory.timesToDestination(-1, jacobianMemory);

        
        
        jacobianMemory.plusToDestination(dy, jacobianMemory);
        
        jacobianMemory.timesToDestination(1.0 / superValue.sum(), jacobianMemory);
        
        return jacobianMemory;
    
    }


    @Override
    protected void trainFunction(int i, double learningRate) {

        Matrix outputErrorGradient = getLayer().getOutputErrorGradient();
        
        
        NeuronFunction function = getFunctions()[i];
        
        Matrix jacob = getFunctionTrainingJacobian(i);
                

        outputErrorGradient.productToDestination(jacob, gradientMemory);
        

        gradientMemory.transposeToDestination(gradientTransposeMemory);
        
        gradientTransposeMemory.timesToDestination(-learningRate, gradientTransposeMemory);
        
    
        function.getParameter().plusToDestination(gradientTransposeMemory, function.getParameter());

    }
    
    
    
    /**
     * Metodi palauttaa funktion i:nnen komponentin parametrien vaikutuksesta
     * tulosteeseen kertovan Jakobiaanin.
     * @param i
     * @return Jakobiaani
     */
    public Matrix getFunctionTrainingJacobian(int i) {

        int layerN = getLayer().getLayerNumber();
        
        NetworkLayer previous = getLayer().getNetwork().getPreviousLayer(layerN);
        
        Vector input = previous.getValue();
        
        Vector superVal = super.value(input);
        
        double superValSum = superVal.sum();

        double superValSumSqr = superValSum * superValSum;
        
        superVal.timesToDestination(- 1.0 / superValSumSqr, superVal);
        
        superVal.set(i, 0, superVal.get(i, 0) + 1.0 / superValSum);
        
        Matrix superParGrad = getFunctions()[i].parameterJacobian(input);
        
        
        superVal.productToDestination(superParGrad, jacobianMemory);
        
                
        
        return jacobianMemory;
    }
    
}
