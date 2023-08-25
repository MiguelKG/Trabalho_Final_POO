/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GameElements;

import WumpusWorld.Board;
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
        this.inventory = new HashMap<String, Integer>(3);
        inventory.put("flashligth", 2);
    }
    
    public void useItem ( String item ) {
        if ( inventory.containsKey( item ) ) {
            inventory.put( item, inventory.get( item ) - 1 );
        }
    }
    
    public void addItem ( String item ) {
        if ( inventory.containsKey( item ) ) {
            inventory.put( item, inventory.get( item ) + 1 );
        } else {
            inventory.put( item, 1 );
        }
    }
    
    public int getItem ( String item ) {
        return this.inventory.get( item );
    }
    
    public void useFlashlight ( Board board, int direction ) {
        if ( this.getItem( "flashlight" ) <= 0 ) {
            System.out.println( "Você não possui mais bateria na lanterna!" );
            return;
        }
        this.useItem( "flashlight" );
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        switch( direction ) {
            case 1:
                while ( y > 0 ){
                    board.grid[ y ][ x ].setVisible( true );
                    y--;
                }
                break;
            case 2:
                while ( y <= board.maxY ){
                    board.grid[ y ][ x ].setVisible( true );
                    y++;
                }
                break;
            case 3:
                while ( x > 0 ){
                    board.grid[ y ][ x ].setVisible( true );
                    x--;
                }
                break;
            case 4:
                while ( x <= board.maxX ){
                    board.grid[ y ][ x ].setVisible( true );
                    x++;
                }
                break;
        }
    }
    
    public void useArrow( Board board, int direction ) {
        if ( this.getItem( "wood" ) <= 0 ) {
            System.out.println( "Você não possui madeira!" );
            return;
        }
        this.useItem( "wood" );
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        switch( direction ) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
        }
    }
}
