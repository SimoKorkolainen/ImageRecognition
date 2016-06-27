/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.neuralnetwork.activation.ActivationFunction;
import imagerecognition.neuralnetwork.activation.FunctionMaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Simo
 */
public class NetworkStructurePanel extends JPanel {
    private JTextField activation;
    private JTextField hiddenLayers;
    public NetworkStructurePanel(ControlButtonListener listener) {
        super.setBorder(BorderFactory.createLineBorder(Color.gray));
        super.setLayout(new GridLayout(2, 1));
        JLabel title = new JLabel("Network structure");
        title.setFont(new Font("Sans serif", Font.PLAIN, 20));
        super.add(title);
        this.activation = new JTextField();
        this.hiddenLayers = new JTextField();
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 2));
        grid.add(new JLabel("Hidden layers:"));
        grid.add(hiddenLayers);
        grid.add(new JLabel("Activation function:"));
        grid.add(activation);

        JButton create = new JButton("Create");
        create.setActionCommand("Create");
        create.addActionListener(listener);
        
        grid.add(create);
        
        JButton clear = new JButton("Clear");
        clear.setActionCommand("Clear");
        clear.addActionListener(listener);
        grid.add(clear);
        super.add(grid);
    }
    
    
    public ActivationFunction getActivation() {
        return FunctionMaker.make(activation.getText());
    }
    
    public int[] getHiddenLayers() {
        
        String[] numbers = hiddenLayers.getText().split(",");
        
        int[] hidden = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            try {
                hidden[i] = Integer.parseInt(numbers[i].trim());
            } catch (NumberFormatException e) {
                hidden[i] = 10;
            }
            hidden[i] = Math.min(hidden[i], 30);
        }
        
        return hidden;
    }
}
