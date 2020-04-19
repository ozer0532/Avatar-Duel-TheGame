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

/**
 * GameManager merupakan class yang bertanggung jawab dalam melakukan
 * management terhadap keberjalanan game, mulai dari inisialisasi aplikasi
 * hingga pergantian state di dalam suatu game.
 * GameManager ini akan digenerate di awal aplikasi dan hanya diinstansiasi
 * sebanyak satu kali.
 * @author 13518056 / Michael Hans
 */

public class GameManager {
    /**
     * Atribut-atribut dari GameManager 
     */ 
    private GameState gameState;
    private Player currentPlayer;
    private Player oppositePlayer;
    private List<CardSprite> discardPile;
    private List<IMouseClickSub> mouseClickSubs;
    private List<IMouseMoveSub> mouseMoveSubs;
    private GameDrawer gameDrawer;
    private GraphicsContext graphicsContext;
    private int winner;

    /**
     * Membuat GameManager baru berdasarkan masukan input graphics context
     * @param gc graphics context of GUI
     */
    public GameManager(GraphicsContext gc){
        
        mouseClickSubs = new ArrayList<>();
        mouseMoveSubs = new ArrayList<>();
        discardPile = new ArrayList<>();
        winner = 0;

        // Init gameDrawer
        graphicsContext = gc;
        gameDrawer = new GameDrawer(this);
        
        // Init CurrentPlayer dan oppositePlayer
        currentPlayer = new Player(false);
        oppositePlayer = new Player(true);
        
        // Generate kartu dan masukin ke player yang bersangkutan, dan simpan ke drawListnya GameDrawer
        DataLoader dl = new DataLoader();
        try {
            dl.loadCards(currentPlayer, oppositePlayer);
        } catch (Exception e) {
            System.out.println(e.toString());
            // Data pada csv tidak valid, perlu restart...
        }
        if (currentPlayer.getPlayerDeck().size() > 60 || 40 > currentPlayer.getPlayerDeck().size()) {
            throw new Error("Player 1 has an invalid deck size. Decks need to have 40 to 60 cards.");
        }
        if (oppositePlayer.getPlayerDeck().size() > 60 || 40 > oppositePlayer.getPlayerDeck().size()) {
            throw new Error("Player 2 has an invalid deck size. Decks need to have 40 to 60 cards.");
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
        registerMouseClick(new EndButton(this));

        // Init state dengan draw phase
        gameState = new DrawPhase(this);
        gameState.startTurn();
    }
    
    /**
     * Melakukan pengulangan terhadap permainan dengan durasi tertentu
     * @param durationSinceLastFrame long integer melambangkan durasi sejak frame terakhir
     */
    public void gameLoop(double durationSinceLastFrame){
        // Ini dipanggil oleh Avatar Duel pada Animation Timer pada handle()
        // Reference: (1) - Cari bagian animation (bisa dipake buat yang bukan animasi juga)
        this.gameDrawer.drawGame(durationSinceLastFrame);
    }

    /**
     * Menukar currentPlayer dan oppositePlayer diakhir giliran saat ini
     */
    public void switchPlayer(){
        // Menukar giliran player dalam bermain
        Player temp = this.currentPlayer;
        this.currentPlayer = this.oppositePlayer;
        this.oppositePlayer = temp;
    }
    
    /**
     * Mengembalikan sebuah kartu dari kartu-kartu di tangan
     * pada posisi mouse click pada koordinat X,Y dalam sebuah GUI.
     * Kembalikan null bila tidak ada kartu pada posisi tersebut
     * @param X posisi x dari mouse
     * @param Y posisi y dari mouse
     * @return sebuah kartu atau null
     */
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

    /**
     * Mengembalikan ArenaClickInfo yang berisi informasi detail terkait
     * kartu di arena yang diclick oleh mouse pada koordinat (x,y)
     * Kembalikan null bila posisi click tidak ada kartu
     * @param x posisi x dari mouse
     * @param y posisi y dari mouse
     * @return sebuah ArenaClickInfo atau null
     */
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
        } else {
            selectedCard = selectedPlayer.getPlayerArena().getSkillCard(xIndex);
        }

