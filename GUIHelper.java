/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WumpusWorld;

/**
 *
 * @author Miguel-KG
 */
public class GUIHelper {
    private static boolean playerDead = false;
    private static boolean gameWon = false;
    private static boolean flashlightMode = false;
    private static boolean arrowMode = false;
    
    public static boolean isPlayerDead() {
        return playerDead;
    }

    public static void setPlayerDead( boolean playerDead ) {
        playerDead = playerDead;
    }

    public static boolean isGameWon() {
        return gameWon;
    }

    public static void setGameWon( boolean gameWon ) {
        gameWon = gameWon;
    }

    public static boolean isFlashlightMode() {
        return flashlightMode;
    }

    public static void setFlashlightMode( boolean flashlightMode ) {
        GUIHelper.flashlightMode = flashlightMode;
    }

    public static boolean isArrowMode() {
        return arrowMode;
    }

    public static void setArrowMode( boolean arrowMode ) {
        GUIHelper.arrowMode = arrowMode;
    }
}
