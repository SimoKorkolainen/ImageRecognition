/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.functions;

/**
 *
 * @author Simo
 */
public abstract class ActivationFunction implements Differentiable {
    private String name;

    public ActivationFunction(String name) {
        this.name = name;
    }
    
    public abstract double value(double x);
    
    public String getName() {
        return name;
    }
}
