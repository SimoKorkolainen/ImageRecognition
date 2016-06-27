/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.data.ClassifiedVector;
import imagerecognition.neuralnetwork.NeuralNetwork;
import imagerecognition.neuralnetwork.layers.NetworkLayer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Simo
 */
public class NeuralNetworkVisualizer extends JPanel {
    private UserInterface ui;
    private int imageWidth;
    private int imageHeight;

    public NeuralNetworkVisualizer(UserInterface ui, int imageWidth, int imageHeight) {
        this.ui = ui;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        super.setBorder(BorderFactory.createLineBorder(Color.gray));
        super.setVisible(true);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle bounds = super.getBounds();
        
        int centerX = bounds.width / 2;
        int centerY = bounds.height / 2;
        int size = Math.min(bounds.width, bounds.height) / 2;
        
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        int layerGap = 80;
        int padding = 2;
        g2d.setColor(new Color(50, 50, 50, 255));
        g2d.drawRect(centerX - imageWidth - layerGap, centerY - imageHeight / 2, imageWidth, imageHeight);
            
        NetworkLayer layer = ui.getNetwork().getLayer(1);
        int neuronSize = size / layer.getLayerSize();
        int x = centerX - neuronSize / 2;
        int xImg = centerX - layerGap;
        int yImg = centerY;
        for(int i = 0; i < layer.getLayerSize(); i++) {
            int y = i * neuronSize + centerY - size / 2;

            g2d.setColor(new Color(0, 0, 0, 70));
            g2d.drawLine(x, y, xImg, yImg);
        }
        
        for (int j = 1; j < ui.getNetwork().getNumberOfLayer(); j++) {
            layer = ui.getNetwork().getLayer(j);

            neuronSize = size / layer.getLayerSize();
            

            x = centerX - neuronSize / 2 + (j - 1) * layerGap;
            for (int i = 0; i < layer.getLayerSize(); i++) {

                int y = i * neuronSize - neuronSize / 2 + padding + centerY - size / 2;
                g2d.setColor(new Color(50, 50, 50, 255));
                g2d.fillOval(x, y, neuronSize - 2 * padding, neuronSize - 2 * padding);
                
                if (j + 1 < ui.getNetwork().getNumberOfLayer()) {
                    NetworkLayer next = ui.getNetwork().getNextLayer(j);
                    
                    for (int k = 0; k < next.getLayerSize(); k++) {
                        int nextNeuronSize = size / next.getLayerSize();
                        int xNext = centerX + j * layerGap;
                        int yNext= k * nextNeuronSize + padding + centerY - size / 2;
                        g2d.setColor(new Color(0, 0, 0, 70));
                        g2d.drawLine(x + neuronSize / 2, y + neuronSize / 2 - padding, xNext, yNext);
                    }
                    
                }

            }
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
      return new Dimension(500, 500);
    }
    

}
