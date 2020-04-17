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
    private Char selectedCard;
    private int selectedCardIndex;
    private Player pemain;
    private RoundInfo roundInfo;

    public BattlePhase(GameManager gameManager, RoundInfo info){
        super(gameManager);
        this.roundInfo = info;
    }

    public void StartTurn(){
        // Subscribe to mouse click subs
        System.out.println(">>>>> BATTLE PHASE START <<<<<");
        gameManager.RegisterMouseClick(this);
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        gameManager.UnregisterMouseClick(this);

        // Pindah ke main 2 phase, dan kirim round infonya
        GameState gs = new EndPhase(gameManager);
        gameManager.setGameState(gs);
        gameManager.getGameState().StartTurn();
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik kartu currentplayer di arena (dan kartunya gak ada di playedCards), simpen ke selectedcard
        // Kalau engga, Selected Card set null
        double X = event.getX();
        double Y = event.getY();
        this.pemain = gameManager.getCurrentPlayer();
        boolean cekOverlap = false;
        int i;
        ArenaClickInfo info = gameManager.getArenaClickInfo(X, Y);
        if (info != null) {
            if (!info.getIsEnemy()) {
                if (info.getCharacterSlotOccupied()) {
                    System.out.println("----USING CARD-----");
                    selectedCard = gameManager.getCurrentPlayer().getPlayerArena().getCharCard(info.getIdx());
                    if (roundInfo.getPlayedCards().contains(selectedCard) || roundInfo.getCardsAttacked().contains(selectedCard)) {
                        System.out.println("----CANCEL USAGE-----");
                        selectedCard = null;
                    } else {
                        selectedCardIndex = info.getIdx();
                    }
                }
            } else {
                // Kalau klik kartu musuh di arena, dan selectedCard ada, serang musuh dengan getAttack kartu selectedCard
                //dan getDefend musuh. Kalau musuh gak sedang defend atau ada skill power up, kurangin darah musuh.
                //Simpan kartu ke cardsAttacked di RoundInfo
                if (info.getCharacterSlotOccupied() && selectedCard != null) {
                    Player opp = gameManager.getOppositePlayer();
                    Player cur = gameManager.getCurrentPlayer();
                    Char charOpp = opp.getPlayerArena().getCharCard(info.getIdx());
                    Char charCur = this.selectedCard;
                    Aura auraOpp = null;
                    Aura auraCur = null;
                    if (cur.getPlayerArena().getSkillCard(selectedCardIndex) instanceof Aura) {
                        auraCur = (Aura)cur.getPlayerArena().getSkillCard(selectedCardIndex);
                    }
                    if (opp.getPlayerArena().getSkillCard(info.getIdx()) instanceof Aura) {
                        auraOpp = (Aura)cur.getPlayerArena().getSkillCard(selectedCardIndex);
                    }

                    int attCur = charCur.getAttack() + (auraCur != null ? auraCur.getAttack() : 0);
                    int defOpp = charOpp.getDefense() + (auraOpp != null ? auraOpp.getDefense() : 0);
                    int attOpp = charOpp.getAttack() + (auraOpp != null ? auraOpp.getAttack() : 0);
                    System.out.println("----ATTACKING CARD-----");
                    if (!charOpp.getIsDefense() && (attCur > attOpp)) {
                        // karakter lawan mati
                        opp.getPlayerArena().removeCharCard(info.getIdx());
                        gameManager.addToDiscardPile(charOpp);
                        roundInfo.addCardsAttacked(charCur);
                    }
                    else if (charOpp.getIsDefense() && (attCur > defOpp)) {
                        // karakter lawan mati
                        opp.getPlayerArena().removeCharCard(info.getIdx());
                        gameManager.addToDiscardPile(charOpp);
                        roundInfo.addCardsAttacked(charCur);
                        if (cur.getPlayerArena().getSkillCard(info.getIdx()) instanceof PowerUp) {
                            opp.getPlayerStats().takeDamage(attCur - attOpp);
                        }
                    } else {
                        System.out.println("----ATTACK FAILED-----");
                    }
                }
                selectedCard = null;
            }
        }
    }
}