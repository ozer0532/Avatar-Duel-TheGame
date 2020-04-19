// EndPhase.java
package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.IMouseClickSub;

import javafx.scene.input.MouseEvent;

/**
 * EndPhase merupakan class yang bertanggung jawab dalam mengakhiri giliran dari pemain sekarang
 * dan mengganti giliran ke pemain lawan.
 * EndPhase akan di-generate setiap giliran pemain untuk mengganti giliran bermain.
 */

public class EndPhase extends GameState implements IMouseClickSub{
    
    /**
     * Membuat EndPhase baru dengan masukan gameManager
     * @param gameManager gameManager sekarang
     */
    public EndPhase(GameManager gameManager){
        super(gameManager);
    }

    /**
     * Memulai EndPhase, me-reset stats dari player, dan mengganti giliran bermain
     */
    @Override
    public void startTurn(){
        // Tuker current player dan opposite player
        System.out.println(">>>>> END PHASE START <<<<<");
        gameManager.getCurrentPlayer().getPlayerStats().resetStats();
        gameManager.switchPlayer();
    }

    /**
     * Mengganti state game menjadi  DrawPhase
     */
    @Override
    public void endTurn(){
        gameManager.setGameState(new DrawPhase(gameManager));
        gameManager.getGameState().startTurn();
    }

    /**
     * Berpindah ke EndTurn dengan menggunakan MouseEvent
     * @param event input event dari mouse
     */
    public void onMouseClick(MouseEvent event) {
        endTurn();
    }
}