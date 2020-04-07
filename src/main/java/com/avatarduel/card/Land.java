// Land.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Land extends Card {
    // Constructor
    public Land(String name, Element elmt, String desc, CardSprite sprite){
        super(name, elmt, desc, sprite);
    }

    // Hapus diri dari arena, tambahin stats power dari player
    public void OnCardPlayed(GameManager gm){
        if ((super.getElmt() == Element.AIR) || (super.getElmt() == Element.WATER) || (super.getElmt() == Element.FIRE) || (super.getElmt() == Element.EARTH)){
            PlayerStats temp = gm.getCurrentPlayer().getPlayerStats();
            temp.incrementPower(super.getElmt());
            gm.getCurrentPlayer().setPlayerStats(temp);
        }
    }

    // Return true kalo land belom dimainin saat itu
    public boolean CanBePlayed(PlayerStats ps){
        if (!(ps.getPlayedLandThisRound())){
            return true;
        }
        else {
            return false;
        }
    }
}