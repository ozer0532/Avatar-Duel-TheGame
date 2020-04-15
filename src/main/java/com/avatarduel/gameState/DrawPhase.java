package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.player.*;
import com.avatarduel.card.*;

public class DrawPhase extends GameState {
    private Player pemain;
    private Card kartu;

    public DrawPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Ambil kartu dari deck player saat ini
        // Masukin kartu ke hand
        this.pemain = gameManager.getCurrentPlayer();
        this.kartu = this.pemain.getCardFromDeck();
        this.pemain.addPlayerHands(this.kartu);
        EndTurn();
    }

    public void EndTurn(){
        // Pindah ke main phase 1
        // Cara pindah: bikin phase baru, set fase GameManager jadi fase baru itu (liat referensi diatas)
        gameManager.setGameState(new Main1Phase(this.gameManager));
        gameManager.getGameState().StartTurn();
    }
}