// Land.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Character extends Card {
    private int atk;
    private int def;
    private boolean isDefense;
    private boolean isPoweredUp;

    public Character(String name, Element elmt, String desc, 
    CardSprite spr ,int atk, int def){
        super(name, elmt, desc, spr);
        this.atk = atk;
        this.def = def;
        this.isDefense = false;
        this.isPoweredUp = false;
    }

    public int getAttack(){
        return this.atk;
    }

    public int getAttack(Aura ar){
        return this.atk + ar.getAttack();
    }
    
    public int getDefense(){
        return this.def;
    }   

    public int getDefense(Aura ar){
        return this.def + ar.getDefense();
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