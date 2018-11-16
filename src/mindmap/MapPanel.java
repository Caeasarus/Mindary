/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindmap;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Julian
 */
public class MapPanel extends JPanel{

    private int dx;
    private int dy;
    private ArrayList<MindPoint> mindpoints;

    private ArrayList<Button> scrollButtons;

    public MapPanel(){
        this.mindpoints = new ArrayList<>();
        this.scrollButtons = new ArrayList<>();
        this.dy = 0;
        this.dx = 0;
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                processClick(e);
            }

        });
        Button leftButton = new Button(1, 400 - 10, 10, 10,
                Button.Rotation.LEFT);
        Button rightButton = new Button(400 - 20, 400
                - 10, 10, 10,
                Button.Rotation.RIGHT);
        Button upButton = new Button(400 - 10, 0, 10, 10,
                Button.Rotation.UP);
        Button downButton = new Button(400 - 10, 400
                - 20, 10, 10,
                Button.Rotation.DOWN);
        this.scrollButtons.add(leftButton);
        this.scrollButtons.add(rightButton);
        this.scrollButtons.add(upButton);
        this.scrollButtons.add(downButton);

    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        mindpoints.forEach((mindpoint) -> {
            mindpoint.draw(g2d, dx, dy);
        });
        scrollButtons.forEach((button) -> {
            button.draw(g2d);
        });
    }

    public boolean addMindpoint(MindPoint mindpoint){
        return this.mindpoints.add(mindpoint);
    }

    public boolean removeMindpoint(MindPoint mindpoint){
        return this.mindpoints.remove(mindpoint);
    }

    public int getDx(){
        return dx;
    }

    public void setDx(int dx){
        this.dx = dx;
    }

    public int getDy(){
        return dy;
    }

    public void setDy(int dy){
        this.dy = dy;
    }

    private void processClick(MouseEvent e){
        Point clickPoint = e.getPoint();
//        System.out.println(clickPoint.getX() + " " + clickPoint.getY());
        boolean found = false;
        //<editor-fold defaultstate="collapsed" desc="mindpoints">
        for(MindPoint mindpoint : mindpoints){
            if(mindpoint.getInnerEllipse(this.dx, this.dy)
                    .contains(clickPoint.getX(), clickPoint.getY())){
                if(!found){
                    System.out.println("g");
                    mindpoint.processInput(true);
                }
                found = true;
            }else{
                mindpoint.processInput(false);
            }
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="scrollbuttons">
        for(Button button : scrollButtons){
            if(new Rectangle2D.Double(button.getX(), button.getY(),
                    button.getWidth(), button.getHeight())
                    .contains(clickPoint.getX(), clickPoint.getY())){
                switch(button.getRotation()){
                    case LEFT:
                        this.dx -= 5;
                        break;
                    case RIGHT:
                        this.dx += 5;
                        break;
                    case UP:
                        this.dy -= 5;
                        break;
                    case DOWN:
                        this.dy += 5;
                        break;
                }
            }
        }
        //</editor-fold>
        this.repaint();
        //setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    protected void processKey(KeyEvent e){
        for(MindPoint mindpoint : mindpoints){
            if(mindpoint.isProcessingInput()){
                mindpoint.processInput(e);
            }
        }
        this.repaint();
    }
}
