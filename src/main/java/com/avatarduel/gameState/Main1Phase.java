package com.avatarduel.gameState;

import java.util.List;

import com.avatarduel.card.*;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;

public class Main1Phase extends GameState implements IMouseClickSub{
    private GameManager gm;
    private Card selectedCard;
    private RoundInfo roundInfo;
    private ArenaClickInfo aci;

    public Main1Phase(GameManager gameManager){
        super(gameManager);
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
        GameState gs = new BattlePhase(this.gm);
        this.gm.setGameState(gs);
        super.setGameManager(this.gm);
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik di kartu di hand, simpan kartu ke SelectedCard
        // Kalau engga, SelectedCard set null
        Card cardClicked = getChosenCard();
        List<Character> pc = getPlayedCards();
        boolean adaKartu = pc.contains(cardClicked);
        if (!adaKartu) {
            this.selectedCard = cardClicked;
        }
        else {
            this.selectedCard = null;
        }
        // Kalau ada kartu yang selected, dan klik di arena di area yang valid, pindahin kartu ke arena
        // (Cek dulu cardCanBePlayed), simpan kartu ke playedCards di RoundInfo.
		// Kalau engga dan klik di arena, putar kartu Karakter atau buang kartu skill
        boolean cek = CanBePlayed(getPlayerStats());
        if ((this.selectedCard != null) && (cek)) {
            if (cardClicked == charCard) {
                if (isCharacter && !characterSlotOccupied) {
                    addCharacterCard(event, cardClicked);
                }
            }
            else if (cardClicked == skills) {
                if (!isCharacter && !skillSlotOccupied) {
                    addSkillCard(event, cardClicked);
                }
            }
            addPlayedCards(cardClicked);
        }
        else {
            Card c = getChosenCard();
            if (c == charCard) {
                if (getIsDefense() == true) {
                    setIsDefense(false);
                }
                else {
                    setIsDefense(true);
                }
            }
            else if (c == skills) {
                removeSkillCard(event);
            }
        }
    }
}