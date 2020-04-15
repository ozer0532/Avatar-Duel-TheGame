package com.avatarduel.gamestate;

import java.util.List;

import com.avatarduel.card.*;
import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.sprite.*;

import javafx.scene.input.MouseEvent;

public class Main1Phase extends GameState implements IMouseClickSub{
    private GameManager gm;
    private Player pemain;
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

        // Kalau ada kartu yang selected, dan klik di arena di area yang valid, pindahin kartu ke arena
        // (Cek dulu cardCanBePlayed), simpan kartu ke playedCards di RoundInfo.
		// Kalau engga dan klik di arena, putar kartu Karakter atau buang kartu skill
        boolean cek = this.selectedCard.CanBePlayed(this.pemain.getPlayerStats());
        RoundInfo roundinfo = new RoundInfo();
        if ((this.selectedCard != null) && (cek)) {
            if (this.selectedCard instanceof Char) {
                // if (!ArenaClickInfo.getCharacterSlotOccupied()) {
                    Char ch = (Char) this.selectedCard;
                    this.pemain.getPlayerArena().addCharacterCard(ch);
                    roundinfo.addPlayedCards(ch);
                // }
            }
            else if (this.selectedCard instanceof Skill) {
                // if (!ArenaClickInfo.getSkillSlotOccupied()) {
                    Skill sk = (Skill) this.selectedCard;
                    this.pemain.getPlayerArena().addSkillCard(sk);
                // }
            }
            this.selectedCard = null;
        }
        else {
            // Pemain tidak memilih kartu di tangan dan (mungkin) memilih kartu di arena
            boolean cekArena = false;
            for (i=0; i < this.pemain.getPlayerHands().size(); i++) {
                if (this.pemain.getPlayerHands().get(i).getSprite().isPointOverlap(X, Y)) {
                    cekArena = true;
                    break;
                }
            }
            if (this.selectedCard instanceof Char) {
                Char ch = (Char) this.selectedCard;
                if (ch.getIsDefense() == true) {
                    ch.setIsDefense(false);
                }
                else {
                    ch.setIsDefense(true);
                }
            }
            else if (this.selectedCard instanceof Skill) {
                this.pemain.getPlayerArena().removeSkillCard(i);
            }
        }
        super.setGameManager(gm);
    }
}