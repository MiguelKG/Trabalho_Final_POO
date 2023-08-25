/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

import java.util.ArrayList;
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
    
    public Tile( int x, int y) {
        this.position = new Coord( x, y );
        this.visible = false;
        this.pieces = new ArrayList<GameElement>();
        this.monster = false;
        this.player = false;
        this.item = false;
        this.hazard = false;
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
        }
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
    
    public ArrayList<GameElement> getPieces() {
        return this.pieces;
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

    public void setMonster(boolean monster) {
        this.monster = monster;
    }

    public boolean hasPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public boolean hasItem() {
        return item;
    }

    public void setItem(boolean item) {
        this.item = item;
    }

    public boolean hasHazard() {
        return hazard;
    }

    public void setHazard(boolean hazard) {
        this.hazard = hazard;
    }

    public Coord getPosition() {
        return position;
    }

    public void setPosition(Coord position) {
        this.position = position;
    }
}