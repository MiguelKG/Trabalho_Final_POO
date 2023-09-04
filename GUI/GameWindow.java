/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GUI;

import WumpusWorld.Board;
import WumpusWorld.GameSystem;
import WumpusWorld.Tile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import wumpusworld.GameElements.GameElement;

/**
 *
 * @author Miguel-KG
 */
public class GameWindow extends JFrame {
    private JPanel gameScreen;
    private JTextArea inventory;
    private JTextArea gameLog;
    private GameSystem system;
    private int sizeY;
    private int sizeX;
    
    public GameWindow ( GameSystem system, int sizeY, int sizeX ) {
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.system = system;
    }
    
    public void create ( Board board ) {
        setTitle("WumpusWorld");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout( new GridBagLayout() );
        
        GridBagConstraints config = new GridBagConstraints();
        
        JPanel gameScreen = new JPanel();
        gameScreen.setBackground( GamePalette.color4() );
        gameScreen.setBorder( BorderFactory.createMatteBorder
            ( 10,  10, 0, 0, GamePalette.color4() ) );
        gameScreen.setVisible( true );
        config.fill = GridBagConstraints.BOTH;
        config.weightx = 0.6;
        config.weighty = 0.7;
        config.gridx = 0;
        config.gridy = 0;
        add( gameScreen, config );
        
        JTextArea gameLog = new JTextArea( 3, 30 );
        gameLog.setEditable( false );
        gameLog.setBackground(GamePalette.color2() );
        gameLog.setBorder( BorderFactory.createMatteBorder
            ( 10,  10, 10, 0, GamePalette.color4() ) );
        
        gameLog.setVisible( true );
        config.weightx = 0.6;
        config.weighty = 0.3;
        config.gridx = 0;
        config.gridy = 1;
        this.gameLog = gameLog;
        gameLog.setBackground( GamePalette.color2() );
        this.add( gameLog, config );
        
        JPanel playerOptions = new JPanel();
        playerOptions.setBackground( GamePalette.color2() );
        playerOptions.setBorder(BorderFactory.createLineBorder( GamePalette.color4(), 10 ) );
        playerOptions.setVisible( true );
        config.gridheight = 2;
        config.weightx = 0.4;
        config.weighty = 1;
        config.gridx = 1;
        config.gridy = 0;
        add( playerOptions, config );
        
        // Populando a janela:
        
        gameScreen.setLayout( new GridLayout( sizeY, sizeX ) );
        this.gameScreen = gameScreen;
        
        boardDraw( board );
        
        // Divisão da área dos botões e exibição do inventário
        JPanel playerCommand = new JPanel();
        playerCommand.setBackground( new Color( 0, 0, 0, 0  ) );
        
        Font inventoryFont = new Font( "serif", Font.PLAIN, 20 );
        JTextArea inventory = new JTextArea();
        inventory.setEditable( false );
        inventory.setBackground( new Color( 0, 0, 0, 0  ) );
        inventory.setFont( inventoryFont );
        inventory.setForeground( GamePalette.color4() );
        this.inventory = inventory;
        
        playerOptions.setLayout( new GridLayout( 2, 1 ) );
        playerOptions.add( playerCommand );
        playerOptions.add( inventory );
        
        playerCommand.setLayout( new BorderLayout( ) );
        playerCommand.setBackground( GamePalette.color4() );
        
        // Criação dos botões
        
        JButton buttonUp = new JButton("Cima");
        setButtonColor( buttonUp );
        buttonUp.setBorder( BorderFactory.createMatteBorder
            ( 0,  0, 5, 0, GamePalette.color2() ) );
        playerCommand.add(buttonUp, BorderLayout.PAGE_START);
        configButton( buttonUp, "Up" );
        
        JButton buttonDown = new JButton("Baixo");
        setButtonColor( buttonDown );
        buttonDown.setBorder( BorderFactory.createMatteBorder
            ( 5,  0, 0, 0, GamePalette.color2() ) );
        playerCommand.add( buttonDown, BorderLayout.PAGE_END );
        configButton( buttonDown, "Down" );
        
        JButton buttonLeft = new JButton("Esquerda");
        setButtonColor( buttonLeft );
        buttonLeft.setBorder( BorderFactory.createMatteBorder
            ( 0,  0, 0, 5, GamePalette.color2() ) );
        //buttonLeft.setPreferredSize( new Dimension( 70, 10 ) );
        playerCommand.add(buttonLeft, BorderLayout.LINE_START);
        configButton( buttonLeft, "Left" );
        
        JButton buttonRight = new JButton("Direita");
        setButtonColor( buttonRight );
        buttonRight.setBorder( BorderFactory.createMatteBorder
            ( 0,  5, 0, 0, GamePalette.color2() ) );
        //buttonRight.setPreferredSize( new Dimension( 70, 10 ) );
        playerCommand.add(buttonRight, BorderLayout.LINE_END);
        configButton( buttonRight, "Right" );
        
        JPanel specialActions = new JPanel();
        specialActions.setLayout( new GridLayout( 2, 1 ) );
        specialActions.setBackground( new Color( 0, 0, 0, 0  ) );
        playerCommand.add( specialActions, BorderLayout.CENTER );
        
        JButton buttonFlashlight = new JButton("Lanterna");
        setButtonColor( buttonFlashlight );
        buttonFlashlight.setBorder( BorderFactory.createMatteBorder
            ( 0,  0, 0, 0, GamePalette.color2() ) );
        specialActions.add( buttonFlashlight );
        configButton( buttonFlashlight, "Flashlight" );
        
        JButton buttonArrow = new JButton("Flecha");
        setButtonColor( buttonArrow );
        buttonArrow.setBorder( BorderFactory.createMatteBorder
            ( 0,  0, 0, 0, GamePalette.color2() ) );
        specialActions.add( buttonArrow );
        configButton( buttonArrow, "Arrow" );
        
        boardCreate( board );
        this.setVisible( true );
    }
    
