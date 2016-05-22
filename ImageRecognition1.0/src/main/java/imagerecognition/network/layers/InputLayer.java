/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.network.layers;

/**
 *
 * @author Simo
 */
public class InputLayer extends NetworkLayer {

    public InputLayer(int size) {
        super(size);
    }

    public void setInputs(double inputs[]) {
        setValues(inputs);
    }
    
    
    @Override
    public void updateLayer(NetworkLayer input) {
    }
    
}
