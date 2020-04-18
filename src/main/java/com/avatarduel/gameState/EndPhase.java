package com.avatarduel.gameState;

import com.avatarduel.gameManager.GameManager;

public class EndPhase extends GameState{
    public EndPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Tuker current player dan opposite player
        System.out.println(">>>>> END PHASE START <<<<<");
        gameManager.getCurrentPlayer().getPlayerStats().resetStats();
        gameManager.switchPlayer();

        // Panggil endturn
        this.EndTurn();
    }

    public void EndTurn(){
        // Pindah ke draw phase
        gameManager.setGameState(new DrawPhase(gameManager));
        gameManager.getGameState().StartTurn();
    }
}