    public void setButtonColor( JButton button ) {
        button.setBackground( GamePalette.color4() );
        button.setForeground( GamePalette.color1() );
    }
    
    public void configButton ( JButton button, String command ) {
        button.setActionCommand( command );
        button.addActionListener( system );
    }
    
    private void boardCreate( Board board ) {
        boolean colorSwitch = false;
        Tile tile;
        JPanel panel;
        for ( int i = 0; i < sizeY; i++ ) {
            for ( int i2 = 0; i2 < sizeX; i2++ ) {
                tile = board.grid()[ i ][ i2 ];
                panel = board.grid()[ i ][ i2 ].getCell();
                
                if ( colorSwitch ) {
                    panel.setBackground( GamePalette.color2() );
                } else {
                    panel.setBackground( GamePalette.color3() );
                }
                colorSwitch = !colorSwitch;
                gameScreen.add( panel );
                
                for ( GameElement piece : tile.getPieces() ) {
                    panel.add( piece.getBoardPiece() );
                }
                
                if ( !tile.isVisible() ) {
                    panel.setVisible( false );
                } else {
                    panel.setVisible( true );
                }
            }
        }
    }
    
    public void boardDraw( Board board ) {
        Tile tile;
        JPanel panel;
        for ( int i = 0; i < sizeY; i++ ) {
            for ( int i2 = 0; i2 < sizeX; i2++ ) {
                tile = board.grid()[ i ][ i2 ];
                panel = board.grid()[ i ][ i2 ].getCell();
                
                for( Component c : panel.getComponents() ){
                    panel.remove( c );
                }
                
                for ( GameElement piece : tile.getPieces() ) {
                    panel.add( piece.getBoardPiece() );
                }
                
                if ( !tile.isVisible() ) {
                    panel.setVisible( false );
                } else {
                    panel.setVisible( true );
                }
            }
        }
    }
    
    public void log( String log ) {
        gameLog.append( log + "\n" );
    }
    
    public void printInventoryItem( String item ) {
        inventory.append( item + "\n" );
    }
    
    public void resetInventory( ) {
        inventory.setText( null );
    }
    
    public void resetLog( ) {
        gameLog.setText( null );
    }
}
