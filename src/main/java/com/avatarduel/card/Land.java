// Land.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Land extends Card {
    // Constructor
    public Land(String name, Element elmt, String desc, String image){
        super(name, elmt, desc, image, 0);
    }

    // Public Method
    public boolean isElementValid() {
        return ((this.element == Element.AIR) || (this.element == Element.WATER) || (this.element == Element.FIRE) || (this.element == Element.EARTH));
    }

    // Method Implementation
    // Hapus diri dari arena, tambahin stats power dari player
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy) {
        if (this.isElementValid()) {
            PlayerStats temp = gm.getCurrentPlayer().getPlayerStats();
            temp.incrementPower(this.element);
            temp.setPlayedLandThisRound(true);
            gm.getCurrentPlayer().getPlayerHands().remove(this);
            gm.addToDiscardPile(this);
        }
    }

    public boolean CanBePlayed(Player p){
        PlayerStats ps = p.getPlayerStats();
        if (!ps.getPlayedLandThisRound()){
            ps.setPlayedLandThisRound(true);
            return true;
        }
        else {
            return false;
        }
    }

    // Return true kalo land belom dimainin saat itu
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
        attr="LAND";
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(attr,25,354,"Arial Bold",32);
        return cs;
    }
    public CardSprite DrawCardDetail() {
        // kamus lokal
        CardSprite cs;
        String imagePath, front, back, attr, elmt, desc, type;
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
        cs.SetImagePos(36, 69);

        type="[ Land ]";
        attr="POW/+1";
        cs.InsertText(this.name,33,35);   
        cs.InsertText(type,20,353);
        cs.InsertText(this.description,25,380);
        cs.InsertText(attr,25,545);

        return cs;
    }
}
