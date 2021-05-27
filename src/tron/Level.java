/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;


// import statements
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.ImageIcon;

/**
 *
 * @author Chen Danqing
 */
public class Level {

    // each brick is 40x20, so there can be at most 20 bricks side by side
    // the last 10 rows will be empty, so there can be at most 20 rows of bricks
    private final int BRICK_WIDTH = 20;
    private final int BRICK_HEIGHT = 20;// the width and height of the brick
    public boolean isOver=false;
    public ArrayList<Brick> bricks;
   /**
constructor
 * @param  the path of the level file
 * @return constructor
 */
    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }
    
    /**
 how to generate new level of the game
 * @param  the path of the level files
 * @return generate a new level of the gameS
 */

    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        bricks = new ArrayList<>();
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {// read the files
            int x = 0;
            for (char blockType : line.toCharArray()) {
                if (Character.isDigit(blockType)) {// if we detect a number(not space not line breaker)
                    Image image = new ImageIcon("data/images/brick0" + blockType + ".png").getImage();
                    bricks.add(new Brick(x * BRICK_WIDTH, y * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, image));// then we make a new brick object
                }
                x++;
            }
            y++;
        }// all of the number in the file is going to be 1
    }// we set the boundary of the level as the difference between each level

    
    /**
whether the player is collided with the bricks that is generated by each level as the boundary
 * @param Player p
 * @return boolean, true if the player hit the bricks, false otherwise
 */
    public boolean collides(Player p) {// whether any players have hit the boundary of the walll
        Brick collidedWith = null;
        for (Brick brick : bricks) {
            if (p.borderCliding(brick)) {// this method is in the sprite class
                collidedWith = brick;
                break;// if the ball hit any one of the brick then we break the loop
            }
        }
        if (collidedWith != null) {
            isOver=true;
            return true;
        } else {
            isOver=false;
            return false;
        }
    }
    

/**
 draw the bricks
 * @param  Graphics g
 * @return draw the bricks
 */
    public void draw(Graphics g) {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

}