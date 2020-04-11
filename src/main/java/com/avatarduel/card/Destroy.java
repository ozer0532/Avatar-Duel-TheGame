// Destroy.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Destroy extends Skill {
    // Constructor
    public Destroy(String name, Element elmt, String desc, int pn){
        super(name, elmt, desc, pn);
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        // hapus musuh di sisi berlawanan
        if (isPlayedonEnemy) {
            gm.getOppositePlayer().getPlayerArena().removeCharCard(idx);
        }
        else {
            gm.getCurrentPlayer().getPlayerArena().removeSkillCard(idx);
        }
        
    }

    public boolean CanBePlayed(PlayerStats ps){
        if (ps.getRemainingPower(super.getElmt()) >= this.powerNeeded){
            return true;
        }
        else {
            return false;
        }
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
        imagePath="../../../../resources/com/avatarduel/card/image/skill/"+this.name+".png";
        front="../../../../resources/com/avatarduel/generic/image/"+elmt+"SmallCard.png";
        back="../../../../resources/com/avatarduel/generic/image/BackSmallCard.png";
        attr="POW/"+this.powerNeeded;
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(attr,0,0);
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
        imagePath="../../../../resources/com/avatarduel/card/image/skill/"+this.name+".png";
        front="../../../../resources/com/avatarduel/generic/image/"+elmt+"LargeCard.png";
        back="../../../../resources/com/avatarduel/generic/image/BackSmallCard.png";
        
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