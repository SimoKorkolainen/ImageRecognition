/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import java.awt.Color;
import java.awt.Dimension;
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
public class TrainingPanel extends JPanel {

    private JTextField trainingImages;
    private JTextField trainingIterations;
    private JTextField learningRate;
    public TrainingPanel(ControlButtonListener listener) {
            
        super.setBorder(BorderFactory.createLineBorder(Color.gray));
        super.setLayout(new GridLayout(2, 1));
        JLabel title = new JLabel("Network training");
        title.setFont(new Font("Sans serif", Font.PLAIN, 20));
        super.add(title);
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(4, 2));
        
        grid.add(new JLabel("Training images:"));
        trainingImages = new JTextField();
        grid.add(trainingImages);
        grid.add(new JLabel("Training iterations:"));
        trainingIterations = new JTextField();
        grid.add(trainingIterations);
        grid.add(new JLabel("Learning rate:"));
        learningRate = new JTextField();
        grid.add(learningRate);
        JButton start = new JButton("Start training");
        start.setActionCommand("Start training");
        start.addActionListener(listener);
        
        grid.add(start);
        
        JButton stop = new JButton("Stop");
        stop.setActionCommand("Stop training");
        stop.addActionListener(listener);
        
        grid.add(stop);
        super.add(grid);
    }
    
    public int getTrainingImages() {
    
        try {
            int images = Integer.parseInt(trainingImages.getText());
            images = Math.min(Math.max(images, 50), 10000);
            trainingImages.setText("" + images);
            return images;
        } catch (NumberFormatException e){
            String message = "It's 50 now.";
            trainingImages.setText(message);
            return 50;
        }
    
    }
    
    public int getTrainingIterations() {
    
        try {
            int iters = Integer.parseInt(trainingIterations.getText());
            iters = Math.min(Math.max(iters, 0), 1000);
            trainingIterations.setText("" + iters);
            return iters;
        } catch (NumberFormatException e){
            String message = "It's 20 now.";
            trainingIterations.setText(message);
            return 20;
            
        }
    
    }
    public double getLearningRate() {
    
        try {
            double rate = Double.parseDouble(learningRate.getText());
            rate = Math.max(rate, 0);
            learningRate.setText("" + rate);
            return rate;
        } catch (NumberFormatException e){
            String message = "It's 0 now.";
            learningRate.setText(message);
            return 0;
            
        }
    
    }
   
}
