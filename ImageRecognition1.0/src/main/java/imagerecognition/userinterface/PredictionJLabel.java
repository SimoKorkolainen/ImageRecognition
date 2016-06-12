/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.data.classification.ClassTable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *
 * @author Simo
 */
public class PredictionJLabel extends JLabel {
    private boolean correct;
    private double probability;
    private String name;

    public PredictionJLabel(boolean correct, double probability, String name) {
        this.correct = correct;
        this.probability = probability;
        this.name = name;
        super.setText(name);
        super.setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.white);
        g2d.fillRect(2, 2, (int) ((probability * 200) - 4), 9);
        g2d.setColor(myColor());
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(0, 0, (int) (probability * 200), 13);
        super.paintComponent(g);
    }
    
    public Color myColor() {
        if (correct) {
            return Color.green;
        }
        return Color.red;
    }
}
