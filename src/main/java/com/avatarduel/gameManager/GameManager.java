// GameManager.java
package com.avatarduel.gameManager;

import com.avatarduel.player.*;
import com.avatarduel.sprite.*;
import com.avatarduel.gameState.*;
import java.util.List;

public class GameManager {
    private GameState gameState;
    private Player currentPlayer;
    private Player oppositePlayer;
    private List<IMouseClickSub> mouseClickSubs;
    private GameDrawer gameDrawer;

    public GameManager(){
        // Init gameDrawer
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

    public void RegisterMouse(IMouseClickSub click){
        // Mendaftarkan IMouseClickSub agar dikirim event nanti
        mouseClickSubs.add(click);
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