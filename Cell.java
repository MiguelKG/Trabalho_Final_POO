/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

/**
 *
 * @author Miguel-KG
 */
public class Cell {
    private boolean player = false;
    private boolean gold = false;
    private boolean wood = false;
    private boolean wumpus = false;
    private boolean monster = false;
    private boolean pit = false;
    private boolean visible = false;
    
    public Cell() {
        this.player = false;
        this.gold = false;
        this.wood = false;
        this.wumpus = false;
        this.monster = false;
        this.pit = false;
        this.visible = false;
    }
    
    public boolean hasPlayer () {
        return this.player;
    }
    
    public boolean hasGold () {
        return this.gold;
    }
    
    public boolean hasWood () {
        return this.wood;
    }
    
    public boolean hasWumpus () {
        return this.wumpus;
    }
    
    public boolean hasMonster () {
        return this.monster;
    }
    
    public boolean hasPit () {
        return this.pit;
    }
    
    public boolean isVisible () {
        return this.visible;
    }
    
    public void setPlayer ( boolean status ) {
        this.player = status;
    }
    
    public void setGold ( boolean status ) {
        this.gold = status;
    }
    
    public void setWood ( boolean status ) {
        this.wood = status;
    }
    
    public void setWumpus ( boolean status ) {
        this.wumpus = status;
    }
    
    public void setMonster ( boolean status ) {
        this.monster = status;
    }
    
    public void setPit ( boolean status ) {
        this.pit = status;
    }
    
    public void setVisible ( boolean status ) {
        this.visible = status;
    }
}