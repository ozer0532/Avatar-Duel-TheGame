package com.avatarduel.gameState;

public class DrawPhase extends GameState {
    public DrawPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Ambil kartu dari deck player saat ini
        // Masukin kartu ke hand
    }

    public void EndTurn(){
        // Pindah ke main phase 1
        // Cara pindah: bikin phase baru, set fase GameManager jadi fase baru itu (liat referensi diatas) 
    }
}