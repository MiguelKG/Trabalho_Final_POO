/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

import wumpusworld.GameElements.*;

/**
 *
 * @author Miguel-KG
 */
public class Board {
    private Tile[][] grid;
    private int maxX;
    private int maxY;
    
    public Board ( int sizeX, int sizeY ) {
        grid = new Tile[ sizeY ][ sizeX ];
        
        for ( int i = 0; i < sizeY; i++ ) {
            for ( int i2 = 0; i2 < sizeX; i2++ ) {
                grid[ i ][ i2 ] = new Tile( i2, i );
            }
        }
        
        this.maxY = this.grid.length - 1;
        this.maxX = this.grid[ 0 ].length - 1;
    }
    
    public void print () {
        Tile tile;
        GameElement piece;
        Monster monster;
        char icon;
        
        for ( int i = 0; i <= maxY; i++ ) {
            for ( int i2 = 0; i2 <= maxX; i2++ ) {
                
                tile = this.grid[ i ][ i2 ];
                
                if ( tile.isVisible() == false ) {
                    icon = '#';
                } else
                if ( tile.hasMonster() ) {
                    piece = tile.getPieceByType( PieceType.MONSTER );
                    icon = piece.getIcon();
                } else
                if ( tile.hasHazard() ) {
                    piece = tile.getPieceByType( PieceType.HAZARD );
                    icon = piece.getIcon();
                } else
                if ( tile.hasPlayer() ) {
                    piece = tile.getPieceByType( PieceType.PLAYER );
                    icon = piece.getIcon();
                } else
                if ( tile.hasItem() ) {
                    piece = tile.getPieceByType( PieceType.ITEM );
                    icon = piece.getIcon();
                } else
                if ( tile.getPosition().x == 0 && tile.getPosition().y == maxY ) {
                    icon = 'E';
                } else {
                    icon = '*';
                }
                System.out.print( " " + icon + " " );
            }
            System.out.print( "\n" );
        }
    }

    public Tile[][] grid() {
        return grid;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }   
}