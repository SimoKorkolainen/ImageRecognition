/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.userinterface;

import imagerecognition.data.CifarDataset;
import imagerecognition.util.Vector;
import imagerecognition.neuralnetwork.NeuralNetwork;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Simo
 */
public class ImageRecognitionResultsVisualizer extends JPanel {
    private int width;
    private int height;
    private BufferedImage[] imgs;
    private CifarDataset dataset;
    private UserInterface ui;

    public ImageRecognitionResultsVisualizer(int width, int height, CifarDataset dataset, UserInterface ui) {
        this.width = width;
        this.height = height;
        this.dataset = dataset;
        this.ui = ui;
        this.imgs = dataset.getImages();
        this.setDoubleBuffered(true);
        update();
    }


    
    public void update() {
        super.removeAll();
        Vector value = ui.getNetwork().getLayer(1).getValue();
        for (int i = 0; i < value.size(); i++) {
            if (Double.isNaN(value.get(i))) {
                
                
                JLabel fail = new JLabel("Network failure! Please clear the network.");
                fail.setFont(new Font("Sans serif", Font.PLAIN, 30));
                fail.setBorder(BorderFactory.createLineBorder(Color.gray));
                ui.stopTraining();
                super.add(fail);
                return;
            }
        }
        
        
        super.setLayout(new GridLayout(height, width));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                addRecognitionResultsPanel(i * height + j, dataset, ui.getNetwork());

            }
        }
        

        super.revalidate();
        super.repaint();
    }
    
    private void addRecognitionResultsPanel(int i, CifarDataset dataset, NeuralNetwork network) {
        
        BufferedImage img = imgs[i];
        while (network.isInUse()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
            System.out.println("Waiting2...");
        }
        System.out.println("here 1");
        network.setInUse(true);
        Vector pred = network.updateValues(dataset.getTrainingData()[i]);
        network.setInUse(false);
        System.out.println("here 2");
        double prediction[] = new double[pred.size()];
        
        for (int j = 0; j < pred.size(); j++) {
            prediction[j] = pred.get(j);
        }
        
        super.add(new RecognitionResultsPanel(img, prediction, dataset.getClassTable().getClassNames(), dataset.getTrainingData()[i].getPointClass()));
    
    }
    
    
}
