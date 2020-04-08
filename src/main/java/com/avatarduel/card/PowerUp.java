// PowerUp.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class PowerUp extends Skill {
    // Constructor
    public PowerUp(String name, Element elmt, String desc, CardSprite sprite, int pn){
        super(name, elmt, desc, sprite, pn);
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx){
        PlayerArena temp=gm.getCurrentPlayer().getPlayerArena();
        temp.addSkillCard(idx,this);
        gm.getCurrentPlayer().setPlayerArena(temp);
    }

    public boolean CanBePlayed(PlayerStats ps){
        // return true kalo power mencukupi
        if (ps.getRemainingPower(super.getElmt()) >= this.powerNeeded){
            return true;
        }
        else {
            return false;
        }
    }
}