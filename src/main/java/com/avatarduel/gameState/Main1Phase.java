// Main1Phase.java
package com.avatarduel.gamestate;

import java.util.List;

import com.avatarduel.card.*;
import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.sprite.*;

import javafx.scene.input.MouseEvent;

/**
 * Main1Phase merupakan class yang bertanggung jawab untuk melakukan persiapan sebelum BattlePhase.
 * Persiapan yang dilakukan dapat berupa meletakkan kartu dari tangan ke arena, mengubah posisi kartu
 * karakter (attack/defense) di arena, dan/atau membuang kartu skill di arena.
 * Main1Phase akan di-generate setiap giliran pemain untuk melakukan persiapan sebelum battle.
 */

public class Main1Phase extends GameState implements IMouseClickSub{
    private Player pemain;
    private Card selectedCard;
    private RoundInfo roundInfo;

    /**
     * Membuat Main1Phase baru dengan masukan gameManager
     * @param gameManager gameManager sekarang
     */
    public Main1Phase(GameManager gameManager){
        super(gameManager);
        roundInfo = new RoundInfo();
    }

    /**
     * Memulai Main1Phase dan melakukan subscribe untuk mouse click
     */
    @Override
    public void startTurn(){
        // Subscribe to mouse click subs
        gameManager.registerMouseClick(this);
        System.out.println(">>>>> MAIN PHASE START <<<<<");
    }

    /**
     * Melakukan unsubscribe untuk mouse click dan mengganti state game ke BattlePhase
     */
    @Override
    public void endTurn(){
        // Unsubscribe to mouse click subs
        gameManager.unregisterMouseClick(this);

        // Pindah ke main 2 phase, dan kirim round infonya
        GameState gs = new BattlePhase(gameManager, roundInfo);
        gameManager.setGameState(gs);
        gameManager.getGameState().startTurn();
    }

    /**
     * Melakukan persiapan terhadap arena sendiri sebelum battle
     * @param event input event dari mouse
     */
    public void onMouseClick (MouseEvent event){
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
            gameManager.getGameDrawer().setHighlightedCard(this.selectedCard.getSprite());
            System.out.println(this.selectedCard.getName());
        }
        else {
            // Kalau ada kartu yang selected, dan klik di arena di area yang valid, pindahin kartu ke arena
            // (Cek dulu cardCanBePlayed), simpan kartu ke playedCards di RoundInfo.
            // Kalau engga dan klik di arena, putar kartu Karakter atau buang kartu skill
            ArenaClickInfo info = gameManager.getArenaClickInfo(X, Y);
            if (info != null) {

                if ((this.selectedCard != null) && (this.selectedCard.canBePlayed(this.pemain))) {
                    System.out.println("----PLAYING CARD-----");
                    if (this.selectedCard instanceof Char) {
                        System.out.println("----CHAR-----");
                        if (!info.getCharacterSlotOccupied()) {
                            this.selectedCard.onCardPlayed(gameManager, info.getIdx(), info.getIsEnemy());
                            roundInfo.addPlayedCards((Char) this.selectedCard);
                        }
                    }
                    else if (this.selectedCard instanceof Skill) {
                        System.out.println("----SKILL-----");
                        if (!info.getSkillSlotOccupied()) {
                            this.selectedCard.onCardPlayed(gameManager, info.getIdx(), info.getIsEnemy());
                        }
                    } else {
                        System.out.println("----LAND-----");
                        assert this.selectedCard instanceof Land;
                        this.selectedCard.onCardPlayed(gameManager, info.getIdx(), info.getIsEnemy());
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
            gameManager.getGameDrawer().setHighlightedCard(null);
        }
    }
}