package com.avatarduel.sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.avatarduel.card.Card;
import com.avatarduel.card.Char;
import com.avatarduel.card.Skill;
import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.IMouseMoveSub;
import com.avatarduel.gamestate.ArenaClickInfo;
import com.avatarduel.model.Element;
import com.avatarduel.player.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class GameDrawer implements IMouseMoveSub {
    private CardSprite highligthtedCard = null;
    private Card hoveredCard = null;
    private List<Sprite> drawList = new ArrayList<>();
    private GameManager gm;

    public GameDrawer(GameManager gm) {
        this.gm = gm;
        gm.RegisterMouseMove(this);
    }

    public void addToDrawList (Sprite s) {
        drawList.add(s);
    }

    public void removeFromDrawList (Sprite s) {
        drawList.remove(s);
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
            Char card = p.getPlayerArena().getCharCard(i);
            if (card != null) { 
                CardSprite sprite = card.getSprite();
                sprite.changePos(cardXPos + cardXOffset * i, characterYPos);
                sprite.changeScale(0.28, 0.28);
                if (card.getIsDefense()) {
                    sprite.changeRotation(90);
                } else {
                    sprite.changeRotation(0);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            Skill card = p.getPlayerArena().getSkillCard(i);
            if (card != null) { 
                CardSprite sprite = card.getSprite();
                sprite.changePos(cardXPos + cardXOffset * i, skillYPos);
                sprite.changeScale(0.28, 0.28);
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
            p.getPlayerHands().get(i).getSprite().changePos(xCenterPos + xOffset * ((double)i - size/2), yPos);
            if (isCurrentPlayer) {
                p.getPlayerHands().get(i).getSprite().changeScale(0.28, 0.28);
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
        final double xPos = 28;
        if (p.getIsTopPlayer()) {
            yPos = 167;
        } else {
            yPos = 463;
        }

        String healthPoints = p.getPlayerStats().getHealth()
                + "/80";

        String earthSkill = p.getPlayerStats().getRemainingPower(Element.EARTH)
                + "/"
                + p.getPlayerStats().getPower(Element.EARTH);

        String fireSkill = p.getPlayerStats().getRemainingPower(Element.FIRE)
                + "/"
                + p.getPlayerStats().getPower(Element.FIRE);

        String waterSkill = p.getPlayerStats().getRemainingPower(Element.WATER)
                + "/"
                + p.getPlayerStats().getPower(Element.WATER);

        String airSkill = p.getPlayerStats().getRemainingPower(Element.AIR)
                + "/"
                + p.getPlayerStats().getPower(Element.AIR);

        String energySkill = p.getPlayerStats().getRemainingPower(Element.ENERGY)
                + "/"
                + p.getPlayerStats().getPower(Element.ENERGY);

        gc.save();
        gc.setFont(new Font("Arial", 20));
        gc.fillText(healthPoints, xPos, 0 + yPos);
        gc.fillText(airSkill, xPos, 21 + yPos);
        gc.fillText(earthSkill, xPos, 42 + yPos);
        gc.fillText(waterSkill, xPos, 63 + yPos);
        gc.fillText(fireSkill, xPos, 84 + yPos);
        gc.fillText(energySkill, xPos, 105 + yPos);
        gc.restore();
    }

    // Menggambar versi penuh kartu yang memiliki semua info tentang kartu
    // gc : Penggambar
    // card : Kartu yang ingin ditampilkan
    // isTop : Posisi kartu
    private void drawCardInfo (GraphicsContext gc, Card card) {
        if (card != null) {
            final double xPos = 1180;
            final double yPos = 360;
            final double scale = 0.63;

            CardSprite spr = card.DrawCardDetail();
            spr.changePos(xPos, yPos, true);
            spr.changeScale(scale, scale, true);
            spr.update(gc, 0);
            spr.render(gc);
        }
    }

    private void drawDiscardPile(List<CardSprite> cards) {
        final double xPos = 1280;
        final double yPos = 360;

    
        for (CardSprite card : cards) {
            card.changePos(xPos, yPos);
            card.changeScale(0.28, 0.28);
            card.changeRotation(0);
        }
    }

    // Menjalankan urutan rendering pada game
    // gm : Tempat penyimpanan sentral informasi tentang game
    // deltaTime : Waktu sejak frame terakhir (untuk animasi)
    public void drawGame (double deltaTime) {
        
        drawArena(gm.getCurrentPlayer());
        drawArena(gm.getOppositePlayer());
        drawHands(gm.getCurrentPlayer(), true);
        drawHands(gm.getOppositePlayer(), false);
        drawStats(gm.getGraphicsContext(), gm.getCurrentPlayer());
        drawStats(gm.getGraphicsContext(), gm.getOppositePlayer());
        drawDiscardPile(gm.getDiscardPile());
        for (Sprite spr : drawList) {
            spr.update(gm.getGraphicsContext(), deltaTime);
            spr.render(gm.getGraphicsContext());
        }
        drawCardInfo(gm.getGraphicsContext(), hoveredCard);
    }

    public void OnMouseMove(MouseEvent e) {
        List<Card> ph = gm.getCurrentPlayer().getPlayerHands();
        // Cek kartu di tangan di hover
        for (int i=0; i < ph.size(); i++) {
            if (ph.get(i).getSprite().isPointOverlap(e.getX(), e.getY())) {
                hoveredCard = ph.get(i);
                return;
            }
        }
        // Cek kartu di arena di hover
        ArenaClickInfo info = gm.getArenaClickInfo(e.getX(), e.getY());
        if (info != null && info.getChosenCard() != null) {
            hoveredCard = info.getChosenCard();
            return;
        }
        hoveredCard = null;
    }
}