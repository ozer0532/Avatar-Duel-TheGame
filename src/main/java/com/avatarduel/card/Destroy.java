// Destroy.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Destroy extends Card {
    public Destroy(String name, Element elmt, String desc, CardSprite sprite){
        super(name, elmt, desc, sprite);
    }

    public void OnCardPlayed(GameManager gm){
        // hapus musuh di sisi berlawanan
    }

    public boolean CanBePlayed(PlayerStats ps){
        if (ps.getRemainingPower(super.getElmt()) > 0){
            ps.usePower(super.getElmt(), 1);
            return true;
        }
        else {
            return false;
        }
    }
}