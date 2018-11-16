/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindmap;

import java.awt.event.MouseEvent;

/**
 *
 * @author Marc
 */
public class Movement {
    
    private int mouseX;
    private int mouseY;
    
    private MapPanel mapPanel;
    
    public Movement(MapPanel mapPanel){
        this.mouseX = 0;
        this.mouseY = 0;
        
        this.mapPanel = mapPanel;
    }

    public void processDraged(MouseEvent e){
        
        
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
}
