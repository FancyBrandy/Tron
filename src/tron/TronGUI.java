/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;
// import statements
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Chen Danqing
 */
public class TronGUI {
    private JFrame frame;
    private GameEngine gameArea;
    private Level level;
    private Player p1,p2;
    private int levelNum;
/**
constructor of the TronGUI class
 * @param  none
 * @return constructor of the class 
 */
    public TronGUI() {
        frame = new JFrame("Light Motor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameArea = new GameEngine();
        frame.getContentPane().add(gameArea);
        JMenuBar menuBar = new JMenuBar();// we are making the menu bar right now
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem Rank=new JMenuItem("Rank");
        gameMenu.add(Rank);
        // event handler
        Rank.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Database D=new Database(10);
                    ArrayList<Scorerecord> Scores=D.getHighScores();
                    ArrayList<Ranking> result=new ArrayList<Ranking>();
                    String RankContent="    the Ranking of the game: \n";
                    int count=0;
                    for(Scorerecord piece:Scores)
                    {
                        if(count<=10)
                        {
                    RankContent+="Rank: "+String.valueOf(count+1)+"  Name: "+Scores.get(count).getName()+"  Scores: "+Scores.get(count).getScore()+"\n";
                    count++;}
                    }
                    JOptionPane.showMessageDialog(gameArea, RankContent,
                            "Rank", JOptionPane.PLAIN_MESSAGE);
                }catch(SQLException ex)
            {
                Logger.getLogger(Database.class.getName());
            }
            }
        });
        
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
   

    
}
