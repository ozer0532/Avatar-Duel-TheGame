package com.avatarduel.gameState;

import com.avatarduel.gameManager.GameManager;
import com.avatarduel.player.*;
import com.avatarduel.card.*;

public class DrawPhase extends GameState {
    private Player pemain;
    private Card kartu;
    GameManager gm;

    public DrawPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Ambil kartu dari deck player saat ini
        // Masukin kartu ke hand
        this.gm = super.getGameManager();
        this.pemain = gm.getCurrentPlayer();
        this.kartu = this.pemain.getCardFromDeck();
        this.pemain.addPlayerHands(this.kartu);
        this.gm.setCurrentPlayer(pemain);
        super.setGameManager(this.gm);
    }

    public void EndTurn(){
        // Pindah ke main phase 1
        // Cara pindah: bikin phase baru, set fase GameManager jadi fase baru itu (liat referensi diatas) 
        this.gm = super.getGameManager();
        super.getGameManager().setGameState(new Main1Phase(this.gm));
    }
}