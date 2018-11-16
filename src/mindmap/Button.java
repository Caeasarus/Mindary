/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindmap;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

/**
 *
 * @author Julian
 */
public class Button{

    private int x;
    private int y;
    private int width;
    private int height;
    private Rotation rotation;

    protected enum Rotation{
        LEFT,RIGHT,UP,DOWN
    }

    protected Button(int x, int y, int width, int height, Rotation rotation){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }
    
    protected void draw(Graphics2D g2d){
        Color old = g2d.getColor();
        g2d.setColor(Color.BLACK);
        g2d.drawRect(this.x, this.y, this.width, this.height);

        
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        switch(this.rotation){
            case LEFT:
                xPoints[0] = this.x+this.width-1;
                xPoints[1] = this.x+this.width-1;
                xPoints[2] = this.x+1;
                yPoints[0] = this.y+1;
                yPoints[1] = this.y+this.height-1;
                yPoints[2] = this.y+this.height/2;
                break;
            case RIGHT:
                xPoints[0] = this.x + 1;
                xPoints[1] = this.x + 1;
                xPoints[2] = this.x+this.width-1;
                yPoints[0] = this.y+1;
                yPoints[1] = this.y+this.height-1;
                yPoints[2] = this.y+this.height/2;
                break;
            case UP:
                xPoints[0] = this.x+1;
                xPoints[1] = this.x+this.width-1;
                xPoints[2] = this.x+this.width/2;
                yPoints[0] = this.y + this.height-1;
                yPoints[1] = this.y + this.height-1;
                yPoints[2] = this.y+1;
                break;
            case DOWN:
                xPoints[0] = this.x + 1;
                xPoints[1] = this.x+this.width-1;
                xPoints[2] = this.x+this.width/2;
                yPoints[0] = this.y+1;
                yPoints[1] = this.y+1;
                yPoints[2] = this.y + this.height-1;
                break;
        }
        Polygon p = new Polygon(xPoints, yPoints,3);
        g2d.fillPolygon(p);
        g2d.setColor(old);
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Rotation getRotation(){
        return rotation;
    }
    
    
}
