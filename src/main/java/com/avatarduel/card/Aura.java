// Aura.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Aura extends Card {
    private int atk;
    private int def;

    // CONSTRUCTOR
    public Aura(String name, Element elmt, String desc, CardSprite sprite,
    int pn,int atk, int def){
        super(name, elmt, desc, sprite, pn);
        this.atk = atk;
        this.def = def;
    }

    // SETTER
    public void setAttack(int atk){
        this.atk = atk;
    }

    public void setDefense(int def){
        this.def = def;
    }

    // GETTER
    public int getAttack(){
        return this.atk;
    }

    public int getDefense(){
        return this.def;
    }

    // METHODS
    public void OnCardPlayed(GameManager gm, int idx){
        PlayerArena temp = gm.getCurrentPlayer().getPlayerArena();
        temp.addSkillCard(idx,this);
        gm.getCurrentPlayer().setPlayerArena(temp);
    }

    public boolean CanBePlayed(PlayerStats ps){
        if (ps.getRemainingPower(this.Element) >= this.powerNeeded){
            //ps.usePower(super.getElmt(), 1); 
            return true;
        }
        else {
            return false;
        }
    }
}