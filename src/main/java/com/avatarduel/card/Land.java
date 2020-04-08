// Land.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Land extends Card {
    // Constructor
    public Land(String name, Element elmt, String desc, CardSprite sprite){
        super(name, elmt, desc, sprite, 1);
    }

    public boolean isElementValid() {
        return ((this.element == Element.AIR) || (this.element == Element.WATER) || (this.element == Element.FIRE) || (this.element == Element.EARTH));
    }
    // Hapus diri dari arena, tambahin stats power dari player
    public void OnCardPlayed(GameManager gm, int idx) {
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
}
