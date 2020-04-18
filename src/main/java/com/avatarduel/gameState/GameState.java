// GameState.java

package com.avatarduel.gameState;

import com.avatarduel.gameManager.*;

public abstract class GameState {
    protected GameManager gameManager;

    public GameState(GameManager gameManager){
        this.gameManager = gameManager;
    }

    abstract public void StartTurn();
    abstract public void EndTurn();
}