package com.avatarduel.gameState;

import com.avatarduel.gameManager.GameManager;

public class EndPhase extends GameState{
    public EndPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Tuker current player dan opposite player
        GameManager gm = super.getGameManager()
        gm.switchPlayer();
        super.setGameManager(gm);

        // Panggil endturn
        this.EndTurn();
    }

    public void EndTurn(){
        // Pindah ke draw phase
        GameManager gm = getGameManager();
        gm.setGameState(gs);
        GameState gs = new DrawPhase(gm);
        super.gameState(gm);
    }
}