// PowerUp.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class PowerUp extends Card {
    public PowerUp(String name, Element elmt, String desc, CardSprite sprite){
        super(name, elmt, desc, sprite);
    }

    public void OnCardPlayed(GameManager gm){
        // do nothing
    }

    public boolean CanBePlayed(PlayerStats ps){
        // return true kalo power mencukupi
    }
}