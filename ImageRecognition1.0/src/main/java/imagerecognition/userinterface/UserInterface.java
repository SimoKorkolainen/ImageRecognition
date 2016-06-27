/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagerecognition.userinterface;

import imagerecognition.data.CifarDataset;
import imagerecognition.neuralnetwork.NeuralNetwork;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * UserInterface on käyttöliittymäluokka. Joka tällä hetkellä visualisoi dataa.
 */
public class UserInterface implements Runnable {
    private MovingGraph trainingScore;
    private MovingGraph testingScore;
    private JFrame frame;
    private NeuralNetwork network;
    private CifarDataset dataset;
    private ImageRecognitionResultsVisualizer viz;
    private TrainingThread training;
    public UserInterface(NeuralNetwork network, CifarDataset dataset) {
        this.network = network;
        this.dataset = dataset;
        
    }
    
    

    
    @Override
    public void run() {
        
        
        
        
        frame = new JFrame();
        
        frame.setVisible(true);
        
        frame.setSize(1000, 800);
        
        frame.setTitle("ImageRegognition visualization");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        

        frame.pack();
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    
    
    
    
    public void createComponents(Container container) {
        container.setLayout(new GridLayout(1, 2));
        viz = new ImageRecognitionResultsVisualizer(2, 2, dataset, this);
        viz.setDoubleBuffered(true);
        container.add(viz);
        
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 1));

        trainingScore = new MovingGraph(10);
        
        testingScore = new MovingGraph(10);

        
        grid.add(new GraphPlotter(trainingScore, testingScore, "Training score", "Testing score"));
        
        grid.add(new NeuralNetworkVisualizer(this, 32, 32));

        grid.add(new NetworkSettingsPanel(this));
        
        container.add(grid);
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

    public NeuralNetwork getNetwork() {
        return network;
    }

    public void setNetwork(NeuralNetwork network) {
        this.network = network;
    }

    public CifarDataset getDataset() {
        return dataset;
    }
    
    public void startTraining(int trainingImages, int trainingIterations, double learningRate) {
        dataset = new CifarDataset(trainingImages, 1000);
        training = new TrainingThread(trainingIterations, this, learningRate);
        training.start();
    }
    
    public void stopTraining() {
        if (training != null) {
            training.stopTraining();
        }
    }

    public MovingGraph getTrainingScore() {
        return trainingScore;
    }

    public MovingGraph getTestingScore() {
        return testingScore;
    }
    
    
    
}
