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
        Card a;
        a = getCardFromDeck();
        addPlayerHands(a);
    }

    public void EndTurn(){
        // Pindah ke main phase 1
        // Cara pindah: bikin phase baru, set fase GameManager jadi fase baru itu (liat referensi diatas) 
        GameManager gm = getGameManager();
        GameState gs = Main1Phase;
        gm.setGameState(gs);
        super.gameState(gm);
    }
}