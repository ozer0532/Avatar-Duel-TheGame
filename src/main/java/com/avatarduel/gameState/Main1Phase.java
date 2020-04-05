package com.avatarduel.gameState;

public class Main1Phase extends GameState {
    public Main1Phase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Subscribe to mouse click subs
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        // Pindah ke battle phase, dan kirim round infonya
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik di kartu di hand, simpan kartu ke SelectedCard
        // Kalau ada kartu yang selected, dan klik di arena di area yang valid, pindahin kartu ke arena (Cek dulu cardCanBePlayed), simpan kartu ke playedCards di RoundInfo.
        // Kalau engga dan klik di arena, putar kartu Karakter atau buang kartu skill
        // Kalau engga, SelectedCard set null
    }
}