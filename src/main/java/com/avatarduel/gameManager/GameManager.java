// GameManager.java
package com.avatarduel.gameManager;

import com.avatarduel.player.*;
import com.avatarduel.sprite.*;
import com.avatarduel.gameState.*;
import javafx.scene.canvas.GraphicsContext.*;
import java.util.List;

public class GameManager {
    private GameState gameState;
    private Player currentPlayer;
    private Player oppositePlayer;
    private List<IMouseClickSub> mouseClickSubs;
    private GameDrawer gameDrawer;
    private GraphicsContext graphicsContext;

    // Setter
    public void setGameState(GameState gs){
        this.gameState = gs;
    }

    public void switchPlayer(){
        Player temp = this.currentPlayer;
        this.currentPlayer = this.oppositePlayer;
        this.oppositePlayer = temp;
    }

    public void setCurrentPlayer(Player P){
        this.currentPlayer = P;
    }

    public void setOppositePlayer(Player P){
        this.oppositePlayer = P;
    }

    public void setGameDrawer(GameDrawer gd){
        this.gameDrawer = gd;
    }

    public void setGraphicsContext(GraphicsContext gc){
        this.graphicsContext = gc;
    }

    // Mendaftarkan IMouseClickSub agar dikirim event nanti
    public void RegisterMouse(IMouseClickSub click){
        mouseClickSubs.add(click);
    }
    
    // Getter
    public GameState getGameState(){
        return this.gameState;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public Player getOppositePlayer(){
        return this.oppositePlayer;
    }

    public List<IMouseClickSub> getMouseClickSubs(){
        return this.mouseClickSubs;
    }

    public GameDrawer getGameDrawer(){
        return this.gameDrawer;
    }

    public GraphicsContext getGraphicsContext(){
        return this.graphicsContext;
    }

    public GameManager(GraphicsContext gc){
        // Init gameDrawer
        graphicsContext = gc;
        gameDrawer = new GameDrawer();
        // Init CurrentPlayer dan oppositePlayer
        currentPlayer = new Player();
        oppositePlayer = new Player();

        // Generate kartu dan masukin ke player yang bersangkutan, dan simpan ke drawListnya GameDrawer
        // Init exit button dan masukin spritenya ke drawlistnya gamedrawer
        // Masukin 7 kartu ke tangan kedua player
        // Init state dengan draw phase
    }

    public void GameLoop(double durationSinceLastFrame){
        // Ini dipanggil oleh Avatar Duel pada Animation Timer pada handle()
	    // Reference: (1) - Cari bagian animation (bisa dipake buat yang bukan animasi juga)
    }


    public void SendMouseClickEvent(MouseEvent event){
        // Kirim event OnMouseClick ke subs dari MouseClickSubs saat klik
		// Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
		// Reference: (1) - Cari bagian mouse click
    }

    public void getClickedCardFromHand(int X, int Y){
        // Cek kartu apa yang di klik dari tangan player saat itu
		// Return null kalo gak ada kartu yang di klik
		// Caranya loop untuk setiap kartu di hand kalo Sprite.OverlapPoint
    }

    public ArenaClickInfo GetArenaClickInfo(int X, int Y){
        // Cek posisi klik dari mouse, apakah ada di belah atas atau bawah, dan ada di kolom ke berapa
        // Cek apakah ada kartu di posisi tersebut
        // Return ArenaClickInfo
    }
}