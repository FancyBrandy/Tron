/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;
// import statements
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;

/**
 *
 * @author Chen Danqing
 */
public class Stuff {
    /**
     * The coordinates of the top left corner of the sprite
     */
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    public Color c;
    protected Image image;// the image is what we will display on the screen
    
   /**
 constructor of the Stuff class
 * @param  x y coordinates and the size and the image of the "Sprite" in the game
 * @return constructor of the Stuff class
 */
  public Stuff(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }  
    /**
draw the Stuff
 * @param  Graphics g
 * @return draw the Stuff
 */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
    
        /**
whether the stuff (players) collides with the bricks or not
 * @param  Graphics g
 * @return boolean whether the stuff collides with the bricks or not
 */
    public boolean borderCliding(Brick b)// whether something is in collision with the border, specifically for the player class
    {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());        
        return rect.intersects(otherRect);// whether the rectangle intesects
    }
    

// getters and setter of the class
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
