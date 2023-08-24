/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

/**
 *
 * @author Miguel-KG
 */
public class Board {
    private Cell[][] board = new Cell[15][15];
    
    public void setup() {
        for ( int i = 0; i < board.length; i++ ) {
            for ( int i2 = 0; i2 < board[0].length; i2++ ) {
                board[ i ][ i2 ] = new Cell();
            }
        }
        
        Coord coord = new Coord();
        
        board[ board.length - 1 ][ 0 ].setPlayer( true );
        
        for ( int i = 0; i < 5; i++ ) {
            coord.createStartCoord( board );
            board[ coord.y ][ coord.x ].setPit( true );
        }
        
        for ( int i = 0; i < 2; i++ ) {
            coord.createStartCoordWood( board );
            board[ coord.y ][ coord.x ].setWood( true );
        }
        
        coord.createStartCoord( board );
        board[ coord.y ][ coord.x ].setGold( true );
        
        coord.createStartCoord( board );
        board[ coord.y ][ coord.x ].setWumpus( true );
        
        coord.createStartCoord( board );
        board[ coord.y ][ coord.x ].setMonster( true );
    }
    
    public void print() {
        for ( int i = 0; i < board.length; i++ ) {
            for ( Cell cell : board[ i ] ){
                System.out.print( "  " );
                
                /*if ( !cell.isVisible() ) {
                    System.out.print( "#" );
                } else*/
                if ( cell.hasPlayer() ) {
                    System.out.print( 'O' );
                } else
                if ( cell.hasPit() ) {
                    System.out.print( 'P' );
                } else
                if ( cell.hasWumpus() ) {
                    System.out.print( 'W' );
                } else
                if ( cell.hasMonster() ) {
                    System.out.print( 'M' );
                } else
                if ( cell.hasGold() ) {
                    System.out.print( 'G' );
                } else
                if ( cell.hasWood() ) {
                    System.out.print( 'I' );
                } else {
                    System.out.print( '*' );
                }
            }
            System.out.print( "  \n" );
        }
        System.out.print( 
                "\n"
                + "Jogador = O\n"
                + "Ouro = G\n"
                + "Madeira = I\n"
                + "Poço = P\n"
                + "Wumpus = W\n"
                + "Mostro ??? = M" );
    }
}