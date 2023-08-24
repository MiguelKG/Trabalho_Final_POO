/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

import java.util.Random;

/**
 *
 * @author Miguel-KG
 */
public class Coord {
    public int x;
    public int y;
    private Random randomizer = new Random();
    
    public Coord( ) {
        this.x = -1;
        this.y = -1;
    }
    
    public Coord( int x, int y ) {
        this.x = x;
        this.y = y;
    }
    
    public void createStartCoord( Cell board[][] ) {
        x = randomizer.nextInt( board[0].length );
        y = randomizer.nextInt( board.length );
        
        while (
            board[ y ][ x ].hasPlayer() ||
            board[ y ][ x ].hasPit()
        ) {
            x++;
            if ( x > board[0].length - 1 ) {
                x = 0;
                y++;
                if ( y > board.length - 1 ) {
                    y = 0;
                }
            }
        }
    }
    
    public void createStartCoordWood( Cell board[][] ) {
        createStartCoord( board );
        
        while (
            board[ y ][ x ].hasPlayer() ||
            board[ y ][ x ].hasPit() ||
            board[ y ][ x ].hasWood()
        ) {
            x++;
            if ( x > board[0].length - 1 ) {
                x = 0;
                y++;
                if ( y > board.length - 1 ) {
                    y = 0;
                }
            }
        }
    }
}
