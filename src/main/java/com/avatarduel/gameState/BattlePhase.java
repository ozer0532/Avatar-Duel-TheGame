// BattlePhase.java

package com.avatarduel.gamestate;

import com.avatarduel.card.*;
import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;

import javafx.scene.input.MouseEvent;
import java.util.Arrays;
import java.util.List;

// BattlePhase.java
public class BattlePhase extends GameState implements IMouseClickSub{
    private GameManager gm;
    private Card selectedCard;
    private Player pemain;
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
        this.gm = super.getGameManager();
        List<IMouseClickSub> mc = this.gm.getMouseClickSubs();
        mc.remove(this);
        this.gm.setMouseClickSubs(mc);

        // Pindah ke main 2 phase, dan kirim round infonya
        GameState gs = new EndPhase(this.gm);
        this.gm.setGameState(gs);
        super.setGameManager(this.gm);
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik kartu currentplayer di arena (dan kartunya gak ada di playedCards), simpen ke selectedcard
        // Kalau engga, Selected Card set null
        double X = event.getX();
        double Y = event.getY();
        this.gm = super.getGameManager();
        this.pemain = this.gm.getCurrentPlayer();
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
        Card cardClicked = getSelectedCard();
        Player p = super.getGameManager().getOppositePlayer();
        Char[] cc = p.getPlayerArena().getCharCard();
        boolean cek = false;
        for (int j = 0; j < cc.length; j++) {
            if (cc[j] == cardClicked) {
                cek = true;
                break;
            }
        }
        if ((cek) && (this.selectedCard != null) && (this.selectedCard.getIsDefense() == false)) {
            int attCur = this.selectedCard.getAttack();
            int defOpp = cardClicked.getDefense();
            int attOpp = cardClicked.getAttack();
            if ((cardClicked.getIsDefense() == false) && (attCur >= attOpp)) {
                // karakter lawan mati
                for (int a=0; i < cc.length; a++) {
                    if (cc[a] == cardClicked2) {
                        cc = ArrayUtils.remove(cc, a);
                        break;
                    }
                }
                setCharCard(cc);
                int healthNow = getHealth() - (attCur - attOpp);
                p.setHealth(healthNow);
            }
            else if ((cardClicked.getIsDefense() == true) && (attCur >= defOpp)) {
                // karakter lawan mati
                for (int b=0; i < cc.length; b++) {
                    if (cc[b] == cardClicked) {
                        cc = ArrayUtils.remove(cc, b);
                        break;
                    }
                }
                setCharCard(cc);
            }
            addCardsAttacked(cardClicked);
        }
    }
}