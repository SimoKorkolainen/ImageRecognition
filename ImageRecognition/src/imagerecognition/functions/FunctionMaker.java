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
public class FunctionMaker {
    public static ActivationFunction make(String functionName) {
        switch (functionName) {
            case "Softplus": return new SoftplusFunction();
            case "Sigmoid": return new SigmoidFunction();
            default: return new IdentityFunction();
        }
    }
}
