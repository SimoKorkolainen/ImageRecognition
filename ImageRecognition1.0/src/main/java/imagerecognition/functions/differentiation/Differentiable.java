/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.functions.differentiation;

import imagerecognition.math.Vector;
import imagerecognition.math.Matrix;

/**
 *
 * Derivoituvuuden määrittelevä rajapinta.
 */
public interface Differentiable {
    /**
     * Metodi palauttaa funktion Jakobin matriisin pisteessä x.
     * Kts. https://en.wikipedia.org/wiki/Jacobian_matrix_and_determinant.
     * @param x piste
     * @return Jakobin matriisi
     */
    public Matrix jacobian(Vector x);
}
