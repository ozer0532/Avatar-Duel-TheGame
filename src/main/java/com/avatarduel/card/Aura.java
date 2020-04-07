// Aura.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Aura extends Card {
    private int atk;
    private int def;

    public Aura(String name, Element elmt, String desc, CardSprite sprite
    ,int atk, int def){
        super(name, elmt, desc, sprite);
        this.atk = atk;
        this.def = def;
    }

    public void setAttack(int atk){
        this.atk = atk;
    }

    public void setDefense(int def){
        this.def = def;
    }

    public int getAttack(){
        return this.atk;
    }

    public int getDefense(){
        return this.def;
    }

    public void OnCardPlayed(GameManager gm){
        // do nothing
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