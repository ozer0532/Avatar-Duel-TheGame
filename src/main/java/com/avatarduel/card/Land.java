// Land.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Land extends Card {
    // Constructor
    public Land(String name, Element elmt, String desc){
        super(name, elmt, desc, 1);
    }

    // Public Method
    public boolean isElementValid() {
        return ((this.element == Element.AIR) || (this.element == Element.WATER) || (this.element == Element.FIRE) || (this.element == Element.EARTH));
    }

    // Method Implementation
    // Hapus diri dari arena, tambahin stats power dari player
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy) {
        if (this.isElementValid()) {
            PlayerStats temp = gm.getCurrentPlayer().getPlayerStats();
            temp.incrementPower(this.element);
            gm.getCurrentPlayer().setPlayerStats(temp);
        }
    }

    // Return true kalo land belom dimainin saat itu
    public boolean CanBePlayed(PlayerStats ps){
        if (!(ps.getPlayedLandThisRound()) && (ps.getRemainingPower(super.getElmt()) > 0)){
            return true;
        }
        else {
            return false;
        }
    }

    public CardSprite DrawCardSimple(float x, float y, boolean isFlipped) {
        CardSprite cs;
        String imagePath, front, back, stext;

        //imagePath="../../../../resources/com/avatarduel/card/image/character/"+this.name;
        //front="";
        //back="";
        stext="ATK/"+this.atk+" | "+"DEF/"+this.def+" | "+"POW/"+this.powerNeeded;
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(stext,0,0);
        return cs;
    }
    public CardSprite DrawCardDetail() {
        return cs;
    }
}
