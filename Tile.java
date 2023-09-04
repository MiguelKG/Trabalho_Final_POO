/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import wumpusworld.GameElements.*;

/**
 *
 * @author Miguel-KG
 */
public class Tile {
    private Coord position;
    private boolean visible = false;
    private ArrayList<GameElement> pieces;
    private boolean monster;
    private boolean player;
    private boolean item;
    private boolean hazard;
    private JPanel cell;
    
    public Tile( int x, int y) {
        this.position = new Coord( x, y );
        this.visible = false;
        this.pieces = new ArrayList<GameElement>();
        this.monster = false;
        this.player = false;
        this.item = false;
        this.hazard = false;
        this.cell = new JPanel();
        this.cell.setPreferredSize( new Dimension( 20, 20 ) );
    }
    
    public boolean isVisible () {
        return this.visible;
    }
    
    public void setVisible ( boolean status ) {
        this.visible = status;
    }
    
    public void addPiece( GameElement piece ) {
        this.pieces.add( piece );
        switch ( piece.getType() ) {
            case PLAYER:
                this.player = true;
                break;
            case MONSTER:
                this.monster = true;
                break;
            case ITEM:
                this.item = true;
                break;
            case HAZARD:
                this.hazard = true;
                break;
        }
        piece.setPosition( this.position );
    }
    
    public void removePiece( GameElement piece ) {
        if ( this.pieces.contains( piece ) ) {
            this.pieces.remove( piece );
            
            if ( !this.hasType( piece.getType() ) ) {
                switch ( piece.getType() ) {
                    case PLAYER:
                        this.player = false;
                        break;
                    case MONSTER:
                        this.monster = false;
                        break;
                    case ITEM:
                        this.item = false;
                        break;
                    case HAZARD:
                        this.hazard = false;
                        break;
                }
            }
        }
    }
    
    public GameElement removePieceByType( PieceType type ) {
        for ( GameElement piece : pieces ) {
            if ( piece.getType() == type ) {
                removePiece( piece );
                return piece;
            }
        }
        
        return null;
    }
    
    public ArrayList<GameElement> getPieces() {
        return this.pieces;
    }
    
    public GameElement getPieceByType( PieceType type ) {
        for ( GameElement piece : pieces ) {
            if ( piece.getType() == type ) {
                return piece;
            }
        }
        
        return null;
    }
    
    public ArrayList<GameElement> getPiecesByType( PieceType type ) {
        ArrayList<GameElement> resultPieces = new ArrayList<GameElement>();
        
        for ( GameElement piece : pieces ) {
            if ( piece.getType() == type ) {
                resultPieces.add( piece );
            }
        }
        
        return resultPieces;
    } 
    
    public boolean hasType( PieceType type ) {
        for ( GameElement piece : pieces ) {
            if ( piece.getType() == type ) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isAvailable ( ) {
        return !this.hasHazard();
    }
    
    public boolean isAvailableToStart ( GameElement piece ) {
        switch ( piece.getType() ) {
            case MONSTER:
                return !( this.hasHazard() || this.hasPlayer() );
            case ITEM:
                return !( this.hasHazard() || this.hasItem() || this.hasPlayer() );
            case HAZARD:
                return !( this.hasHazard() || this.hasItem() || this.hasPlayer() || this.hasMonster() );
        }
        
        return true;
    }

    public boolean hasMonster() {
        return monster;
    }

    public boolean hasPlayer() {
        return player;
    }

    public boolean hasItem() {
        return item;
    }
    
    public boolean hasSpecificItem( String name ) {
        for ( GameElement piece : pieces ) {
            if ( piece.getName() == name ) {
                return true;
            }
        }
        
        return false;
    }

    public boolean hasHazard() {
        return hazard;
    }

    public Coord getPosition() {
        return position;
    }

    public void setPosition( Coord position ) {
        this.position = position;
    }

    public JPanel getCell() {
        return cell;
    }

    public void setCell( JPanel cell ) {
        this.cell = cell;
    }
}