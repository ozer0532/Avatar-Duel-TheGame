// BattlePhase.java

package com.avatarduel.gamestate;

import com.avatarduel.card.*;
import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.sprite.*;

import javafx.scene.input.MouseEvent;
import java.util.List;
import java.util.Arrays; 
import java.util.stream.IntStream; 

// BattlePhase.java
public class BattlePhase extends GameState implements IMouseClickSub{
    private Card selectedCard;
    private Player pemain;
    private RoundInfo roundInfo;

    public BattlePhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Subscribe to mouse click subs
        gameManager.RegisterMouseClick(this);
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        gameManager.UnregisterMouseClick(this);

        // Pindah ke main 2 phase, dan kirim round infonya
        GameState gs = new EndPhase(gameManager);
        gameManager.setGameState(gs);
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik kartu currentplayer di arena (dan kartunya gak ada di playedCards), simpen ke selectedcard
        // Kalau engga, Selected Card set null
        double X = event.getX();
        double Y = event.getY();
        this.pemain = gameManager.getCurrentPlayer();
        boolean cekOverlap = false;
        int i;
        for (i=0; i < this.pemain.getPlayerHands().size(); i++) {
            if (this.pemain.getPlayerHands().get(i).getSprite().isPointOverlap(X, Y)) {
                cekOverlap = true;
                break;
            }
        }
        if (cekOverlap) {
            this.selectedCard = this.pemain.getPlayerHands().get(i);
        }
        else {
            this.selectedCard = null;
        }

        // Kalau klik kartu musuh di arena, dan selectedCard ada, serang musuh dengan getAttack kartu selectedCard
        //dan getDefend musuh. Kalau musuh gak sedang defend atau ada skill power up, kurangin darah musuh.
        //Simpan kartu ke cardsAttacked di RoundInfo
        double A = event.getX();
        double B = event.getY();
        RoundInfo roundinfo = new RoundInfo();
        Player opp = gameManager.getOppositePlayer();
        Char[] cc = opp.getPlayerArena().getCharCard();
        Card cardOpp;
        boolean cek = false;
        for (i = 0; i < cc.length; i++) {
            if (opp.getPlayerHands().get(i).getSprite().isPointOverlap(A, B)) {
                cek = true;
                break;
            }
        }
        if (cek) {
            cardOpp = opp.getPlayerArena().getCharCard(i);
        }
        else {
            cardOpp = null;
        }
        Char charCur = (Char) this.selectedCard;
        Char charOpp = (Char) cardOpp;
        if ((cekOverlap) && (cek) && (this.selectedCard != null) && (charCur.getIsDefense() == false)) {
            int attCur = charCur.getAttack();
            int defOpp = charOpp.getDefense();
            int attOpp = charOpp.getAttack();
            if ((charOpp.getIsDefense() == false) && (attCur >= attOpp)) {
                // karakter lawan mati
                opp.getPlayerArena().removeCharCard(i);
                int healthNow = opp.getPlayerStats().getHealth() - (attCur - attOpp);
                opp.getPlayerStats().setHealth(healthNow);
            }
            else if ((charOpp.getIsDefense() == true) && (attCur >= defOpp)) {
                // karakter lawan mati
                opp.getPlayerArena().removeCharCard(i);
            }
            roundinfo.addCardsAttacked(charOpp);
        }
    }
}