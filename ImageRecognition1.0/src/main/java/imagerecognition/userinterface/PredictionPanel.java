/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.data.classification.ClassTable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Simo
 */
public class PredictionPanel extends JPanel {
    private boolean correct;
    private double probability;
    private String name;

    public PredictionPanel(boolean correct, double probability, String name) {
        this.correct = correct;
        this.probability = probability;
        this.name = name;
        JLabel nameLabel = new JLabel(name);
        nameLabel.setOpaque(false);
        super.setOpaque(false);
        super.add(nameLabel);
    }
    
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Rectangle bounds = super.getBounds();
        int startX = 0;
        int startY = 1;
        
        int w = (int) (probability * 200);
        int h = bounds.height - 2;
        
        g2d.setColor(myColor());
        
        g2d.fillRect(startX, startY, w, h);
        super.paintComponent(g);
    }
    
    public Color myColor() {
        if (correct) {
            return new Color(100, 255, 100);
        }
        return new Color(255, 100, 100);
    }
    
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(5, 200);
    }

}
