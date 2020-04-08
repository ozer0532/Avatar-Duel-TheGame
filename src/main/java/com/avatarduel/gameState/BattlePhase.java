// BattlePhase.java

package com.avatarduel.gameState;

import com.avatarduel.card.*;
import com.avatarduel.gameManager.*;

public class BattlePhase extends GameState implements IMouseClickSub{
    private Card selectedCard;
    private RoundInfo roundInfo;

    public void StartTurn(){
        // Subscribe to mouse click subs
        GameManager.RegisterMouse(this);
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        // Pindah ke main 2 phase, dan kirim round infonya
        GameManager gm = getGameManager();
        GameState gs = Main2Phase;
        gm.setGameState(gs);
        super.gameState(gm);
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik kartu currentplayer di arena (dan kartunya gak ada di playedCards), simpen ke selectedcard
        // Kalau engga, Selected Card set null
        if () {
            // this.selectedCard = kartu yang si klik;
        }
        else {
            this.selectedCard = null;
        }
        // Kalau klik kartu musuh di arena, dan selectedCard ada, serang musuh dengan getAttack kartu selectedCard
        //dan getDefend musuh. Kalau musuh gak sedang defend atau ada skill power up, kurangin darah musuh.
        //Simpan kartu ke cardsAttacked di RoundInfo
        if ( && this.selectedCard != null) {
            
        }
    }
}