// DrawPhase.java
package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.player.*;
import com.avatarduel.card.*;

/**
 * DrawPhase merupakan class yang bertanggung jawab dalam fase mengambil kartu dari dek ke tangan
 * dari pemain dengan giliran bermain saat ini.
 * DrawPhase akan di-generate setiap giliran pemain untuk mengambil kartu.
 */

public class DrawPhase extends GameState {
    private Player pemain;
    private Card kartu;

    /**
     * Membuat DrawPhase baru dengan masukan gameManager
     * @param gameManager gameManager sekarang
     */
    public DrawPhase(GameManager gameManager){
        super(gameManager);
    }

    /**
     * Memulai DrawPhase dan mengambil kartu dari dek lalu meletakkannya di tangan
     */
    @Override
    public void StartTurn(){
        // Ambil kartu dari deck player saat ini
        // Masukin kartu ke hand
        System.out.println(">>>>> DRAW PHASE START <<<<<");
        this.pemain = gameManager.getCurrentPlayer();
        this.kartu = this.pemain.getCardFromDeck();
        this.pemain.addPlayerHands(this.kartu);
        EndTurn();
    }

    /**
     * Mengganti state game menjadi Main1Phase
     */
    @Override
    public void EndTurn(){
        gameManager.setGameState(new Main1Phase(this.gameManager));
        gameManager.getGameState().StartTurn();
    }
}