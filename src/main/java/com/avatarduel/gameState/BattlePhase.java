// BattlePhase.java

package com.avatarduel.gameState;

import com.avatarduel.card.*;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;

import javafx.scene.input.MouseEvent;
import java.util.Arrays;
import java.util.List;

// BattlePhase.java
public class BattlePhase extends GameState implements IMouseClickSub{
    private Card selectedCard;
    private RoundInfo roundInfo;

    public BattlePhase(GameManager gameManager){
        super(gameManager);
    }

    public Card getSelectedCard(){
        return this.selectedCard;
    }

    public RoundInfo getRoundInfo(){
        return this.roundInfo;
    }

    public void StartTurn(){
        // Subscribe to mouse click subs
        super.getGameManager().RegisterMouseClick(this);
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        List<IMouseClickSub> mc = super.getGameManager().getMouseClickSubs();
        mc.remove(this);
        GameManager gm = super.getGameManager();
        gm.setMouseClickSubs(mc);
        super.setGameManager(gm);

        // Pindah ke main 2 phase, dan kirim round infonya
        GameState gs = new Main2Phase(gm);
        gm.setGameState(gs);
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik kartu currentplayer di arena (dan kartunya gak ada di playedCards), simpen ke selectedcard
        // Kalau engga, Selected Card set null
        Card cardClicked = getChosenCard();
        List<Character> pc = getPlayedCards();
        boolean adaKartu = pc.contains(cardClicked);
        if (!adaKartu) {
            this.selectedCard = cardClicked;
        }
        else {
            this.selectedCard = null;
        }
        // Kalau klik kartu musuh di arena, dan selectedCard ada, serang musuh dengan getAttack kartu selectedCard
        //dan getDefend musuh. Kalau musuh gak sedang defend atau ada skill power up, kurangin darah musuh.
        //Simpan kartu ke cardsAttacked di RoundInfo
        Card cardClicked2 = getSelectedCard();
        Player p = super.getGameManager().getOppositePlayer();
        Char[] cc = p.getPlayerArena().getCharCard();
        boolean cek = false;
        for (int i = 0; i < cc.length; i++) {
            if (cc[i] == cardClicked2) {
                cek = true;
                break;
            }
        }
        if ((cek) && (this.selectedCard != null) && (this.selectedCard.getIsDefense() == false)) {
            int attCur = this.selectedCard.getAttack();
            int defOpp = cardClicked2.getDefense();
            int attOpp = cardClicked2.getAttack();
            if ((cardClicked2.getIsDefense() == false) && (attCur >= attOpp)) {
                // karakter lawan mati
                for (int i=0; i < cc.length; i++) {
                    if (cc[i] == cardClicked2) {
                        cc = ArrayUtils.remove(cc, i);
                        break;
                    }
                }
                setCharCard(cc);
                int healthNow = getHealth() - (attCur - attOpp);
                p.setHealth(healthNow);
            }
            else if ((cardClicked2.getIsDefense() == true) && (attCur >= defOpp)) {
                // karakter lawan mati
                for (int i=0; i < cc.length; i++) {
                    if (cc[i] == cardClicked2) {
                        cc = ArrayUtils.remove(cc, i);
                        break;
                    }
                }
                setCharCard(cc);
            }
            addCardsAttacked(cardClicked2);
        }
    }
}