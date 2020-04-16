// GameState.java

package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.*;

public abstract class GameState {
    protected GameManager gameManager;

    public GameState(GameManager gameManager){
        this.gameManager = gameManager;
    }

    abstract public void StartTurn();
    abstract public void EndTurn();
}