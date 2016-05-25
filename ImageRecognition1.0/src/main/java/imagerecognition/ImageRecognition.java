/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition;

import imagerecognition.data.CifarDataset;
import javax.swing.SwingUtilities;

/**
 *
 * @author Simo
 */
public class ImageRecognition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        SwingUtilities.invokeLater(new UserInterface());
    }
    
}
