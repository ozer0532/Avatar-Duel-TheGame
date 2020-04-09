// BattlePhase.java

package com.avatarduel.gameState;

import com.avatarduel.card.*;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;

import javafx.scene.input.MouseEvent;
import java.util.Arrays;

// BattlePhase.java
public class BattlePhase extends GameState implements IMouseClickSub{
    private Card selectedCard;
    private RoundInfo roundInfo;

    public BattlePhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Subscribe to mouse click subs
        RegisterMouseClick(this);
        RegisterMouseMove(this);
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        List<IMouseClickSub> mc = getMouseClickSubs();
        List<IMouseMoveSub> mm = getMouseMoveSubs();
        mc.remove(this);
        mm.remove(this);
        setMouseClickSubs(mc);
        setMouseMoveSubs(mm);
        // Pindah ke main 2 phase, dan kirim round infonya
        GameManager gm = getGameManager();
        GameState gs = new Main2Phase(gm);
        gm.setGameState(gs);
        super.gameState(gm);
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
        Card cardClicked2 = getChosenCard();
        Player p = getOppositePlayer();
        Character[] cc = p.getPlayerArena().getCharCard();
        bool cek = false;
        for (int i=0; i < cc.length; i++) {
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

    @Override
    public void OnMouseClick(MouseEvent event) {
        // TODO Auto-generated method stub

    }
}