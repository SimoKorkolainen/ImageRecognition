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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
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
        nameLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        nameLabel.setOpaque(false);
        nameLabel.setFont(new Font("Sans serif", Font.PLAIN, 15));
        super.setOpaque(false);
        super.add(nameLabel);
    }
    
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        Rectangle bounds = super.getBounds();
        int startX = 0;
        int startY = 0;
        
        int w = (int) (probability * bounds.width);
        int h = bounds.height - 1;
        
        g2d.setColor(myColor());
        
        g2d.fillRect(startX, startY, w, h);
        
        g2d.setColor(new Color(150, 150, 150));
        g2d.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
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
