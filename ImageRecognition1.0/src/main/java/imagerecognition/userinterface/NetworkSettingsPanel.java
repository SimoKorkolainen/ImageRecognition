/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Simo
 */
public class NetworkSettingsPanel extends JPanel {
    private NetworkStructurePanel structure;
    private TrainingPanel training;
    public NetworkSettingsPanel(UserInterface ui) {
    
        ControlButtonListener listener = new ControlButtonListener(ui, this);
        
        super.setLayout(new GridLayout(2, 1));
        structure = new NetworkStructurePanel(listener);
        super.add(structure);
        training = new TrainingPanel(listener);
        super.add(training);
        
        
    }

    public NetworkStructurePanel getStructure() {
        return structure;
    }

    public TrainingPanel getTraining() {
        return training;
    }
    
    
}
