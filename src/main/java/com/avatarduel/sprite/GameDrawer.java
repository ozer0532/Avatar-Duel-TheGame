package com.avatarduel.sprite;

import java.util.*;

import com.avatarduel.player.*;

public class GameDrawer {
    private CardSprite highligthtedCard = null;
    private List<Sprite> drawList = new ArrayList<>();

    public void addToDrawList (Sprite s) {
        drawList.add(s);
    }

    private void drawArenaBottom (Player p) {
        //final double characterYPos = ...;
        //final double skillYPos = ...;
        //final double cardXPos = ...;
        //final double cardXOffset = ...;
        for (int i = 0; i < 8; i++) {
            //p.playerArena.character[i].MoveToPos(cardXPos + cardXOffset * i, characterYPos);
        }
        for (int i = 0; i < 8; i++) {
            //p.playerArena.skill[i].MoveToPos(cardXPos + cardXOffset * i, skillYPos);
        }
    }

    private void drawArenaTop (Player p) {
        //final double characterYPos = ...;
        //final double skillYPos = ...;
        //final double cardXPos = ...;
        //final double cardXOffset = ...;
        for (int i = 0; i < 8; i++) {
            //p.playerArena.character[i].MoveToPos(cardXPos + cardXOffset * i, characterYPos);
        }
        for (int i = 0; i < 8; i++) {
            //p.playerArena.skill[i].MoveToPos(cardXPos + cardXOffset * i, skillYPos);
        }
    }

    private void drawHandsBottom (Player p) {
        //final 
    }
}