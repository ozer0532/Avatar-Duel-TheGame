package com.avatarduel.sprite;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.model.Element;
import com.avatarduel.player.Player;

import javafx.scene.canvas.GraphicsContext;

public class GameDrawer {
    private CardSprite highligthtedCard = null;
    private List<Sprite> drawList = new ArrayList<>();

    public void addToDrawList (Sprite s) {
        drawList.add(s);
    }

    public void setHighlightedCard (CardSprite card) {
        highligthtedCard = card;
    }

    // Menggambar kartu-kartu yang berada pada arena
    // p : Player yang kartunya akan digambar
    private void drawArena (Player p) {
        //double characterYPos;
        //double skillYPos;
        //final double cardXPos = ...;
        //final double cardXOffset = ...;

        // if (p.isTopPlayer) {
        //     characterYPos = ...;
        //     skillYPos = ...;
        // } else {
        //     characterYPos = ...;
        //     skillYPos = ...;
        // }

        for (int i = 0; i < 8; i++) {
            //if (p.getPlayerArena().character[i] != null) { 
            //    p.playerArena.character[i].MoveToPos(cardXPos + cardXOffset * i, characterYPos);
            //}
        }
        for (int i = 0; i < 8; i++) {
            //if (p.getPlayerArena().character[i] != null) { 
            //    p.playerArena.skill[i].MoveToPos(cardXPos + cardXOffset * i, skillYPos);
            //}
        }
    }

    // Menggambar kartu-kartu yang berada pada tangan pemain
    // p : Player yang kartunya akan digambar
    private void drawHands (Player p, boolean isCurrentPlayer) {
        //double yPos;
        //final double xCenterPos = ...;
        //final double xOffset = ...;

        // if (p.isTopPlayer) {
        //     yPos = ...;
        // } else {
        //     yPos = ...;
        // }
        
        for (int i = 0; i < 8; i++) {
            //p.getPlayerHands()[i].MoveToPos(cardXPos + cardXOffset * i, characterYPos);
            if (isCurrentPlayer) {
                //p.getPlayerHands()[i].ChangeScale(-1, 1);
            } else {
                //p.getPlayerHands()[i].ChangeScale(1, 1);
            }
        }
    }

    // Menggambar stats pemain pada layar
    // gc : Penggambar
    // p : Player yang kartunya akan digambar
    private void drawStats (GraphicsContext gc, Player p) {
        final double yPos;
        // if (p.isTopPlayer) {
        //     yPos = ...;
        // } else {
        //     yPos = ...;
        // }

        // gc.drawImage("Backdrop", x, y + yPos, w, h));
        // gc.drawImage("EarthLogo", x, y + yPos, w, h));
        // gc.drawImage("FireLogo", x, y + yPos, w, h));
        // gc.drawImage("WaterLogo", x, y + yPos, w, h));
        // gc.drawImage("AirLogo", x, y + yPos, w, h));

        String earthSkill = "EARTH: "
                + p.getPlayerStats().getRemainingPower(Element.EARTH)
                + "/"
                + p.getPlayerStats().getPower(Element.EARTH);

        String fireSkill = "FIRE: "
                + p.getPlayerStats().getRemainingPower(Element.FIRE)
                + "/"
                + p.getPlayerStats().getPower(Element.FIRE);

        String waterSkill = "WATER: "
                + p.getPlayerStats().getRemainingPower(Element.WATER)
                + "/"
                + p.getPlayerStats().getPower(Element.WATER);

        String airSkill = "AIR: "
                + p.getPlayerStats().getRemainingPower(Element.AIR)
                + "/"
                + p.getPlayerStats().getPower(Element.AIR);

        // gc.save();
        // gc.setFont(new Font("Arial", 30));
        // gc.fillText(earthSkill, x, y + yPos);
        // gc.fillText(fireSkill, x, y + yPos);
        // gc.fillText(waterSkill, x, y + yPos);
        // gc.fillText(airSkill, x, y + yPos);
    }

    // Menggambar versi penuh kartu yang memiliki semua info tentang kartu
    // gc : Penggambar
    // card : Kartu yang ingin ditampilkan
    // isTop : Posisi kartu
    // private void drawCardInfo (GraphicsContext gc, Card card, bool isTop) {
    //     double yPos = (isTop) ? yAtas : yBawah
    //     -------------------------
    //     CardSprite spr = new CardSprite("Gambar template kartu", "Backface kartu", card.image, x, yPos, w, h);
    //     spr.InsertText(card...., x, y, w, h);
    //     ... Add for all card attributes ...
    //     -------------------------
    //     CardSprite spr = card.DrawCardDetail();
    //     spr.jumpToPos(...);
    //
    //     spr.render(gc);
    // }

    // Menjalankan urutan rendering pada game
    // gm : Tempat penyimpanan sentral informasi tentang game
    // deltaTime : Waktu sejak frame terakhir (untuk animasi)
    public void drawGame (GameManager gm, double deltaTime) {
        drawArena(gm.getCurrentPlayer());
        drawArena(gm.getOppositePlayer());
        drawHands(gm.getCurrentPlayer(), true);
        drawHands(gm.getOppositePlayer(), false);
        drawStats(gm.getGraphicsContext(), gm.getCurrentPlayer());
        drawStats(gm.getGraphicsContext(), gm.getOppositePlayer());
        // drawCardInfo(gm.gc, ..., ...);
    }
}