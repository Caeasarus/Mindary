/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindmap;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Julian
 */
public class Mindmap{
    
    JFrame frame = new JFrame();
    private MapPanel mindmap;
    
    public Mindmap(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.mindmap = new MapPanel();
        this.mindmap.setMinimumSize(new Dimension(400, 400));
        this.mindmap.setPreferredSize(new Dimension(400, 400));
        
        frame.add(mindmap);
        
        frame.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                mindmap.processKey(e);
            }

        });
        frame.requestFocus();
        
        MindPoint mindpoint1 = new MindPoint(5, 5, 3);
        MindPoint mindpoint2 = new MindPoint(200, 100, 3);
        
        mindpoint1.addOutConnection(mindpoint2);
        
        this.mindmap.addMindpoint(mindpoint1);
        this.mindmap.addMindpoint(mindpoint2);
        
        
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        new Mindmap();
    }
    
}
