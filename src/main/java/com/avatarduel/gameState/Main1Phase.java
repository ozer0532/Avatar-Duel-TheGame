package com.avatarduel.gamestate;

import java.util.List;

import com.avatarduel.card.*;
import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.sprite.*;

import javafx.scene.input.MouseEvent;

public class Main1Phase extends GameState implements IMouseClickSub{
    private Player pemain;
    private Card selectedCard;
    private RoundInfo roundInfo;

    public Main1Phase(GameManager gameManager){
        super(gameManager);
        roundInfo = new RoundInfo();
    }

    public void StartTurn(){
        // Subscribe to mouse click subs
        gameManager.RegisterMouseClick(this);
        System.out.println(">>>>> MAIN PHASE START <<<<<");
    }

    public void EndTurn(){
        // Unsubscribe to mouse click subs
        gameManager.UnregisterMouseClick(this);

        // Pindah ke main 2 phase, dan kirim round infonya
        GameState gs = new BattlePhase(gameManager);
        gameManager.setGameState(gs);
        gameManager.getGameState().StartTurn();
    }

    public void OnMouseClick (MouseEvent event){
        // Kalau klik di kartu di hand, simpan kartu ke SelectedCard
        // Kalau engga, SelectedCard set null
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
            System.out.println(this.selectedCard.getName());
        }
        else {
            // Kalau ada kartu yang selected, dan klik di arena di area yang valid, pindahin kartu ke arena
            // (Cek dulu cardCanBePlayed), simpan kartu ke playedCards di RoundInfo.
            // Kalau engga dan klik di arena, putar kartu Karakter atau buang kartu skill
            ArenaClickInfo info = gameManager.getArenaClickInfo(X, Y);
            if (info != null) {

                if ((this.selectedCard != null) && (this.selectedCard.CanBePlayed(this.pemain.getPlayerStats()))) {
                    System.out.println("----PLAYING CARD-----");
                    if (this.selectedCard instanceof Char) {
                        System.out.println("----CHAR-----");
                        if (!info.getCharacterSlotOccupied()) {
                            System.out.println("test");
                            this.selectedCard.OnCardPlayed(gameManager, info.getIdx(), info.getIsEnemy());
                            roundInfo.addPlayedCards((Char) this.selectedCard);
                        }
                    }
                    else if (this.selectedCard instanceof Skill) {
                        System.out.println("----SKILL-----");
                        if (!info.getSkillSlotOccupied()) {
                            this.selectedCard.OnCardPlayed(gameManager, info.getIdx(), info.getIsEnemy());
                            this.pemain.getPlayerArena().addSkillCard((Skill) this.selectedCard);
                        }
                    } else {
                        System.out.println("----LAND-----");
                        assert this.selectedCard instanceof Land;
                        this.selectedCard.OnCardPlayed(gameManager, info.getIdx(), info.getIsEnemy());
                    }
                    this.selectedCard = null;
                }
                else {
                    // Pemain tidak memilih kartu di tangan dan (mungkin) memilih kartu di arena
                    if (info.getChosenCard() != null) {
                        System.out.println("----MODDING ARENA-----");
                        if (info.getChosenCard() instanceof Char) {
                            System.out.println("-----SWITCH STANCE-----");
                            Char ch = (Char) info.getChosenCard();
                            if (ch.getIsDefense() == true) {
                                ch.setIsDefense(false);
                            }
                            else {
                                ch.setIsDefense(true);
                            }
                        } else if (info.getChosenCard() instanceof Skill) {
                            System.out.println("-----REMOVE SKILL-----");
                            gameManager.addToDiscardPile(this.pemain.getPlayerArena().getSkillCard(info.getIdx()));
                            this.pemain.getPlayerArena().removeSkillCard(info.getIdx());
                        }
                    }
                }
            }
            
            this.selectedCard = null;
        }
    }
}