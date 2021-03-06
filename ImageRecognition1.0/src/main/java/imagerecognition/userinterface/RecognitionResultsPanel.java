/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Simo
 */
public class RecognitionResultsPanel extends JPanel {
    
    private BufferedImage image;

    public RecognitionResultsPanel(BufferedImage image, double predictions[], String classNames[], int correctN) {
        this.image = image;
        super.setBorder(BorderFactory.createLineBorder(Color.gray));
        super.setLayout(new GridBagLayout());
        super.setDoubleBuffered(true);
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.insets = new Insets(5, 5, 5, 5);
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        super.add(new JLabel(new ImageIcon(image)), constraints);
        
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(predictions.length, 1));
        
        

        for (int i = 0; i < predictions.length; i++) {
            
            
            grid.add(new PredictionPanel(i == correctN, predictions[i], classNames[i]));
        
        }
        
        constraints.gridx = 1;
        constraints.weightx = 6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.EAST;
        
        super.add(grid, constraints);
    }
    
    
    
    
}
