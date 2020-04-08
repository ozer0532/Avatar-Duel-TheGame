package com.avatarduel.gameState;

public class EndPhase extends GameState{
    public EndPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Tuker current player dan opposite player
        Player temp;
        temp = currentPlayer;
        currentPlayer = oppositePlayer;
        oppositePlayer = temp;
        // Panggil endturn
        EndTurn();
    }

    public void EndTurn(){
        // Pindah ke draw phase
        GameManager gm = getGameManager();
        GameState gs = DrawPhase;
        gm.setGameState(gs);
        super.gameState(gm);
    }
}