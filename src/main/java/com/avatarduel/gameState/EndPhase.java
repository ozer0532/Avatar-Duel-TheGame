package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.GameManager;

public class EndPhase extends GameState{
    public EndPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Tuker current player dan opposite player
        gameManager.switchPlayer();

        // Panggil endturn
        this.EndTurn();
    }

    public void EndTurn(){
        // Pindah ke draw phase
        gameManager.setGameState(new DrawPhase(gameManager));
    }
}