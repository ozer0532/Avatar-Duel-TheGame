// PowerUp.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class PowerUp extends Skill {
    // Constructor
    public PowerUp(String name, Element elmt, String desc, String image, int pn){
        super(name, elmt, desc, image, pn);
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        PlayerArena temp=gm.getCurrentPlayer().getPlayerArena();
        if (temp.getCharCard(idx) != null) {
            temp.addSkillCard(idx,this);
            gm.getCurrentPlayer().setPlayerArena(temp);
            gm.getCurrentPlayer().getPlayerHands().remove(this);
            gm.getCurrentPlayer().getPlayerStats().usePower(element, powerNeeded);
        }
    }

    public boolean CanBePlayed(Player p){
        // return true kalo power mencukupi
        PlayerStats ps = p.getPlayerStats();
        PlayerArena pa = p.getPlayerArena();
        if (ps.getRemainingPower(this.element) >= this.powerNeeded) {
            if (pa.charCardCount() + pa.skillCardCount() < 6) {
                return true;
            }
        }
        return false;
    }

    public CardSprite DrawCardSimple(float x, float y, boolean isFlipped) {
        // kamus lokal
        CardSprite cs;
        String imagePath, front, back, attr, elmt;
        elmt = "";
        if (this.element==Element.AIR) {
            elmt="Air";
        }
        else if (this.element==Element.EARTH) {
            elmt="Earth";
        }
        else if (this.element==Element.ENERGY) {
            elmt="Energy";
        }
        else if (this.element==Element.FIRE) {
            elmt="Fire";
        }
        else if (this.element==Element.WATER) {
            elmt="Water";
        }

        imagePath=image;
        front="com/avatarduel/generic/image/"+elmt+"SmallCard.png";
        back="com/avatarduel/generic/image/BackSmallCard.png";

        attr="POWER UP / P: " + this.powerNeeded;
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(attr,25,354,"Arial Bold",32);
        return cs;
    }
    public CardSprite DrawCardDetail() {
        // kamus lokal
        CardSprite cs;
        String imagePath, front, back, attr, elmt, desc, type, effect;
        elmt = "";
        if (this.element==Element.AIR) {
            elmt="Air";
        }
        else if (this.element==Element.EARTH) {
            elmt="Earth";
        }
        else if (this.element==Element.ENERGY) {
            elmt="Energy";
        }
        else if (this.element==Element.FIRE) {
            elmt="Fire";
        }
        else if (this.element==Element.WATER) {
            elmt="Water";
        }

        imagePath=image;
        front="com/avatarduel/generic/image/"+elmt+"LargeCard.png";
        back="com/avatarduel/generic/image/BackSmallCard.png";

        cs = new CardSprite(front, back, imagePath);

        effect="Power Up";
        attr="POW/"+this.powerNeeded;
        type="[ Skill ]"; 

        cs.InsertText(this.name,0,0); 
        cs.InsertText(effect,0,0);  
        cs.InsertText(type,0,0);
        cs.InsertText(this.description,0,0);
        cs.InsertText(attr,0,0);

        return cs;
    }
}