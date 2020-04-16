package com.avatarduel.sprite;

import com.avatarduel.gamemanager.*;

import javafx.scene.input.MouseEvent;

public class EndButton implements IMouseClickSub {
    private GameManager gm;
    private Sprite spr;

    public EndButton (GameManager gm) {
        this.gm = gm;
        spr = new Sprite("com/avatarduel/generic/image/EndTurnButton.png", 200, 300);
        spr.setPivot(0, 0.5);
        spr.jumpToPos(0, 360);
        gm.getGameDrawer().addToDrawList(spr);
    }

    public void OnMouseClick (MouseEvent mouse) {
        if (spr.isPointOverlap(mouse.getX(), mouse.getY())) {
            System.out.println("TES");
            gm.getGameState().EndTurn();
        }
    }
}