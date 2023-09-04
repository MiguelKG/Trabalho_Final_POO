/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GameElements;

import WumpusWorld.Board;
import WumpusWorld.Coord;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Miguel-KG
 */
public class GameElement {
    private String name;
    private PieceType type;
    private char icon;
    private Coord position;
    private JPanel boardPiece;
    private Color color;
    
    public GameElement ( String name, PieceType type, char icon, Color color ) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.color = color;
        this.position = new Coord();
        this.boardPiece = new JPanel();
        this.boardPiece.setBackground( color );
        this.getBoardPiece().setBackground( color );
    }
    
    public void move ( Board board, int direction ) {
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        int newX = x;
        int newY = y;
        
        switch ( direction ) {
            case 1: // Cima
                if ( y > 0 ) {
                    newY--;
                }
                break;
            case 2: // Baixo
                if ( y < board.getMaxY() ) {
                    newY++;
                }
                break;
            case 3: // Esquerda
                if ( x > 0 ) {
                    newX--;
                }
                break;
            case 4: // Direita
                if ( x < board.getMaxX() ) {
                    newX++;
                }
                break;
        }
        board.grid()[ y ][ x ].removePiece( this );
        board.grid()[ newY ][ newX ].addPiece( this );
    }
    
    public PieceType getType() {
        return this.type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public char getIcon() {
        return icon;
    }

    public void setIcon(char icon) {
        this.icon = icon;
    }

    public Coord getPosition() {
        return position;
    }

    public void setPosition(Coord position) {
        this.position = position;
    }

    public JPanel getBoardPiece() {
        return boardPiece;
    }

    public void setBoardPiece(JPanel boardPiece) {
        this.boardPiece = boardPiece;
    }
}
