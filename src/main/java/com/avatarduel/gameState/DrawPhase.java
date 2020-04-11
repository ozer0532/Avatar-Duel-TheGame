package com.avatarduel.gameState;

import com.avatarduel.gameManager.GameManager;
import com.avatarduel.player.*;

public class DrawPhase extends GameState {
    public DrawPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Ambil kartu dari deck player saat ini
        // Masukin kartu ke hand
        Card a = getCardFromDeck();
        super.getGameManager().getCurrentPlayer().addPlayerHands(a);
    }

    public void EndTurn(){
        // Pindah ke main phase 1
        // Cara pindah: bikin phase baru, set fase GameManager jadi fase baru itu (liat referensi diatas) 
        GameManager gm = super.getGameManager();
        super.getGameManager().setGameState(new MainPhase1(gm));
    }
}