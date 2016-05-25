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
public interface ParametricDifferentiable {
    public Matrix parameterJacobian(Vector x);
}
