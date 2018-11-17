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
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Julian
 */
public class MindPoint{

    private int x;
    private int y;
    private int width;
    private int height;
    private int edgeThickness;
    private ArrayList<MindPoint> outConnections;
    private boolean processingInput;
    private String text;

    public MindPoint(int x, int y, int width, int height, int edgeThickness){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        final MindPoint other = (MindPoint) obj;
        if(this.x != other.x){
            return false;
        }
        if(this.y != other.y){
            return false;
        }
        if(this.width != other.width){
            return false;
        }
        if(this.height != other.height){
            return false;
        }
        if(this.edgeThickness != other.edgeThickness){
            return false;
        }
        if(!Objects.equals(this.text, other.text)){
            return false;
        }
        if(!Objects.equals(this.outConnections, other.outConnections)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        hash = 97 * hash + this.width;
        hash = 97 * hash + this.height;
        hash = 97 * hash + this.edgeThickness;
        hash = 97 * hash + Objects.hashCode(this.outConnections);
        hash = 97 * hash + Objects.hashCode(this.text);
        return hash;
    }
}
