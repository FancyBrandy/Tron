/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// import statements
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.awt.event.*;  
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.sql.SQLException;


/**
 *
 * @author Chen Danqing
 */
public class GameEngine extends JPanel {

    private final int FPS = 240;// frames per second

    private P1 p1;
    private P2 p2;
    private int Sx=0;
    private int Sy=0;
    private boolean paused = false;
    private Image background;
    private int levelNum = 1;// the default starting level is the 1 levels
    private Level level;
    private Timer newFrameTimer;
    private final int speedx=1;
    private final int speedy=1;// the dafault speed is 2
    private Image I1;
    private Image I2;
    private JFrame frame;
    private TronGUI TronGUI;
    public int point1=0;
    public int point2=0;
    public String name1,name2;
  
   
/**
Constructor of the class GameEngine
 * @param  none
 * @return defines the functions when we press the keyboard keys wasd and up down left right and other event handlers.
 */
    public GameEngine() {
        super();
       // TronGUI = new TronGUI();
    
        background = new ImageIcon("data/images/background.jpg").getImage();// set the background
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");// inputmap is how we handle the keyboard
        // the string connects the action map with the input map
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.setVelx(-speedx);
                p1.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.setVelx(speedx);
                p1.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              p1.setVelx(0);
               p1.setVely(speedy);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              p1.setVelx(0);
                p1.setVely(-speedy);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");// what we have to do when the escape button is pressed
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
  
   
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed A");// inputmap is how we handle the keyboard
        // the string connects the action map with the input map
        this.getActionMap().put("pressed A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.setVelx(-speedx);
                p2.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed d");
        this.getActionMap().put("pressed d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.setVelx(speedx);
                p2.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed S");
        this.getActionMap().put("pressed S", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              p2.setVelx(0);
               p2.setVely(speedy);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed W");
        this.getActionMap().put("pressed W", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              p2.setVelx(0);
                p2.setVely(-speedy);
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        }
    
    /**
restart the levels of the game
 * @param  none
 * @return ask for the names of the players and the colors at the very beginning and annouce the scores at the end of the game(after all the levels have been played)
 */
     public void restart() {
        try {
            if(levelNum==1)
            {
                point1=0;
                point2=0;
                 name1 = JOptionPane.showInputDialog(" Please give your name:","Player1");
                 name2 = JOptionPane.showInputDialog(" Please give your name:","Player2");
                String color1 = JOptionPane.showInputDialog(" Please choose a color :","Player1 color");
               String color2 = JOptionPane.showInputDialog(" Please choose a color :","Player2 color");
              
            switch (color1) {
                case "red":
                  I1=new ImageIcon("data/images/red.png").getImage();
                 
                  break;
                case "blue":
                  I1=new ImageIcon("data/images/blue.gif").getImage();
                
                  break;
               case "orange":
                  I1=new ImageIcon("data/images/orange.png").getImage();
                  break;
                case "pink":
                  I1=new ImageIcon("data/images/pink.png").getImage();
                  break;
                default:
                   I1=new ImageIcon("data/images/blue.gif").getImage();
               
              }
               switch (color2) {
                case "red":
                  I2=new ImageIcon("data/images/red.png").getImage();
                  break;
                case "blue":
                  I2=new ImageIcon("data/images/blue.gif").getImage();
                  break;
                case "orange":
                  I2=new ImageIcon("data/images/orange.png").getImage();
                  break;
                case "pink":
                  I2=new ImageIcon("data/images/pink.png").getImage();
                  break;
               
                default:
                   I2=new ImageIcon("data/images/blue.gif").getImage();
               
              }
                
          
            }
            if(levelNum==11)
            {
                 JOptionPane.showMessageDialog(frame,
                "Game is over, all the levels has been played won! "+name1+" got: "+point1+" \n" +name2 +"got "+point2+" points");
                 paused=true;
                 try{  Database db=new Database(10);
                    db.putHighScore(name2,point2);
                    db.putHighScore(name1,point1);
                     System.out.println("hello");
                    System.out.println(db.getHighScores());
                 }catch(SQLException ex)
                  {
                       Logger.getLogger(Database.class.getName());
                  }
            }
            if(levelNum<=10)
            {
            level = new Level("data/levels/level0" + levelNum + ".txt");}// this levelNum is the one that the player chose before
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Image p1Image = new ImageIcon("data/images/p1.jpg").getImage();
       
        p1 = new P1(200,20,20,20, p1Image,speedx,speedy);// here the number of level stand for the speed of the motorcycle
        p1.trace_Image=I1;
        Image p2Image = new ImageIcon("data/images/p2.jpg").getImage();
        
        p2 = new P2(300,200,20,20, p2Image,speedx,speedy);
        p2.trace_Image=I2;
        
        // the default starting postion of p1 is 00 and for p2 is 600 600

    }
/**
paint the components of the game
 * @param  Graphics grphcs
 * @return paint all the "sprites" of the game
 */
    @Override
    protected void paintComponent(Graphics grphcs) {
        
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 600, null);
        level.draw(grphcs);
        p1.draw(grphcs);
        p2.draw(grphcs);
        for(Passed piece : p1.trace)
        {
          
            piece.draw(grphcs);
           
            
        }
           for(Passed piece : p2.trace)
        {
            
            piece.draw(grphcs);
        }
    }// draw the elements and the order is not that important here

    
    /**
even handler, defines what the program should do as long as the game is not paused
 * @param  
 * event handler
 */
  class NewFrameListener implements ActionListener {
        
       
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {// we will alwasy do something if the game is not paused
              // when and how the player is gonna move
                if (p1.collides(p2)) {
                    // p1 wins
                    point1=point1+1;// player 1 gets one more point
                 
                     JOptionPane.showMessageDialog(frame,
                "this level is over, "+name1+" won! "); 
          
              
                     levelNum++;// they go to the next level
                    restart();
                }
                if(p2.collides(p1))
                {
                    // p2 wins
                    point2++;
                  
                  
                       JOptionPane.showMessageDialog(frame,
                "this level is over, "+name2+" won! "); 
                 levelNum++; 
                    restart();
                 
                    // p1 wins
                }
                for(Brick brick_piece:level.bricks)
                {
                    if(p1.borderCliding(brick_piece))
                    {
                        // here, then p2 wins
                        point2++;
                         
                             JOptionPane.showMessageDialog(frame,
                "this level is over, "+name2+" won! "); 
                         
                                levelNum++;
                               restart();
                                   
                                   break;
                    }
                  
                    if(p2.borderCliding(brick_piece))
                    {
                        // here, then p1 wins
                        point1++;
                    
                              JOptionPane.showMessageDialog(frame,
                "this level is over, "+name1+" won! "); 
                     
              
                        levelNum++;
                        restart();
                        break;
                    }
                }
                p1.moveX();
                p1.moveY();
                p2.moveX();
                p2.moveY();
                
                
               
            }
           
            repaint();//if we forget it it will look like that nothing happens
        }

    }


}
        

   
   

