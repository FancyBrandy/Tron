/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Chen Danqing
 */
public class Player extends Stuff{
    private int velx;
    private int vely;
    public Image trace_Image;
    public ArrayList<Passed> trace = new ArrayList<Passed>();
    /**
 constructor for the player class
 * @param   x y coordinates, the width and the height of the player figure and the x and y velocity
 * @return constructor for the player class
 */
    public Player(int x, int y, int width, int height, Image image,int vx,int vy)
    {
        super(x, y, width, height, image);
        velx=vx;
        vely=vy;
        
    }
    
    /**
define what happens when the player moves in the X direction
 * @param  none
 * @return boolean, whether the player went out of the boundary or not
 */
     public boolean moveX() {
        x += velx;
        if (x + width >= 800 || x <= 0) {
                return true;// the player went out of the boundary of the game
        }
        
        Passed newtrace=new Passed(this.getX(),this.getY(),this.getWidth(),this.getHeight(),this.trace_Image,this.c);
        this.trace.add(newtrace);// whenever we move we add one new step to the path
        return false;
    }
    /**
define what happens when the player moves in the Y direction
 * @param  none
 * @return boolean, whether the player went out of the boundary or not
 */
    public boolean moveY() {
        y += vely;
        if ((y <= 0)||(y>600)) {
            return true;
        }
        Passed newtrace=new Passed(x,this.getY(),this.getWidth(),this.getHeight(),this.image,this.c);
        this.trace.add(newtrace);// whenever we move we add one new step to the path
        return false;
    }
// getters and setters of the class attributes
    public int getVelx() {
        return velx;
    }

    public void setVelx(int velx) {
        this.velx = velx;
    }
    
    public int getVely() {
        return velx;
    }

    public void setVely(int vely) {
        this.vely = vely;
    }
    
    
        /**
checks whether a player has failed the game or not
 * @param Player other and the level of the game
 * @return boolean, true if the player failed false otherwise
 */
    public boolean Fail(Player other,Level l)
    {
        if(this.collides(other))
            return true;
        for (Brick piece:l.bricks)
        {
            if(this.borderCliding(piece))
                return true;
        }
        return false;
    }
        /**
draw the trace of the player
 * @param  Graphics g
 * @return draw the trace of the Player
 */
    public void show(Graphics g)
    {
        for (Passed piece: this.trace)
        {
            piece.exposed=true;
            piece.draw(g);
        }
    }
        /**
checks whether the other player hit the trace of this player or not
 * @param  none
 * @return boolean,whether the trace of this player colides with another player
 */
        public boolean collides(Player other) {// whether the trace of this player colides with another player
       
            for (Passed Mystep: this.trace)
            {
                
                if(Mystep.col(other))
                {
                    return true;// then this player wins
                }
            }
        
        return false;
    }
}
