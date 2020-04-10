// PowerUp.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class PowerUp extends Skill {
    // Constructor
    public PowerUp(String name, Element elmt, String desc, int pn){
        super(name, elmt, desc, pn);
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
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