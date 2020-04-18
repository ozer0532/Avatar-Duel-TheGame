package com.avatarduel.sprite;

import com.avatarduel.gameManager.*;

import javafx.scene.input.MouseEvent;

public class EndButton implements IMouseClickSub {
    private GameManager gm;
    private Sprite spr;

    public EndButton (GameManager gm) {
        this.gm = gm;
        spr = new Sprite("com/avatarduel/generic/image/EndTurnButton.png");
        spr.setPivot(0.05, 0.5);
        spr.changePos(0, 360, true);
        gm.getGameDrawer().addToDrawList(spr);
    }

    public void OnMouseClick (MouseEvent mouse) {
        if (spr.isPointOverlap(mouse.getX(), mouse.getY())) {
            System.out.println("TES");
            gm.getGameState().EndTurn();
        }
    }
}