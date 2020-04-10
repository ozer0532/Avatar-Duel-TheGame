package com.avatarduel.sprite;

import com.avatarduel.gameManager.*;

import javafx.scene.input.MouseEvent;

public class EndButton implements IMouseClickSub {
    private GameManager gm;
    private Sprite spr;

    public EndButton (GameManager gm) {
        this.gm = gm;
        spr = new Sprite("Gambar Tombolnya", 200, 300);
        gm.getGameDrawer().addToDrawList(spr);
    }

    public void OnMouseClick (MouseEvent mouse) {
        if (spr.isPointOverlap(mouse.getX(), mouse.getY())) {
            //gm.getGameState().EndTurn();
        }
    }
}