package com.avatarduel.sprite;

import com.avatarduel.gamemanager.*;

import javafx.scene.input.MouseEvent;

/**
 * Kelas untuk mengatur tombol untuk pindah
 * ke ronde selanjutnya
 */
public class EndButton implements IMouseClickSub {
    private GameManager gm;
    private Sprite spr;

    /**
     * Membuat kontroler tombol akhiri ronde serta membangkitkan
     * gambarnya.
     * @param gm Game manager yang mengatur game
     */
    public EndButton (GameManager gm) {
        this.gm = gm;
        spr = new Sprite("com/avatarduel/generic/image/EndTurnButton.png");
        spr.setPivot(0.05, 0.5);
        spr.changePos(0, 360, true);
        gm.getGameDrawer().addToDrawList(spr);
    }

    public void OnMouseClick (MouseEvent mouse) {
        if (spr.isPointOverlap(mouse.getX(), mouse.getY())) {
            gm.getGameState().EndTurn();
        }
    }
}