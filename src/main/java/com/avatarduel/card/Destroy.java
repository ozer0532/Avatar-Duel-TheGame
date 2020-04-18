// Destroy.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Destroy extends Skill {
    // Constructor
    public Destroy(String name, Element elmt, String desc, String image, int pn){
        super(name, elmt, desc, image, pn);
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        // hapus musuh di sisi berlawanan
        PlayerArena temp;
        if (!isPlayedonEnemy) {
            temp = gm.getCurrentPlayer().getPlayerArena();
        } else {
            temp = gm.getOppositePlayer().getPlayerArena();
        }
        if (!isPlayedonEnemy) {
            temp = gm.getOppositePlayer().getPlayerArena();
        } else {
            temp = gm.getCurrentPlayer().getPlayerArena();
        }
        Char card = temp.getCharCard(idx);
        if (card != null) {
            gm.getOppositePlayer().getPlayerArena().removeCharCard(idx);
            gm.addToDiscardPile(card);
            Skill skill = temp.getSkillCard(idx);
            if (skill != null) {
                gm.getOppositePlayer().getPlayerArena().removeSkillCard(idx);
                gm.addToDiscardPile(skill);
                
            }
        }
        gm.getCurrentPlayer().getPlayerHands().remove(this);
        gm.addToDiscardPile(this);
        gm.getCurrentPlayer().getPlayerStats().usePower(element, powerNeeded);
        
    }

    public boolean CanBePlayed(Player p){
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

        attr="DESTROY / P: " + this.powerNeeded;
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
        effect="Destroy";
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