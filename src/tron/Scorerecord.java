/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

/**
 *
 * @author Chen Danqing
 */
public class Scorerecord {
    private final String name;
    private final int score;
/**
constructor of the Scorerecord class
 * @param  name and score of the player
 * @return constructor of the class 
 */
    public Scorerecord(String name, int score) {
        this.name = name;
        this.score = score;
    }
// getter and setter and toString method
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "HighScore{" + "name=" + name + ", score=" + score + '}';
    }
    
}
