package com.avatarduel.sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.avatarduel.card.Card;
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
        double characterYPos;
        double skillYPos;
        final double cardXPos = 358;
        final double cardXOffset = 95;

        if (p.getIsTopPlayer()) {
            characterYPos = 300;
            skillYPos = 180;
        } else {
            characterYPos = 420;
            skillYPos = 540;
        }

        for (int i = 0; i < 8; i++) {
            if (p.getPlayerArena().getCharCard(i) != null) { 
                p.getPlayerArena().getCharCard(i).getSprite().moveToPos(cardXPos + cardXOffset * i, characterYPos);
                p.getPlayerArena().getCharCard(i).getSprite().changeScale(.28, .28);
            }
        }
        for (int i = 0; i < 8; i++) {
            if (p.getPlayerArena().getSkillCard(i) != null) { 
                p.getPlayerArena().getSkillCard(i).getSprite().moveToPos(cardXPos + cardXOffset * i, skillYPos);
                p.getPlayerArena().getSkillCard(i).getSprite().changeScale(.28, .28);
            }
        }
    }

    // Menggambar kartu-kartu yang berada pada tangan pemain
    // p : Player yang kartunya akan digambar
    private void drawHands (Player p, boolean isCurrentPlayer) {
        double yPos;
        final double xCenterPos = 738;
        final double xOffset = 95;

        if (p.getIsTopPlayer()) {
            yPos = 60;
        } else {
            yPos = 660;
        }

        double size = p.getPlayerHands().size();
        
        for (int i = 0; i < size; i++) {
            p.getPlayerHands().get(i).getSprite().moveToPos(xCenterPos + xOffset * ((double)i - size/2), yPos);
            if (isCurrentPlayer) {
                p.getPlayerHands().get(i).getSprite().changeScale(.28, .28);
            } else {
                p.getPlayerHands().get(i).getSprite().changeScale(-0.28, 0.28);
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

    private void drawDiscardPile(List<CardSprite> cards) {
        final double xPos = 1380;
        final double yPos = 360;

        for (CardSprite card : cards) {
            card.moveToPos(xPos, yPos);
        }
    }

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
        drawDiscardPile(gm.getDiscardPile());
        for (Sprite spr : drawList) {
            spr.update(gm.getGraphicsContext(), deltaTime);
            spr.render(gm.getGraphicsContext());
        }
    }
}