/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GameElements;

import WumpusWorld.Coord;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miguel-KG
 */
public class Player extends GameElement {
    int life;
    private Map<String, Integer> inventory;
    
    public Player( String name ) {
        super( name, PieceType.PLAYER, 'O' );
        this.life = 100;
        this.inventory = new HashMap<String, Integer>();
    }
    
    public void useItem ( String item ) {
        if ( inventory.containsKey( item ) ) {
            inventory.put( item, inventory.get( item ) - 1 );
        }
    }
    
    public void getItem ( String item ) {
        if ( inventory.containsKey( item ) ) {
            inventory.put( item, inventory.get( item ) + 1 );
        } else {
            inventory.put( item, 1 );
        }
    }
}
