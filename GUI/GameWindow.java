/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Miguel-KG
 */
public class GameWindow extends JFrame {
    
    public void create () {
        setTitle("WumpusWorld");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout( new GridBagLayout() );
        
        GridBagConstraints config = new GridBagConstraints();
        
        JPanel gameScreen = new JPanel();
        gameScreen.setBackground( Color.decode( "#2A453B" ) );
        gameScreen.setBorder( BorderFactory.createMatteBorder
            ( 10,  10, 0, 0, Color.decode( "#365D48" ) ) );
        gameScreen.setVisible( true );
        config.fill = GridBagConstraints.BOTH;
        config.weightx = 0.7;
        config.weighty = 0.8;
        config.gridx = 0;
        config.gridy = 0;
        add( gameScreen, config );
        
        JPanel gameLog = new JPanel();
        gameLog.setBackground( Color.decode( "#577C44" ) );
        gameLog.setBorder( BorderFactory.createMatteBorder
            ( 10,  10, 10, 0, Color.decode( "#365D48" ) ) );
        
        gameLog.setVisible( true );
        config.weightx = 0.7;
        config.weighty = 0.2;
        config.gridx = 0;
        config.gridy = 1;
        add( gameLog, config );
        
        JPanel playerOptions = new JPanel();
        playerOptions.setBackground( Color.decode( "#577C44" ) );
        playerOptions.setBorder(BorderFactory.createLineBorder( Color.decode( "#365D48" ), 10 ) );
        playerOptions.setVisible( true );
        config.gridheight = 2;
        config.weightx = 0.3;
        config.weighty = 1;
        config.gridx = 1;
        config.gridy = 0;
        add( playerOptions, config );
        
        // Populando a janela:
        
        JPanel[][] board = new JPanel[15][15];
        
        this.setVisible( true );
    }
}
