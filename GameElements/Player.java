/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GameElements;

import WumpusWorld.Board;
import WumpusWorld.Tile;
import WumpusWorld.GameSystem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miguel-KG
 */
public class Player extends GameElement {
    int maxLife;
    int life;
    private Map<String, Integer> inventory;
    
    public Player( String name ) {
        super( name, PieceType.PLAYER, 'O' );
        this.life = 100;
        this.maxLife = this.life;
        this.inventory = new HashMap<String, Integer>(3);
        inventory.put("Lanterna", 2);
    }
    
    public void addLife( int val ) {
        life += val;
        if ( life <= 0 ) {
            life = 0;
        } else if ( life > maxLife ) {
            life = maxLife;
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife( int life ) {
        this.life = life;
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
        if ( inventory.containsKey( item ) ) {
            return inventory.get( item );
        }
        
        return -1;
    }
    
    public Map<String, Integer> getInventory() {
        return this.inventory;
    }
    
    public void useFlashlight ( Board board, int direction, GameSystem game ) {
        if ( this.getItem( "Lanterna" ) <= 0 ) {
            game.addGameInfo( "Você não possui mais bateria na lanterna!" );
            return;
        }
        this.useItem( "Lanterna" );
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        switch( direction ) {
            case 1:
                while ( y >= 0 ){
                    board.grid()[ y ][ x ].setVisible( true );
                    y--;
                }
                break;
            case 2:
                while ( y <= board.getMaxY() ){
                    board.grid()[ y ][ x ].setVisible( true );
                    y++;
                }
                break;
            case 3:
                while ( x >= 0 ){
                    board.grid()[ y ][ x ].setVisible( true );
                    x--;
                }
                break;
            case 4:
                while ( x <= board.getMaxX() ){
                    board.grid()[ y ][ x ].setVisible( true );
                    x++;
                }
                break;
        }
    }
    
    public void useArrow( Board board, int direction, GameSystem game ) {
        if ( this.getItem( "Madeira" ) <= 0 ) {
            game.addGameInfo( "Você não possui madeira!" );
            return;
        }
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        GameElement monster;
        
        switch( direction ) {
            case 1:
                if ( y > 0 ) y--;
                break;
            case 2:
                if ( y < board.getMaxY() ) y++;
                break;
            case 3:
                if ( x > 0 ) x--;
                break;
            case 4:
                if ( x < board.getMaxX() ) x++;
                break;
        }
        if ( x != this.getPosition().x || y != this.getPosition().y ) {
            this.useItem( "Madeira" );
            if ( board.grid()[ y ][ x ].hasMonster() ) {
                monster = board.grid()[ y ][ x ].removePieceByType( PieceType.MONSTER );
                game.addGameInfo( "Você ouve um grito aterrorizante" );
            }
        }
    }
    
    public void stepOnHazard( Board board, GameSystem game ) {
        Tile playerTile = board.grid()[ this.getPosition().y ][ this.getPosition().x ];
        
        for ( GameElement hazard : playerTile.getPiecesByType( PieceType.HAZARD ) ) {
            if ( hazard.getName() == "Pit" ) {
                if ( this.getItem( "Madeira" ) > 0 ) {
                    game.addGameInfo( "Você constrói uma ponte para não cair no poço" );
                    this.useItem( "Madeira" );
                    playerTile.removePiece( hazard );
                } else {
                    addLife( 0 - ( (Hazard) hazard ).getDamage() );
                }
            } else {
                addLife( 0 - ( (Hazard) hazard ).getDamage() );
            }
        }
    }
}
