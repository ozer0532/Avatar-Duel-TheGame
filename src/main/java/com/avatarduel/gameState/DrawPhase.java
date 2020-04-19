// DrawPhase.java
package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.IMouseClickSub;
import com.avatarduel.player.*;

import javafx.scene.input.MouseEvent;

import com.avatarduel.card.*;

/**
 * DrawPhase merupakan class yang bertanggung jawab dalam fase mengambil kartu dari dek ke tangan
 * dari pemain dengan giliran bermain saat ini.
 * DrawPhase akan di-generate setiap giliran pemain untuk mengambil kartu.
 */

public class DrawPhase extends GameState implements IMouseClickSub {
    private Player pemain;
    private Card kartu;

    /**
     * Membuat DrawPhase baru dengan masukan gameManager
     * @param gameManager gameManager sekarang
     */
    public DrawPhase(GameManager gameManager){
        super(gameManager);
    }

    /**
     * Memulai DrawPhase dan mengambil kartu dari dek lalu meletakkannya di tangan
     */
    @Override
    public void startTurn(){
        // Ambil kartu dari deck player saat ini
        // Masukin kartu ke hand
        System.out.println(">>>>> DRAW PHASE START <<<<<");
        this.pemain = gameManager.getCurrentPlayer();
        gameManager.registerMouseClick(this);
        if (!this.pemain.getPlayerDeck().empty()) {
            if (this.pemain.getPlayerHands().size() < 12) {
                this.kartu = this.pemain.getCardFromDeck();
                this.pemain.addPlayerHands(this.kartu);
                endTurn();
            }
        } else {
            gameManager.setWinner(!this.pemain.getIsTopPlayer());
        }
    }

    /**
     * Mengganti state game menjadi Main1Phase
     */
    @Override
    public void endTurn(){
        gameManager.unregisterMouseClick(this);
        gameManager.setGameState(new Main1Phase(this.gameManager));
        gameManager.getGameState().startTurn();
    }

    /**
     * Membuang kartu yang ada di tangan lalu mengambil kartu baru dari dek
     * @param e input event dari mouse
     */
    public void onMouseClick(MouseEvent e) {
        for (int i=0; i < this.pemain.getPlayerHands().size(); i++) {
            if (this.pemain.getPlayerHands().get(i).getSprite().isPointOverlap(e.getX(), e.getY())) {
                Card card = this.pemain.getPlayerHands().get(i);
                this.pemain.getPlayerHands().remove(card);
                gameManager.addToDiscardPile(card);
                this.kartu = this.pemain.getCardFromDeck();
                this.pemain.addPlayerHands(this.kartu);
                endTurn();
                break;
            }
        }
    }
}