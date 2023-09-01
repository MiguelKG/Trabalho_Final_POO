/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GameElements;

import WumpusWorld.Board;
import WumpusWorld.Coord;
import java.util.Random;

/**
 *
 * @author Miguel-KG
 */
public class Monster extends GameElement {
    private int damage;
    private Random randomizer;
    // Movimento
    private int movement;
    private int deviation;
    
    public Monster( String name, char icon, int damage, int movement, int deviation ) {
        super( name, PieceType.MONSTER, icon );
        this.damage = damage;
        this.randomizer = new Random();
        this.movement = movement;
        this.deviation = deviation;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage( int damage ) {
        this.damage = damage;
    }
    
    public void move( Board board ) {
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        int newX = x;
        int newY = y;
        
        int direction = randomizer.nextInt(4) + 1;
        
        switch ( direction ) {
            case 1:
                if ( y >= movement ) {
                    newY = newY - movement;
                } else {
                    newY = 0;
                }
                break;
            case 2:
                if ( y <= board.getMaxY() - movement ) {
                    newY = newY + movement;
                } else {
                    newY = board.getMaxY();
                }
                break;
            case 3:
                if ( x >= movement ) {
                    newX = newX - movement;
                } else {
                    newX = 0;
                }
                break;
            case 4:
                if ( x <= board.getMaxX() - movement ) {
                    newX = newX + movement;
                } else {
                    newX = board.getMaxX();
                }
                break;
        }
        
        if ( this.deviation > 0 ) {
            boolean deviateOnX = false;
            int deviationCoordinate = -1;
            int deviationMaxCoordinate = -1;
            
            
            if ( direction <= 2 ) {
                deviateOnX = true;
                deviationCoordinate = newX;
                deviationMaxCoordinate = board.getMaxX();
            } else {
                deviateOnX = false;
                deviationCoordinate = newY;
                deviationMaxCoordinate = board.getMaxY();
            }
            
            int deviationDirection = randomizer.nextInt( 2 );
            
            if ( deviationDirection == 0 ) {
                if ( deviationCoordinate >= deviation ) {
                    deviationCoordinate = deviationCoordinate - deviation;
                } else {
                    deviationCoordinate = 0;
                }
            } else {
                if ( deviationCoordinate <= deviationMaxCoordinate - deviation ) {
                    deviationCoordinate = deviationCoordinate + deviation;
                } else {
                    deviationCoordinate = deviationMaxCoordinate;
                }
            }
            
            if ( deviationCoordinate > deviationMaxCoordinate ) {
                deviationCoordinate = deviationMaxCoordinate;
            } else if ( deviationCoordinate < 0 ) {
                deviationCoordinate = 0;
            }
            
            if ( deviateOnX ) {
                newX = deviationCoordinate;
            } else {
                newY = deviationCoordinate;
            }
        }
        
        board.grid()[ y ][ x ].removePiece( this );
        board.grid()[ newY ][ newX ].addPiece( this );
    }
}
