/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.userinterface;

import imagerecognition.data.datasets.CifarDataset;
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
    

    
    @Override
    public void run() {
        
        
        
        
        frame = new JFrame();
        
        frame.setVisible(true);
        
        frame.setSize(800, 800);
        
        frame.setTitle("ImageRegognition data visualization");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        

        frame.pack();

        

        
    }
    
    
    
    
    public void createComponents(Container container) {
        CifarDataset data = new CifarDataset(1);
        
        container.setLayout(new GridLayout(20, 5));
        BufferedImage[] imgs = data.getImages();
        int classes[] = data.getClasses();
        int ind = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 5; j++) {
                

                JLabel label = new JLabel(data.getClassTable().getClassName(classes[ind]));

                
                label.setIcon(new ImageIcon(imgs[ind]));
                container.add(label);
                
                ind++;
            }
        }
    
    }
    
}
