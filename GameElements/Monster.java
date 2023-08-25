/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wumpusworld.GameElements;

import WumpusWorld.Coord;

/**
 *
 * @author Miguel-KG
 */
public class Monster extends GameElement {
    private int damage;
    
    public Monster( String name, char icon, int damage ) {
        super( name, PieceType.MONSTER, icon );
        this.damage = damage;
    }
}
