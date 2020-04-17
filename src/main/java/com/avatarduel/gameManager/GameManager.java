// GameManager.java
package com.avatarduel.gamemanager;

import com.avatarduel.card.*;
import com.avatarduel.gamestate.*;
import com.avatarduel.model.Element;
import com.avatarduel.player.*;
import com.avatarduel.sprite.*;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class GameManager {
    // Atribut-atribut dari GameManager
    private GameState gameState;
    private Player currentPlayer;
    private Player oppositePlayer;
    private List<CardSprite> discardPile;
    private List<IMouseClickSub> mouseClickSubs;
    private List<IMouseMoveSub> mouseMoveSubs;
    private GameDrawer gameDrawer;
    private GraphicsContext graphicsContext;

    // Constructor
    public GameManager(GraphicsContext gc){
        // Init gameDrawer
        graphicsContext = gc;
        gameDrawer = new GameDrawer();

        mouseClickSubs = new ArrayList<>();
        mouseMoveSubs = new ArrayList<>();
        discardPile = new ArrayList<>();
        
        // Init CurrentPlayer dan oppositePlayer
        currentPlayer = new Player(false);
        oppositePlayer = new Player(true);
        
        // Generate kartu dan masukin ke player yang bersangkutan, dan simpan ke drawListnya GameDrawer
        DataLoader dl = new DataLoader();
        try {
            dl.LoadCards(currentPlayer, oppositePlayer);
        } catch (Exception e) {
            System.out.println(e.toString());
            // Data pada csv tidak valid, perlu restart...
        }
        for (Card card : currentPlayer.getPlayerDeck()) {
            gameDrawer.addToDrawList(card.getSprite());
            card.getSprite().changePos(55, 656, true);
            card.getSprite().changeScale(-0.32, 0.32, true);
        }
        for (Card card : oppositePlayer.getPlayerDeck()) {
            gameDrawer.addToDrawList(card.getSprite());
            card.getSprite().changePos(55, 64, true);
            card.getSprite().changeScale(-0.32, 0.32, true);
        }
        
        // Init exit button dan masukin spritenya ke drawlistnya gamedrawer
        // Masukin 7 kartu ke tangan kedua player
        for (int i = 0; i < 7; i++) {
            currentPlayer.addPlayerHands(currentPlayer.getCardFromDeck());
        }
        for (int i = 0; i < 7; i++) {
            oppositePlayer.addPlayerHands(oppositePlayer.getCardFromDeck());
        }

        // Init tombol endturn
        RegisterMouseClick(new EndButton(this));

        // Init state dengan draw phase
        gameState = new DrawPhase(this);
        gameState.StartTurn();
    }
    
    // Public Method untuk GameManager
    public void gameLoop(double durationSinceLastFrame){
        // Ini dipanggil oleh Avatar Duel pada Animation Timer pada handle()
        // Reference: (1) - Cari bagian animation (bisa dipake buat yang bukan animasi juga)
        this.gameDrawer.drawGame(this, durationSinceLastFrame);
    }

    public void switchPlayer(){
        // Menukar giliran player dalam bermain
        Player temp = this.currentPlayer;
        this.currentPlayer = this.oppositePlayer;
        this.oppositePlayer = temp;
    }
    
    public Card getClickedCardFromHand(int X, int Y){
        // Cek kartu apa yang di klik dari tangan player saat itu
        // Return null kalo gak ada kartu yang di klik
        // Caranya loop untuk setiap kartu di hand kalo Sprite.OverlapPoint
        for (Card kartu : this.currentPlayer.getPlayerHands()){
            if (kartu.getSprite().isPointOverlap(X, Y)){
                return kartu;
            }
        }
        return null;
    }

    public ArenaClickInfo getArenaClickInfo(double x, double y) {
        final double xStart = 310;
        final double xOffset = 95;
        final double yStart = 120;
        final double yOffset = 120;

        int xIndex = (int)((x - xStart) / xOffset);
        int yIndex = (int)((y - yStart) / yOffset);

        if (xIndex < 0 || xIndex > 7 || yIndex < 0 || yIndex > 3) {
            return null;
        }

        boolean isCharacter = (yIndex == 1 || yIndex == 2);
        boolean isTopPlayer = yIndex < 2;
        boolean isCurrent = getCurrentPlayer().getIsTopPlayer() == isTopPlayer;
        Player selectedPlayer = (isCurrent) ? getCurrentPlayer() : getOppositePlayer();

        boolean charSlotOccupied = selectedPlayer.getPlayerArena().getCharCard(xIndex) != null;
        boolean skillSlotOccupied = selectedPlayer.getPlayerArena().getSkillCard(xIndex) != null;

        Card selectedCard;
        if (isCharacter) {
            selectedCard = selectedPlayer.getPlayerArena().getCharCard(xIndex);
            System.out.println(selectedPlayer.getPlayerArena().getCharCard(xIndex) == null);
        } else {
            selectedCard = selectedPlayer.getPlayerArena().getSkillCard(xIndex);
            System.out.println(selectedPlayer.getPlayerArena().getSkillCard(xIndex) == null);
        }

        return new ArenaClickInfo(selectedCard, isCharacter, isTopPlayer, xIndex, charSlotOccupied, skillSlotOccupied, !isCurrent);
    }

    // Setter untuk GameManager
    public void setGameState(GameState gs){
        this.gameState = gs;
    }

    public void setCurrentPlayer(Player P){
        this.currentPlayer = P;
    }

    public void setOppositePlayer(Player P){
        this.oppositePlayer = P;
    }

    public void addToDiscardPile(Card card) {
        discardPile.add(card.getSprite());
    }
    
    // Getter untuk GameManager
    public GameState getGameState(){
        return this.gameState;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public Player getOppositePlayer(){
        return this.oppositePlayer;
    }

    public List<CardSprite> getDiscardPile() {
        return this.discardPile;
    }
    
    public GameDrawer getGameDrawer(){
        return this.gameDrawer;
    }
    
    public GraphicsContext getGraphicsContext(){
        return this.graphicsContext;
    }
    
    // Subscriber-Publisher Method
    public void RegisterMouseClick(IMouseClickSub click){
        // Mendaftarkan IMouseClickSub agar dikirim event nanti
        mouseClickSubs.add(click);
    }

    public void UnregisterMouseClick(IMouseClickSub click) {
        mouseClickSubs.remove(click);
    }

    public void RegisterMouseMove(IMouseMoveSub move){
        // Mendaftarkan IMouseClickSub agar dikirim event nanti
        mouseMoveSubs.add(move);
    }

    public void UnregisterMouseMove(IMouseMoveSub move) {
        mouseMoveSubs.remove(move);
    }

    public void sendMouseClickEvent(MouseEvent event){
        // Kirim event OnMouseClick ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse click
        System.out.println("mouseclick");
        for (int i = 0; i < mouseClickSubs.size(); i++){
            mouseClickSubs.get(i).OnMouseClick(event);
        }
    }

    public void sendMouseMoveEvent(MouseEvent event){
        // Kirim event OnMouseMove ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse move
        for (int i = 0; i < mouseMoveSubs.size(); i++){
            mouseMoveSubs.get(i).OnMouseMove(event);
        }
    }
}