        return new ArenaClickInfo(selectedCard, isCharacter, isTopPlayer, xIndex, charSlotOccupied, skillSlotOccupied, !isCurrent);
    }

    /**
     * Fungsi ini mengecek bila game sudah berakhir
     * @return Mengirimkan true jika game sudah berakhir
     */
    public boolean hasGameEnded() {
        return winner != 0;
    }

    /**
     * Fungsi ini mengecek bila pemain yang menang (jika
     * game sudah berakhir) adalah pemain atas
     * @return Mengirimkan true jika pemain atas menang
     */
    public boolean topPlayerWon() {
        return winner == 2;
    }
    
    /**
     * Fungsi ini akan mengeset pemain yang menang
     * @param isTopWinner Jika true, pemain atas diset
     * menang. Jika false, pemain bawah diset menang
     */
    public void setWinner(boolean isTopWinner) {
        winner = isTopWinner ? 2 : 1;
    }

    /**
     * Mengganti gameState pada GameManager dengan gameState gs
     * @param gs sebuah gameState terbaru
     */
    public void setGameState(GameState gs){
        this.gameState = gs;
    }

    /**
     * Mengganti currentPlayer pada GameManager dengan Player P
     * @param P sebuah Player
     */
    public void setCurrentPlayer(Player P){
        this.currentPlayer = P;
    }

    /**
     * Mengganti oppositePlayer pada GameManager dengan Player P
     * @param P sebuah Player
     */
    public void setOppositePlayer(Player P){
        this.oppositePlayer = P;
    }

    /**
     * Menambahkan kartu ke dalam bagian Discard pada GUI
     * @param card sebuah kartu yang ingin didiscard
     */
    public void addToDiscardPile(Card card) {
        CardSprite sprite = card.getSprite();
        discardPile.add(sprite);
        gameDrawer.removeFromDrawList(sprite);
        gameDrawer.addToDrawList(sprite);
    }
    
    /**
     * Mengembalikan gameState dari GameManager
     * @return gameState of GameManager
     */
    public GameState getGameState(){
        return this.gameState;
    }

    /**
     * Mengembalikan currentPlayer dari GameManager
     * @return currentPlayer of GameManager
     */
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    /**
     * Mengembalikan oppositePlayer dari GameManager
     * @return oppositePlayer of GameManager
     */
    public Player getOppositePlayer(){
        return this.oppositePlayer;
    }

    /**
     * Mengambalikan list kartu yang berada pada discard
     * @return discardPile of GameManager
     */
    public List<CardSprite> getDiscardPile() {
        return this.discardPile;
    }
    
    /**
     * Mengembalikan gameDrawer dari GameManager
     * @return gameDrawer of GameManager
     */
    public GameDrawer getGameDrawer(){
        return this.gameDrawer;
    }
    
    /**
     * Mengembalikan graphicsContext dari GameManager
     * @return graphicsContext of GameManager
     */
    public GraphicsContext getGraphicsContext(){
        return this.graphicsContext;
    }
    
    /**
     * Mendaftarkan IMouseClickSub agar dikirim event nanti
     * @param click sebuah event click yang ingin diregister
     */
    public void registerMouseClick(IMouseClickSub click){
        mouseClickSubs.add(click);
    }

    /**
     * Menghapus sebuah IMouseClickSub dari dafar subscriber
     * @param click sebuah event click yang ingin diunregister
     */
    public void unregisterMouseClick(IMouseClickSub click) {
        mouseClickSubs.remove(click);
    }

    /**
     * Mendaftarkan IMouseMoveSub agar dikirim event nanti
     * @param move sebuah event IMouseMoveSub yang ingin diregister
     */
    public void registerMouseMove(IMouseMoveSub move){
        // Mendaftarkan IMouseClickSub agar dikirim event nanti
        mouseMoveSubs.add(move);
    }

    /**
     * Menghapus sebuah IMouseMoveSub dari daftar subscriber
     * @param move sebuah event IMouseMoveSub yang ingin diunregister
     */
    public void unregisterMouseMove(IMouseMoveSub move) {
        mouseMoveSubs.remove(move);
    }

    /**
     * Mengirimkan event MouseEvent ke setiap subscriber mouseClickSubs
     * @param event sebuah event yang ingin dipublish
     */
    public void sendMouseClickEvent(MouseEvent event){
        // Kirim event OnMouseClick ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse click
        System.out.println("mouseclick");
        for (int i = 0; i < mouseClickSubs.size(); i++){
            mouseClickSubs.get(i).onMouseClick(event);
        }
    }

    /**
     * Mengirimkan event MouseEvent ke setiap subscriber mouseMoveSubs
     * @param event sebuah event yang ingin dipublish
     */
    public void sendMouseMoveEvent(MouseEvent event){
        // Kirim event OnMouseMove ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse move
        for (int i = 0; i < mouseMoveSubs.size(); i++){
            mouseMoveSubs.get(i).onMouseMove(event);
        }
    }
}