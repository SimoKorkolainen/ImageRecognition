/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.userinterface;

import imagerecognition.data.datasets.CifarDataset;
import imagerecognition.neuralnetwork.NeuralNetwork;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * UserInterface on käyttöliittymäluokka. Joka tällä hetkellä visualisoi dataa.
 */
public class UserInterface implements Runnable {
    
    private JFrame frame;
    private NeuralNetwork network;
    private CifarDataset dataset;
    private ImageRecognitionResultsVisualizer viz;
    public UserInterface(NeuralNetwork network, CifarDataset dataset) {
        this.network = network;
        this.dataset = dataset;
    }
    
    

    
    @Override
    public void run() {
        
        
        
        
        frame = new JFrame();
        
        frame.setVisible(true);
        
        frame.setSize(800, 800);
        
        frame.setTitle("ImageRegognition visualization");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        

        frame.pack();

        

        
    }
    
    
    
    
    public void createComponents(Container container) {
        viz = new ImageRecognitionResultsVisualizer(6, 3, dataset, network);
        container.add(viz);

    
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void update() {

        
        if (frame != null  && viz != null) {
            System.out.println("Updating!!!!!!!!!!!!!!!1");
            viz.update();
            frame.revalidate();
            frame.repaint();
        }
    }
    
}
