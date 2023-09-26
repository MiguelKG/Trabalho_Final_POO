/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import wumpusworld.GUI.GameWindow;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import wumpusworld.GameElements.*;

/**
 *
 * @author Miguel-KG
 */
public class GameSystem implements ActionListener {
    private Board board;
    private Random randomizer;
    private Player player;
    private ArrayList<Monster> monsters;
    private ArrayList<String> gameInfo;
    private boolean debug;
    private boolean gui;
    private GameWindow window;
    Scanner entry;
    
    public GameSystem( boolean gui, boolean debug ) {
        this.randomizer = new Random();
        this.monsters = new ArrayList<>();
        this.gameInfo = new ArrayList<>();
        this.debug = debug;
        this.entry = new Scanner( System.in );
        this.gui = gui;
    }
    
    public void play( ) {
        setup();
        if ( !gui ) {
            run();
            newGame();
        }
    }
    
    public void addGameInfo( String message ) {
        gameInfo.add( message );
    }
    
    public void setup() {
        int sizeX = 15;
        int sizeY = 15;
        GameElement element;
        Monster monster;
        
        this.board = new Board( sizeY, sizeX );
        
        this.player = new Player( "player" );
        monsters.clear();
        gameInfo.clear();
        
        board.grid()[ board.getMaxY() ][ 0 ].addPiece( player );
        board.grid()[ board.getMaxY() ][ 0 ].setVisible( true );
        
        for ( int i = 0; i < 5; i++ ) {
            element = new Hazard( "Pit", 'P', Color.MAGENTA, 100 );
            createStartPosition( element );
        }
        
        for ( int i = 0; i < 2; i++ ) {
            element = new GameElement( "Madeira", PieceType.ITEM, 'Y', Color.decode( "#472915" ) );
            createStartPosition( element );
        }
        
        element = new GameElement( "Ouro", PieceType.ITEM, 'G', Color.YELLOW );
        createStartPosition( element );
        
        monster = new Monster( "Wumpus", 'W', Color.RED, 100, 1, 0 );
        this.monsters.add( monster );
        createStartPosition( monster );
        
        monster = new Monster( "Monstro ???", 'M', Color.decode( "#ff5e00" ), 50, 2, 1 );
        this.monsters.add( monster );
        createStartPosition( monster );
        
        checkSurroundings();
        if ( this.debug ) {
            debugMode();
        }
        if ( gui ) {
            if ( window != null ) window.dispose();
            GUIHelper.reset();
            this.window = new GameWindow( this, sizeY, sizeX );
            window.create( board );
            window.boardDraw( board );
        } else {
            board.print();
        }
        printInfo();
    }
    
    public void printInfo( ) {
        float i = 1;
        
        if ( gui ) {
            window.resetInventory();
            window.printInventoryItem( "HP: " + player.getLife() );
        }
        System.out.println( "HP: " + player.getLife() );

        System.out.println("-- Inventário --");
        for ( Map.Entry<String, Integer> entry : player.getInventory().entrySet() ) {
            System.out.print( entry.getKey() + " ( " + entry.getValue() + " )" );
            if ( gui ) {
                window.printInventoryItem( entry.getKey() + " ( " + entry.getValue() + " )" );
            }
            if ( i / 3 != 0 ) {
                System.out.print( "\t" );
            } else {
                System.out.print( "\n" );
            }
            i++;
        }
        System.out.println("\n----");
        if ( gui ) window.resetLog();
        if ( gameInfo.size() > 0 ) {
            for ( String message : gameInfo ) {
                System.out.println( "--> " + message );
                if ( gui ) {
                    window.log( message );
                }
            }
            gameInfo.clear();
        }
    }
    
