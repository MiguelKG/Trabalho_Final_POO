/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package WumpusWorld;

import java.util.Scanner;

/**
 *
 * @author Miguel-KG
 */
public class WumpusWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entry = new Scanner( System.in );
        int op = -1;
        boolean hasGUI = false;
        boolean debug = false;
        
        do {
            System.out.println( "Deseja jogar em qual modo?\n1 - Modo GUI\n2 - Modo Console" );
            System.out.print( "Escolha: " );
            op = entry.nextInt();
            if ( op == 1 ) {
                hasGUI = true;
            } else 
            if ( op == 2 ) {
                hasGUI = false;
            } else if ( op == 42 ) {
                System.out.println( "Modo debug ativado" );
                debug = true;
            }
        } while ( op < 1 || op > 2 );
        
        GameSystem game = new GameSystem( hasGUI, debug );
        game.play();
    }
    
}
