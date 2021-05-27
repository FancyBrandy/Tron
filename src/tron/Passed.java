/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;
// import statements
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author Chen Danqing
 */
public class Passed extends Stuff{

    public boolean exposed=false;// the piece is exposed if it is passed by a player
        /**
 constructor of the class Passed
 * @param  x y coordinates, the width and the height of the unit of the trace of the players and the color of the trace
 * @return constructor of the class Passed
 */
     public Passed(int x, int y, int width, int height, Image image,Color c)
    {
        super(x, y, width, height, image);
        this.c=c;
    }
     /**
 whether the trace is it by any other players
 * @param  Player other
 * @return boolean, true if the trace unit is hit by another play and false otherwise
 */
    public boolean col(Player other)// this method checks whether the Passed piece have intersection with player
    {
        Rectangle rect = new Rectangle(x, y, width, height);// we have a rectangle box around the sprite
        Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);   
     
        return rect.intersects(otherRect);// whether the rectangle intesects
    }// if the light line intersects with any players, then it is 
    
    /**
 check whether the place as been passed by the motor of the players or not
 * @param  Graphics g
 * @return draw out the trace unit is the place is exposed(being passed by a player already)
 */
    public void show(Graphics g)
    {
        if(exposed)
        {
            g.drawImage(image, x, y,this.c,null);
        }
    }
}
