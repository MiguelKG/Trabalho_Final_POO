/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package WumpusWorld;

import WumpusWorld.Board;

/**
 *
 * @author Miguel-KG
 */
public class WumpusWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameSystem game = new GameSystem();
        game.setup();
        game.start();
    }
    
}
