/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

import java.util.ArrayList;
import java.util.Map;
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
    private int debug;
    Scanner entry = new Scanner( System.in );
    
    public GameSystem() {
        this.board = new Board( 15, 15 );
        this.randomizer = new Random();
        this.player = new Player( "player" );
        this.monsters = new ArrayList<>();
        this.debug = 0;
    }
    
    public void setup() {
        GameElement element;
        Monster monster;
        
        board.grid[ board.maxY ][ 0 ].addPiece( player );
        board.grid[ board.maxY ][ 0 ].setVisible( true );
        
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
    
    public void run() {
        int op;
        do {
            System.out.println("\n\n\n");
            board.print();
            System.out.print( "HP: " + player.getLife() );
            System.out.println("\n-- Inventário --");
            int turnPass = 1;
            float i = 1;
            for ( Map.Entry<String, Integer> entry : player.getInventory().entrySet() ) {
                System.out.print( entry.getKey() + " ( " + entry.getValue() + " )" );
                if ( i / 3 == 0 ) {
                    System.out.print( "\n" );
                } else {
                    System.out.print( "\t" );
                }
                i++;
            }
            System.out.print("\n----\n");

            System.out.print(
                "1 - Andar para cima"
                + "\t\t"
                + "2 - Andar para baixo\n"
                        
                + "3 - Andar para esquerda"
                + "\t\t"
                + "4 - Andar para direita\n"
                        
                + "5 - Usar lanterna"
                + "\t\t"
                + "6 - Disparar flecha\n"
                        
                + "10 - Sair do jogo\n"
            );
            checkSurroundings();
            System.out.print( "Ação: " );

            op = entry.nextInt();

            if ( op >= 1 && op <= 4 ) {
                player.move ( board, op );
            } else
            if ( op == 5 ) {
                turnPass = 0;
                do {
                    System.out.println(
                        "-//-\n"
                        + "Iluminar em qual direção?\n"
                        + "1 - Cima\n"
                        + "2 - Baixo\n"
                        + "3 - Esquerda\n"
                        + "4 - Direita\n"
                    );
                    op = entry.nextInt();
                    if ( op < 1 || op > 4 ){
                        System.out.println( "Valor inválido" );
                    } else {
                        player.useFlashlight( board, op );
                    }
                } while ( op < 1 || op > 4 );
            } else
            if ( op == 6 ) { // Incompleto
                do {
                    System.out.println(
                        "-//-\n"
                        + "Disparar em qual direção?\n"
                        + "1 - Cima\n"
                        + "2 - Baixo\n"
                        + "3 - Esquerda\n"
                        + "4 - Direita\n"
                    );
                    op = entry.nextInt();
                    if ( op < 1 || op > 4 ){
                        System.out.println( "Valor inválido" );
                    } else {
                        player.useArrow( board, op );
                    }
                } while ( op < 1 || op > 4 );
            } else
            if ( op == 42 ) {
                this.debug = 1;
                debugMode();
                System.out.println( "Modo debug ativado" );
            }
            
            checkMonsterArea( board );
            collectItems( board );
            board.grid[ player.getPosition().y ][ player.getPosition().x ].setVisible( true );
            
            if ( player.getLife() <= 0) op = 10;
            
            if ( turnPass == 1 ) {
                monsterMovement();
            }
        } while ( op != 10 );
    }
    
    private void checkSurroundings() {
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        int foundGold = 0;
        int foundMonster = 0;
        if ( y - 1 > 0) {
            if ( board.grid[ y - 1 ][ x ].hasSpecificItem( "Gold" ) ) foundGold = 1;
            if ( board.grid[ y - 1 ][ x ].hasMonster() ) foundMonster = 1;
        }
        if ( y + 1 <= board.maxY ) {
            if ( board.grid[ y + 1 ][ x ].hasSpecificItem( "Gold" ) ) foundGold = 1;
            if ( board.grid[ y + 1 ][ x ].hasMonster() ) foundMonster = 1;
        }
        if ( x - 1 > 0 ) {
            if ( board.grid[ y ][ x - 1 ].hasSpecificItem( "Gold" ) ) foundGold = 1;
            if ( board.grid[ y ][ x - 1 ].hasMonster() ) foundMonster = 1;
        }
        if ( x + 1 <= board.maxX ) {
            if ( board.grid[ y ][ x + 1 ].hasSpecificItem( "Gold" ) ) foundGold = 1;
            if ( board.grid[ y ][ x + 1 ].hasMonster() ) foundMonster = 1;
        }
        if ( foundGold == 1 ) {
            System.out.println("- Você percebe um brilho estranho no escuro...");
        }
        
        if ( foundMonster == 1 ) {
            System.out.println("- Você sente um cheiro terrível");
        }
    }
    
    private void monsterMovement () {
        
    }
    
    private void checkMonsterArea( Board board ) {
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        Tile playerTile = board.grid[ y ][ x ];
        
        ArrayList<GameElement> monsters = playerTile.getPiecesByType( PieceType.MONSTER );
        
        for ( GameElement monster : monsters ) {
            System.out.println( monster.getName() + " ataca!" );
            player.addHp( 0 - ( ( Monster )monster ).getDamage() );
        }
    }
    
    private void collectItems ( Board board ) {
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        GameElement item;
        Tile playerTile = board.grid[ y ][ x ];
        
        while( playerTile.hasItem() ) {
            item = playerTile.removePieceByType( PieceType.ITEM );
            player.addItem( item.getName() );
            System.out.println( "Obteve um(a) " + item.getName() + "!" );
        }
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
    
    private void debugMode () {
        for ( int i = 0; i < board.grid.length; i++ ) {
            for ( int i2 = 0; i2 < board.grid[0].length; i2++ ) {
                board.grid[ i ][ i2 ].setVisible( true );
            }
        }
    }
}
