/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.functions.activation.ActivationFunction;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.NeuralNetworkBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Simo
 */
public class ControlButtonListener implements ActionListener {
    private UserInterface ui;
    private NetworkSettingsPanel settings;

    public ControlButtonListener(UserInterface ui, NetworkSettingsPanel settings) {
        this.ui = ui;
        this.settings = settings;
    }



    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
        
            case "Clear": handleClearButton();
                          break;
            case "Create": handleCreateButton();
                          break;
            case "Start training": handleStartTrainingButton();
                          break;
            case "Stop training": handleStopTrainingButton();
                          break;
            default: break;
        
        }
        ui.update();
    }
    
    
    
    public void handleClearButton() {
        ui.stopTraining();
        ui.setNetwork(NeuralNetworkBuilder.onlySoftmax(32 * 32 * 3, 10, 0.01));
    }
    public void handleCreateButton() {
        ui.stopTraining();
        ActivationFunction act = settings.getStructure().getActivation();
        int[] hidden = settings.getStructure().getHiddenLayers();
        
        NeuralNetwork net = NeuralNetworkBuilder.createClassificationNetwork(32 * 32 * 3, hidden, 10, 0.01, act);
        
        ui.setNetwork(net);
    }
    
    public void handleStartTrainingButton() {
        ui.stopTraining();
        ui.startTraining(settings.getTraining().getTrainingImages(), settings.getTraining().getTrainingIterations(), settings.getTraining().getLearningRate());
        
    }
    
    public void handleStopTrainingButton() {
    

        ui.stopTraining();
        
    }
}
