/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Simo
 */
public class GraphPlotter extends JPanel {
    
    private MovingGraph first;
    private MovingGraph second;
    private int padding;
    public GraphPlotter(MovingGraph first, MovingGraph second) {
        super.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        this.first = first;
        this.second = second;
        this.padding = 10;
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(new Color(50, 50, 255));
        paintGraph(first, g2d);
        g2d.setColor(new Color(255, 255, 50));
        paintGraph(second, g2d);
        
        paintYAxel(g2d);
        paintXAxel(g2d);
    }
    
    
    public void paintGraph(MovingGraph graph, Graphics2D g2d) {
        Rectangle bounds = super.getBounds();
        
        double width = bounds.width - 2 * padding;
        double height = bounds.height - 2 * padding;
        
        for (int i = 1; i < graph.getReadySize(); i++) {
            int y1 = (int)(padding + height - graph.getY(i - 1) * height);
            int y2 = (int)(padding + height - graph.getY(i) * height);
            int x1 = (int) (padding + width * (i - 1) / graph.getSize());
            int x2 = (int) (padding + width * i / graph.getSize());
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
    
    public void paintYAxel(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(50, 50, 50));
        
        Rectangle bounds = super.getBounds();
        
        double height = bounds.height - 2 * padding;
        
        g2d.drawLine(padding, padding, padding, (int) (padding + height));
        int ticks = 5;
        int fontSize = 14;
        
        g2d.setFont(new Font("Sans serif", Font.PLAIN, fontSize));
        
        for (int i = 1; i <= ticks; i++) {
        
            int y = (int) (padding + height - height * i / ticks);
            int x1 = padding;
            int x2 = padding + 5;
            
            g2d.drawLine(x1, y, x2, y);
            g2d.drawString("" + ((double) i / ticks), x2 + 1, y + fontSize / 2 - 2);
        }
    
    }
    
    public void paintXAxel(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(50, 50, 50));
        
        Rectangle bounds = super.getBounds();
        
        double width = bounds.width - 2 * padding;
        double height = bounds.height - 2 * padding;
        
        g2d.drawLine(padding, (int) (height + padding), (int) (padding + width), (int) (height + padding));

    
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }
}