    public void run() {
        int op;
        boolean resultItemUse = true;
        boolean turnPass;
        boolean playerDead = false;
        boolean gameWon = false;
        do {
            turnPass = true;
            
            if ( playerDead || gameWon ) {
                return;
            }
            
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
                        
                + "10 - Terminar jogo\n"
            );
            System.out.print( "Ação: " );

            op = entry.nextInt();

            if ( op >= 1 && op <= 4 ) {
                player.move ( board, op );
            } else
            if ( op == 5 ) {
                turnPass = false;
                do {
                    System.out.println(
                        "-//-\n"
                        + "Iluminar em qual direção?\n"
                        + "1 - Cima\n"
                        + "2 - Baixo\n"
                        + "3 - Esquerda\n"
                        + "4 - Direita\n"
                        + "Direção: "
                    );
                    op = entry.nextInt();
                    if ( op < 1 || op > 4 ) {
                        System.out.println( "Valor inválido" );
                    } else {
                        resultItemUse = player.useFlashlight( board, op, this );
                    }
                    if ( !resultItemUse ) {
                        turnPass = false;
                        resultItemUse = true;
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
                        + "Direção: "
                    );
                    op = entry.nextInt();
                    if ( op < 1 || op > 4 ) {
                        System.out.println( "Valor inválido" );
                    } else {
                        resultItemUse = player.useArrow( board, op, this );
                    }
                    if ( !resultItemUse ) {
                        turnPass = false;
                        resultItemUse = true;
                    }
                } while ( op < 1 || op > 4 );
            } else {
                turnPass = false;
            }
            
            if ( turnPass ) {
                collectItems( board );
                board.grid()[ player.getPosition().y ][ player.getPosition().x ].setVisible( true );
                
                if ( !checkMonsterDamage( board ) && !checkHazardDamage( board ) ) {
                    monsterMovement();
                    checkSurroundings();
                    playerDead = checkMonsterDamage( board );
                } else {
                    playerDead = true;
                }
                
                if ( playerDead ) {
                    turnPass = false;
                } else {
                    gameWon = checkWinCondition();
                }
            }
            
            board.print();
            printInfo( );
        } while ( op != 10 );
    }
    
    public void newGame() {
        System.out.print( 
            "Deseja jogar novamente?\n"
            + "1 - Sim\n"
            + "2 - Não\n"
            + "Escolha: "
        );
        int op = entry.nextInt();
        
        if ( op == 1 ) {
            play();
        } else {
            return;
        }
    }
    
    boolean checkWinCondition() {
        if (
            player.getPosition().y == board.getMaxY() &&
            player.getPosition().x == 0 &&
            player.getItem( "Ouro" ) > 0
        ) {
            addGameInfo( "Você escapou com sucesso da caverna com o ouro!" );
            return true;
        }
        return false;
    }
    
    private void checkSurroundings() {
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        int foundGold = 0;
        int foundMonster = 0;
        int foundHazard = 0;
        Tile position = board.grid()[ 0 ][ 0 ];
        
        if ( y - 1 >= 0) {
            position = board.grid()[ y - 1 ][ x ];
            if ( position.hasSpecificItem( "Ouro" ) ) foundGold = 1;
            if ( position.hasMonster() ) foundMonster = 1;
            if ( position.hasHazard() ) foundHazard = 1;
        }
        if ( y + 1 <= board.getMaxY() ) {
            position = board.grid()[ y + 1 ][ x ];
            if ( position.hasSpecificItem( "Ouro" ) ) foundGold = 1;
            if ( position.hasMonster() ) foundMonster = 1;
            if ( position.hasHazard() ) foundHazard = 1;
        }
        if ( x - 1 >= 0 ) {
            position = board.grid()[ y ][ x - 1 ];
            if ( position.hasSpecificItem( "Ouro" ) ) foundGold = 1;
            if ( position.hasMonster() ) foundMonster = 1;
            if ( position.hasHazard() ) foundHazard = 1;
        }
        if ( x + 1 <= board.getMaxX() ) {
            position = board.grid()[ y ][ x + 1 ];
            if ( position.hasSpecificItem( "Ouro" ) ) foundGold = 1;
            if ( position.hasMonster() ) foundMonster = 1;
            if ( position.hasHazard() ) foundHazard = 1;
        }
            
        if ( foundGold == 1 ) {
            gameInfo.add("Você percebe um brilho estranho no escuro...");
        }
        
        if ( foundMonster == 1 ) {
            gameInfo.add("Você sente um cheiro terrível");
        }
        
        if ( foundHazard == 1 ) {
            gameInfo.add("Você sente uma brisa inquietante");
        }
    }
    
    private void monsterMovement () {
        for ( Monster monster : monsters ) {
            monster.move( board );
        }
    }
    
    private boolean checkMonsterDamage( Board board ) {
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        Tile playerTile = board.grid()[ y ][ x ];
        
        ArrayList<GameElement> monsters = playerTile.getPiecesByType( PieceType.MONSTER );
        
        for ( GameElement monster : monsters ) {
            gameInfo.add( monster.getName() + " ataca!" );
            player.addLife( 0 - ( ( Monster )monster ).getDamage() );
        }
        
        if ( player.getLife() <= 0 ) {
            gameInfo.clear();
            gameInfo.add( "Você morreu!" );
            return true;
        }
        
        return false;
    }
    
    private boolean checkHazardDamage( Board board ) {
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        Tile playerTile = board.grid()[ y ][ x ];
        
        if ( playerTile.hasHazard() ) {
            player.stepOnHazard( board, this );
        }
        
        if ( player.getLife() <= 0 ) {
            gameInfo.clear();
            gameInfo.add( "Você morreu!" );
            return true;
        }
        
        return false;
    }
    
    private void collectItems ( Board board ) {
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        GameElement item;
        Tile playerTile = board.grid()[ y ][ x ];
        
        while( playerTile.hasItem() ) {
            item = playerTile.removePieceByType( PieceType.ITEM );
            player.addItem( item.getName(), this );
        }
    }
    
    private void createStartPosition( GameElement piece ) {
        Coord position = new Coord();
        position.x = randomizer.nextInt( board.getMaxX() + 1 );
        position.y = randomizer.nextInt( board.getMaxY() + 1 );
        int x = position.x;
        int y = position.y;
        
        while (
            !board.grid()[ y ][ x ].isAvailableToStart( piece )
        ) {
            x++;
            if ( x > board.getMaxX() - 1 ) {
                x = 0;
                y++;
                if ( y > board.getMaxY() - 1 ) {
                    y = 0;
                }
            }
        }
        
        board.grid()[ y ][ x ].addPiece( piece );
    }
    
    private void debugMode () {
        for ( int i = 0; i < board.grid().length; i++ ) {
            for ( int i2 = 0; i2 < board.grid()[0].length; i2++ ) {
                board.grid()[ i ][ i2 ].setVisible( true );
            }
        }
    }
    
    @Override
    public void actionPerformed( ActionEvent event ){
        
        String action = event.getActionCommand();
        int op = -1;
        
        boolean turnPass = true;

        if ( GUIHelper.isPlayerDead() || GUIHelper.isGameWon() ) {
            addGameInfo( "Deseja jogar novamente?" );
            addGameInfo( "Cima - Sim" );
            addGameInfo( "Baixo - Não" );
            
            if ( action.equals("Up") ) {
                play();
            } else
            if ( action.equals( "Down" ) ) {
                window.dispose();
            }
            return;
        }
        
        if ( action.equals("Up") ) {
            op = 1;
        } else
        if ( action.equals( "Down" ) ) {
            op = 2;
        } else
        if ( GUIHelper.isPlayerDead() || GUIHelper.isGameWon() ) {
            
        } else
        if ( action.equals( "Left" ) ) {
            op = 3;
        } else
        if ( action.equals( "Right" ) ) {
            op = 4;
        } else
        if ( GUIHelper.isArrowMode() || GUIHelper.isFlashlightMode() ) {
            turnPass = false;
            GUIHelper.setArrowMode( false );
            GUIHelper.setFlashlightMode( false );
        } else
        if ( action.equals( "Flashlight" ) ) {
            GUIHelper.setFlashlightMode( true );
            turnPass = false;
            addGameInfo( "Escolha a direção da lanterna:" );
            addGameInfo("(Botões não direcionais cancelam a ação)" );
        } else
        if ( action.equals( "Arrow" ) ) {
            GUIHelper.setArrowMode( true );
            turnPass = false;
            addGameInfo( "Escolha a direção da flecha:" );
            addGameInfo("(Botões não direcionais cancelam a ação)" );
        }
        
        if ( op != -1 ) {
            if ( GUIHelper.isFlashlightMode() ) {
                player.useFlashlight( board, op, this );
                turnPass = false;
                GUIHelper.setFlashlightMode( false );
            } else
            if ( GUIHelper.isArrowMode() ) {
                if ( !player.useArrow( board, op, this ) ) {
                    turnPass = false;
                }
                GUIHelper.setArrowMode( false );
            } else {
                player.move( board, op );
            }
        }
        
        if ( turnPass ) {
            collectItems( board );
            board.grid()[ player.getPosition().y ][ player.getPosition().x ].setVisible( true );

            if ( !checkMonsterDamage( board ) && !checkHazardDamage( board ) ) {
                monsterMovement();
                checkSurroundings();
                
                GUIHelper.setPlayerDead( checkMonsterDamage( board ) );
            } else {
                GUIHelper.setPlayerDead( true );
            }
            
            if ( !GUIHelper.isPlayerDead() ) {
                GUIHelper.setGameWon( checkWinCondition() );
            }
        }
        
        if ( GUIHelper.isPlayerDead() || GUIHelper.isGameWon() ) {
            addGameInfo( "Deseja jogar novamente?" );
            addGameInfo( "Cima - Sim" );
            addGameInfo( "Baixo - Não" );
        }
        
        board.print();
        window.boardDraw( board );
        SwingUtilities.updateComponentTreeUI(window );
        printInfo( );
    }
}
