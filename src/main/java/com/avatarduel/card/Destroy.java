// Destroy.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Destroy extends Card {
    // CONSTRUCTOR
    public Destroy(String name, Element elmt, String desc, CardSprite sprite, int pn){
        super(name, elmt, desc, sprite, pn);
    }

    // METHODS
    public void OnCardPlayed(GameManager gm){
        // hapus musuh di sisi berlawanan
        //gm.getOppositePlayer().
    }

    public boolean CanBePlayed(PlayerStats ps){
        if (ps.getRemainingPower(this.Element) >= this.powerNeeded){
            //ps.usePower(super.getElmt(), 1); why?
            return true;
        }
        else {
            return false;
        }
    }
}