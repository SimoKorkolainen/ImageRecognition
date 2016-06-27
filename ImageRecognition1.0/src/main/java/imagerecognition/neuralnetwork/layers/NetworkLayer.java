/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.neuralnetwork.layers;

import imagerecognition.neuralnetwork.layers.NeuronLayerFunction;
import imagerecognition.util.Matrix;
import imagerecognition.util.Vector;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.training.Trainable;

/**
 *
 * NetworkLayer on neuroverkon kerrosta kuvaava luokka.
 */
public class NetworkLayer implements Trainable {
    private NeuronLayerFunction function;
    private Vector value;
    private Matrix errorGradient;
    private NeuralNetwork network;
    private int layerNumber;

    /**
     * Syötekerroksen konstruktori.
     * @param size kerroksen koko
     */
    public NetworkLayer(int size) {
        errorGradient = Matrix.zeros(1, size);
        value = Vector.zero(size);
    }
    
    /**
     * Normaalin kerroksen konstruktori.
     * @param function koko kerroksessa käytetty funktio
     */
    public NetworkLayer(NeuronLayerFunction function) {
        this(function.outputSize());
        this.function = function;
    }
    
    
    /**
     * Metodi päivittää kerroksen.
     *
     */
    public void updateLayer() {
        if (function == null) {
            return;
        }
        
        NetworkLayer prev = network.getPreviousLayer(layerNumber);
        
        value = function.value(prev.getValue());
    }
    
    public void updateOutputErrorGradient() {

        NetworkLayer next = network.getNextLayer(layerNumber);

        next.getOutputErrorGradient().productToDestination(next.getFunction().jacobian(value), errorGradient);
    
    }

    public Vector getValue() {
        return value;
    }
    
    public Matrix getOutputErrorGradient() {
        return errorGradient;
    }

    public void setValue(Vector value) {
        this.value = value;
    }
    
    public void setOutputErrorGradient(Vector vect) {
        this.errorGradient = vect;
    }

    /**
     * Metodi päivittää neuroverkon kerroksen painot.
     * @param learningRate oppimisnopeutta säätelevä parametri
     */
    @Override
    public void train(double learningRate) {
        function.train(learningRate);
    }
    
    /**
     * Metodi antaa kerrokselle tiedon neuroverkosta, johon kerros kuuluu.
     * Metodi antaa kerrokselle myös tiedon kerroksen sijainnista neuroverkossa.
     * @param network
     * @param layerNumber 
     */
    public void setNeuralNetwork(NeuralNetwork network, int layerNumber) {
        function.setLayer(this);
        this.network = network;
        this.layerNumber = layerNumber;
    }

    public NeuralNetwork getNetwork() {
        return network;
    }

    public int getLayerNumber() {
        return layerNumber;
    }

    public NeuronLayerFunction getFunction() {
        return function;
    }
    
    public int getLayerSize() {
        return function.outputSize();
    }
    
    
}
