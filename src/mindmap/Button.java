/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author Julian
 */
public class Button{

    private int x;
    private int y;
    private int width;
    private int height;
    private Type type;

    protected enum Type{
        LEFT, RIGHT, UP, DOWN, ADD
    }

    protected Button(int x, int y, int width, int height, Type rotation){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = rotation;
    }

    protected void draw(Graphics2D g2d){
        Color old = g2d.getColor();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(this.x, this.y, this.width, this.height);
        g2d.setColor(Color.BLACK);
        Polygon p;
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        switch(this.type){
            case LEFT:
                xPoints[0] = this.x + this.width - 1;
                xPoints[1] = this.x + this.width - 1;
                xPoints[2] = this.x + 1;
                yPoints[0] = this.y + 1;
                yPoints[1] = this.y + this.height - 1;
                yPoints[2] = this.y + this.height / 2;
                p = new Polygon(xPoints, yPoints, 3);
                g2d.fillPolygon(p);
                break;
            case RIGHT:
                xPoints[0] = this.x + 1;
                xPoints[1] = this.x + 1;
                xPoints[2] = this.x + this.width - 1;
                yPoints[0] = this.y + 1;
                yPoints[1] = this.y + this.height - 1;
                yPoints[2] = this.y + this.height / 2;
                p = new Polygon(xPoints, yPoints, 3);
                g2d.fillPolygon(p);
                break;
            case UP:
                xPoints[0] = this.x + 1;
                xPoints[1] = this.x + this.width - 1;
                xPoints[2] = this.x + this.width / 2;
                yPoints[0] = this.y + this.height - 1;
                yPoints[1] = this.y + this.height - 1;
                yPoints[2] = this.y + 1;
                p = new Polygon(xPoints, yPoints, 3);
                g2d.fillPolygon(p);
                break;
            case DOWN:
                xPoints[0] = this.x + 1;
                xPoints[1] = this.x + this.width - 1;
                xPoints[2] = this.x + this.width / 2;
                yPoints[0] = this.y + 1;
                yPoints[1] = this.y + 1;
                yPoints[2] = this.y + this.height - 1;
                p = new Polygon(xPoints, yPoints, 3);
                g2d.fillPolygon(p);
                break;
            case ADD:
                g2d.drawLine(this.x + this.width / 2, this.y + 1, this.x
                        + this.width / 2, this.y + this.height - 1);
                g2d.drawLine(this.x + 1, this.y + this.height / 2, this.x
                        + this.width - 1, this.y + this.height / 2);
                break;
        }
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

    public Type getType(){
        return type;
    }
}
