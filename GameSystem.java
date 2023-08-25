/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import wumpusworld.GameElements.*;

/**
 *
 * @author Miguel-KG
 */
public class GameSystem {
    private Board board;
    private Random randomizer;
    private Player player;
    private ArrayList<Monster> monsters;
    Scanner entry = new Scanner( System.in );
    
    public GameSystem() {
        this.board = new Board( 15, 15 );
        this.randomizer = new Random();
        this.player = new Player( "player" );
        this.monsters = new ArrayList<>();
    }
    
    public void setup() {
        GameElement element;
        Monster monster;
        
        board.grid[ board.maxY ][ 0 ].addPiece( player );
        
        for ( int i = 0; i < 5; i++ ) {
            element = new GameElement( "Pit", PieceType.HAZARD, 'P' );
            createStartPosition( element );
        }
        
        for ( int i = 0; i < 2; i++ ) {
            element = new GameElement( "Wood", PieceType.ITEM, 'Y' );
            createStartPosition( element );
        }
        
        element = new GameElement( "Gold", PieceType.ITEM, 'G' );
        createStartPosition( element );
        
        monster = new Monster( "Wumpus", 'W', 100 );
        this.monsters.add( monster );
        createStartPosition( monster );
        
        monster = new Monster( "??? Monster", '?', 50 );
        this.monsters.add( monster );
        createStartPosition( monster );
    }
    
    public void start() {
        int op;
        
        board.print();
        
        System.out.println(
                "1 - Andar para cima\n"
                + "2 - Andar para baixo\n"
                + "3 - Andar para esquerda\n"
                + "4 - Andar para direita\n"
                + "5 - Usar lanterna\n"
                + "6 - Disparar flecha\n"
                + "Digite o número do que deseja fazer"
        );
        
        op = entry.nextInt();
        
        if ( op >= 1 && op <= 4 ) {
            move ( op );
        }
    }
    
    public void move ( GameElement piece, int direction ) {
        int x = piece.getPosition().x;
        int y = piece.getPosition().y;
        int newX = x;
        int newY = y;
        
        Tile pieceTile = board.grid[ y ][ x ];
        switch ( direction ) {
            case 1: // Cima
                if ( y < board.maxY ) {
                    newY++;
                }
                break;
            case 2:
                if ( y > 0 ) {
                    newY--;
                }
                break;
            case 3:
                if ( x > 0 ) {
                    newX--;
                }
                break;
            case 4:
                if ( x < board.maxX ) {
                    newX++;
                }
                break;
        }
        board.grid[ y ][ x ].removePiece( piece );
        board.grid[ newY ][ newX ].addPiece( piece );
    }
    
    private void createStartPosition( GameElement piece ) {
        Coord position = new Coord();
        position.x = randomizer.nextInt( board.maxX + 1 );
        position.y = randomizer.nextInt( board.maxY + 1 );
        int x = position.x;
        int y = position.y;
        
        while (
            !board.grid[ y ][ x ].isAvailableToStart( piece )
        ) {
            x++;
            if ( x > board.maxX - 1 ) {
                x = 0;
                y++;
                if ( y > board.maxY - 1 ) {
                    y = 0;
                }
            }
        }
        
        board.grid[ y ][ x ].addPiece( piece );
    }
}
