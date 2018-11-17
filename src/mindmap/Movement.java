/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindmap;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

/**
 *
 * @author Marc
 */
public class Movement {
    
    private int mouseX;
    private int mouseY;
    
    private MapPanel mapPanel;
    
    private Cursor mouseCursor;
    private boolean move;
   
    
    public Movement(MapPanel mapPanel){
        this.mouseX = 0;
        this.mouseY = 0;
        this.move = false;
        
        this.mapPanel = mapPanel;
    }

    public void processDragged(MouseEvent e){
        
        if(!move) return;
        
        int directionX =  (this.mouseX - e.getX());
        int directionY =  (this.mouseY - e.getY());

        if(directionX != 0)
            directionX /= Math.abs(directionX);
        if(directionY != 0)
            directionY /= Math.abs(directionY);

        this.mapPanel.setDx(this.mapPanel.getDx() - directionX);
        this.mapPanel.setDy(this.mapPanel.getDy() - directionY);

        this.mouseX = e.getX();
        this.mouseY = e.getY();

        this.mapPanel.repaint();
    }
    
    public void processReleased(MouseEvent e){
        mapPanel.setCursor(mouseCursor);
        move = false;
    }
    
    public void processPressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON2){
            mouseCursor = mapPanel.getCursor();
            mapPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            move = true;
        }
    }
}
