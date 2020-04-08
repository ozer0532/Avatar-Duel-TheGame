package com.avatarduel.gameState;

public class Main2Phase extends GameState{
    private Card selectedCard;
    private RoundInfo roundInfo;

    public void StartTurn(){
        // Subscribe to mouse click subs
        GameManager.RegisterMouse(this);
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        // Pindah ke end phase
        GameManager gm = getGameManager();
        GameState gs = EndPhase;
        gm.setGameState(gs);
        super.gameState(gm);
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik di kartu di hand, simpan kartu ke SelectedCard
		// Kalau ada kartu yang selected, dan klik di arena di area yang valid, pindahin kartu ke arena (Cek dulu cardCanBePlayed), simpan kartu ke playedCards di RoundInfo.
		// Kalau engga dan klik di arena, putar kartu Karakter (kalo karakter gak ada di cardsAttacked) atau buang kartu skill
		// Kalau engga, SelectedCard set null
    }
}