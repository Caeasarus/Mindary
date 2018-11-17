/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindmap;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Julian
 */
public class MindPoint{

    public static final int STANDART_WIDTH = 100;
    public static final int STANDART_HEIGHT = 50;
    
    private int x;
    private int y;
    private int width;
    private int height;
    private int edgeThickness;
    private ArrayList<MindPoint> outConnections;
    private boolean processingInput;
    private String text;

    public MindPoint(int x, int y, int edgeThickness){
        this.x = x;
        this.y = y;
        this.width = STANDART_WIDTH;
        this.height = STANDART_HEIGHT;
        this.edgeThickness = edgeThickness;
        this.outConnections = new ArrayList<>();
        this.text = "";
    }

    void draw(Graphics2D g2d, int dx, int dy){
        Color old = g2d.getColor();
        outConnections.forEach((outConnection) -> {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(this.x - dx + this.width / 2,
                    this.y - dy + this.height / 2,
                    outConnection.getX() - dx + outConnection.getWidth() / 2,
                    outConnection.getY() - dy + outConnection.getHeight() / 2);

            g2d.setColor(old);
            outConnection.overdrawConnections(g2d, dx, dy);
        });
              
        this.overdrawConnections(g2d, dx, dy);
    }

    public void overdrawConnections(Graphics2D g2d, int dx, int dy){
        Color old = g2d.getColor();
        g2d.setColor(Color.BLACK);
        g2d.fill(this.getEllipse(dx, dy));
        g2d.setColor(Color.WHITE);
        g2d.fill(this.getInnerEllipse(dx, dy));
        g2d.setColor(Color.BLACK);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        g2d.drawString(this.getPrintingText(), this.x - dx + this.width / 2
                - fontMetrics.stringWidth(this.getPrintingText()) / 2,
                this.y - dy + this.height / 2 + fontMetrics.getHeight() / 4);
        g2d.setColor(old);
        
        if(STANDART_WIDTH < 
                fontMetrics.stringWidth(this.getPrintingText()) + 10){
            int tempWith = this.width;
            this.width = fontMetrics.stringWidth(this.getPrintingText()) + 10;
            this.x -= (width - tempWith) / 2;
            java.awt.geom.Rectangle2D  rect = 
                    new java.awt.geom.Rectangle2D.Double
        (this.x - dx + 10, ((this.y - dy) + (this.height/ 2)) - 
                fontMetrics.getHeight() / 2 , 
                fontMetrics.stringWidth(this.getPrintingText()) - 10, 
                fontMetrics.getHeight());
            if(!this.getEllipse(dx, dy).contains(rect)){
                this.height += 2;
                this.y -= 1;
            }
        }else{
            width = STANDART_WIDTH;
        }         
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getEdgeThickness(){
        return this.edgeThickness;
    }

    public ArrayList<MindPoint> getOutConnections(){
        return this.outConnections;
    }

    public boolean addOutConnection(MindPoint outConnection){
        return this.outConnections.add(outConnection);
    }

    public boolean removeOutConnection(MindPoint outConnection){
        return this.outConnections.remove(outConnection);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setEdgeThickness(int edgeThickness){
        this.edgeThickness = edgeThickness;
    }

    public Ellipse2D getInnerEllipse(int dx, int dy){
        Ellipse2D.Double innerEllipse = new Ellipse2D.Double(this.x
                + this.edgeThickness - dx, this.y + this.edgeThickness - dy,
                this.width - this.edgeThickness * 2, this.height
                - this.edgeThickness * 2);

        return innerEllipse;
    }

    public Ellipse2D getEllipse(int dx, int dy){
        Ellipse2D.Double Ellipse = new Ellipse2D.Double(this.x - dx, this.y
                - dy, this.width, this.height);
        return Ellipse;
    }

    public void processInput(boolean b){
        this.processingInput = b;
    }

    public boolean isProcessingInput(){
        return processingInput;
    }

    public void processInput(KeyEvent e){
        String allowed_character = "([a-zöäü A-ZÄÖÜ0-9ß:'#,()_])*" + "-*";
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                this.processInput(false);
                break;
            case KeyEvent.VK_DELETE:
                break;
            case KeyEvent.VK_BACK_SPACE:
                if(!this.processingInput || text.length() <= 0){
                    break;
                }
                this.text = this.text.substring(0, text.length() - 1);
                break;
            default:
                if(!this.processingInput /*|| !(text.length() < 20)*/){
                    break;
                }
                if(!String.valueOf(e.getKeyChar()).matches(allowed_character)
                        || text.length() == 0 && e.getKeyChar() == ' '){
                    break;
                }
                this.text += e.getKeyChar();
                break;
        }
    }

    private String getPrintingText(){
        if(!this.processingInput){
            return this.text;
        }else{
            return ">" + this.text + "<";
        }
    }
}
