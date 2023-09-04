/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GameElements;

import java.awt.Color;

/**
 *
 * @author Miguel-KG
 */
public class Hazard extends GameElement {
    private int damage;
    
    public Hazard ( String name, char icon, Color color, int damage ) {
        super( name, PieceType.HAZARD, icon, color );
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage( int damage ) {
        this.damage = damage;
    }
}
