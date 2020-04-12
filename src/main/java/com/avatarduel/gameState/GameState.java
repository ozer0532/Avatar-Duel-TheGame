// GameState.java

package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.*;

public abstract class GameState {
    private GameManager gameManager;

    public GameState(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public GameManager getGameManager(){
        return this.gameManager;
    }

    public void setGameManager(GameManager gm){
        this.gameManager = gm;
    }

    abstract public void StartTurn();
    abstract public void EndTurn();
}