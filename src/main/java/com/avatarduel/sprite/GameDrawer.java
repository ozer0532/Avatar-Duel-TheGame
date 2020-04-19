package com.avatarduel.sprite;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.card.Card;
import com.avatarduel.card.Char;
import com.avatarduel.card.Skill;
import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.IMouseMoveSub;
import com.avatarduel.gamestate.ArenaClickInfo;
import com.avatarduel.gamestate.BattlePhase;
import com.avatarduel.gamestate.DrawPhase;
import com.avatarduel.gamestate.EndPhase;
import com.avatarduel.gamestate.GameState;
import com.avatarduel.gamestate.Main1Phase;
import com.avatarduel.model.Element;
import com.avatarduel.player.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameDrawer implements IMouseMoveSub {
    private CardSprite highligthtedCard = null;
    private Card hoveredCard = null;
    private List<Sprite> drawList = new ArrayList<>();
    private GameManager gm;

    /**
     * Pengatur sintesis citra untuk sprite
     * @param gm Game Manager dari game
     */
    public GameDrawer(GameManager gm) {
        this.gm = gm;
        gm.registerMouseMove(this);
    }

    /**
     * Menandai sprite untuk digambar oleh Game Drawer
     * @param s sprite untuk ditandai
     */
    public void addToDrawList (Sprite s) {
        drawList.add(s);
    }

    /**
     * Menghentikan penggambaran sprite oleh Game Drawer
     * @param s sprite yang penggambarannya ingin dihentikan
     */
    public void removeFromDrawList (Sprite s) {
        drawList.remove(s);
    }

    /**
     * Menandakan kartu yang diseleksi
     * @param card sprite kartu yang diseleksi
     */
    public void setHighlightedCard (CardSprite card) {
        highligthtedCard = card;
    }

    /**
     * Menggambarkan arena untuk salah satu pemain
     * @param p pemain yang digambarkan
     */
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

    /**
     * Menggambarkan kartu di tangan pemain
     * @param p pemain yang digambar
     * @param isCurrentPlayer jika true, kartu digambar terbuka
     * jika false, kartu digambar tertutup
     */
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

    /**
     * Menggambarkan nilai stat pemain
     * @param gc Graphics Context kanvas game
     * @param p pemain yang digambar
     */
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
    /**
     * Menggambar versi penuh kartu yang memiliki semua info tentang kartu
     * @param gc Graphics Context kanvas game
     * @param card Kartu yang versi penuhnya digambar
     */
    private void drawCardInfo (GraphicsContext gc, Card card) {
        if (card != null) {
            final double xPos = 1180;
            final double yPos = 360;
            final double scale = 0.63;

            CardSprite spr = card.drawCardDetail();
            spr.changePos(xPos, yPos, true);
            spr.changeScale(scale, scale, true);
            spr.update(gc, 0);
            spr.render(gc);
        }
    }

    /**
     * Menggambar tumpukan kartu yang dibuang
     * @param cards Daftar kartu yang dibuang
     */
    private void drawDiscardPile(List<CardSprite> cards) {
        final double xPos = 1280;
        final double yPos = 360;

    
        for (CardSprite card : cards) {
            card.changePos(xPos, yPos);
            card.changeScale(0.28, 0.28);
            card.changeRotation(0);
        }
    }

    /**
     * Menggambarkan highlight kartu yang dipilih
     * @param gc Graphics Context canvas game
     */
    private void drawHighlightedCard(GraphicsContext gc) {
        if (highligthtedCard != null) {
            gc.save();
            gc.setEffect(new Glow(0.5));
            highligthtedCard.render(gc);
            gc.restore();
        }
    }

    /**
     * Menggambarkan teks menang pada pemain
     * @param gc Graphics Context canvas game
     * @param isTopPlayer Jika true, teks menang akan digambarkan pada
     * pemain atas.
     */
    private void drawGameWin(GraphicsContext gc, boolean isTopPlayer) {
        final double xPos = 690;
        final double yPos;
        if (isTopPlayer) {
            yPos = 230;
        } else {
            yPos = 470;
        }
        gc.save();
        gc.setFont(new Font("Arial Bold", 50));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("WINNER WINNER\nCHICKEN DINNER", xPos, yPos);
        gc.restore();
    }

    /**
     * Menggambarkan fase game yang sedang berlangsung
     * @param gm Game Manager game
     */
    private void drawGameState(GameManager gm) {
        final double xPos = 120;
        final double yPos = 372;
        GraphicsContext gc = gm.getGraphicsContext();
        GameState gs = gm.getGameState();
        String text = "";

        if (gs instanceof DrawPhase) {
            text = "DRAW";
        } else if (gs instanceof Main1Phase) {
            text = "MAIN";
        } else if (gs instanceof BattlePhase) {
            text = "BATTLE";
        } else if (gs instanceof EndPhase) {
            text = "END";
        }

        gc.save();
        gc.setFont(new Font("Arial", 30));
        gc.fillText(text, xPos, yPos);
        gc.restore();
    }

    /**
     * Menjalankan urutan sintesis citra pada game
     * @param deltaTime Waktu sejak frame terakhir (untuk animasi)
     */
    public void drawGame (double deltaTime) {
        
        drawArena(gm.getCurrentPlayer());
        drawArena(gm.getOppositePlayer());
        drawHands(gm.getCurrentPlayer(), !(gm.getGameState() instanceof EndPhase));
        drawHands(gm.getOppositePlayer(), false);
        drawStats(gm.getGraphicsContext(), gm.getCurrentPlayer());
        drawStats(gm.getGraphicsContext(), gm.getOppositePlayer());
        drawDiscardPile(gm.getDiscardPile());
        for (Sprite spr : drawList) {
            spr.update(gm.getGraphicsContext(), deltaTime);
            spr.render(gm.getGraphicsContext());
        }
        if (!(gm.getGameState() instanceof EndPhase)) {
            drawHighlightedCard(gm.getGraphicsContext());
        }
        drawGameState(gm);
        drawCardInfo(gm.getGraphicsContext(), hoveredCard);
        if (gm.hasGameEnded()) {
            drawGameWin(gm.getGraphicsContext(), gm.topPlayerWon());
        }
    }

    @Override
    public void onMouseMove(MouseEvent e) {
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