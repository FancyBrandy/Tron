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
public class Ranking {
    public String name;
    public int score;

    public Ranking(String name, int score) {
        this.name = name;
        this.score = score;
    }

    
    @Override
    public String toString() {
        return "HighScore{" + "name=" + name + ", score=" + score + '}';
    }
    
}
