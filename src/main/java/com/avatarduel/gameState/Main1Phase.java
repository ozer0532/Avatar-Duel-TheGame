package com.avatarduel.gameState;

import com.avatarduel.gameManager.GameManager;

public class Main1Phase extends GameState {
    private Card selectedCard;
    private RoundInfo roundInfo;

    public Main1Phase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Subscribe to mouse click subs
        GameManager.RegisterMouse(this);
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        
        // Pindah ke battle phase, dan kirim round infonya
        GameManager gm = getGameManager();
        GameState gs = BattlePhase;
        gm.setGameState(gs);
        super.gameState(gm);
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik di kartu di hand, simpan kartu ke SelectedCard
        // Kalau engga, SelectedCard set null
        if () {

        }
        else {
            this.selectedCard = null;
        }
        // Kalau ada kartu yang selected, dan klik di arena di area yang valid, pindahin kartu ke arena
        // (Cek dulu cardCanBePlayed), simpan kartu ke playedCards di RoundInfo.
        // Kalau engga dan klik di arena, putar kartu Karakter atau buang kartu skill
        if (this.selectedCard != null) {

        }
        else {
            
        }
    }
}