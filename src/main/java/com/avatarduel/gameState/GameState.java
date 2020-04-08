// GameState.java

package com.avatarduel.gameState;

import com.avatarduel.gameManager.*;

abstract class GameState {
    private GameManager gameManager;

    public gameState(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public GameManager getGameManager(){
        return this.gameManager;
    }

    abstract public void StartTurn();
    abstract public void EndTurn();
}