/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions;

/**
 *
 * @author simokork
 */
public class CompositionFunction extends VectorFunction {

    private VectorFunction first;
    private VectorFunction second;
    
    
    @Override
    public Vector value(Vector x) {
        
        return first.value(second.value(x));
        
    }

    @Override
    public int inputSize() {
        return second.inputSize();
    }

    @Override
    public int outputSize() {
        return first.outputSize();
    }
    
    
    
}
