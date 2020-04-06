package com.avatarduel.gameState;

abstract class GameState {
    private GameManager gameManager;
    public GameState(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public GameManager getGameManager(){
        return this.gameManager;
    }
    abstract public void StartTurn();
    abstract public void EndTurn();
}