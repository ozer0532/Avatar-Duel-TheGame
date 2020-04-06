package com.avatarduel.gameState;

public class BattlePhase {
    private Card selectedCard;
    private RoundInfo roundInfo;

    public void StartTurn(){
        // Subscribe to mouse click subs
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        // Pindah ke main 2 phase, dan kirim round infonya
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik kartu currentplayer di arena (dan kartunya gak ada di playedCards), simpen ke selectedcard
        // Kalau klik kartu musuh di arena, dan selectedCard ada, serang musuh dengan getAttack kartu selectedCard dan getDefend musuh. Kalau musuh gak sedang defend atau ada skill power up, kurangin darah musuh. Simpan kartu ke cardsAttacked di RoundInfo
        // Kalau engga, Selected Card set null
    }
}