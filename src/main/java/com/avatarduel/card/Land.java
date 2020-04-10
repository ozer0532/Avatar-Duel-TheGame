// Land.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Land extends Card {
    // Constructor
    public Land(String name, Element elmt, String desc){
        super(name, elmt, desc, 1);
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
            gm.getCurrentPlayer().setPlayerStats(temp);
        }
    }

    // Return true kalo land belom dimainin saat itu
    public CardSprite DrawCardSimple(float x, float y, boolean isFlipped) {
        // kamus lokal
        CardSprite cs;
        String imagePath, front, back, attr, elmt;

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
        else if (this.element=Element.WATER) {
            elmt="Water";
        }
        imagePath="../../../../resources/com/avatarduel/card/image/land/"+this.name+".png";
        front="../../../../resources/com/avatarduel/generic/image/"+Elmt+"SmallCard.png";
        back="../../../../resources/com/avatarduel/generic/image/BackSmallCard.png";
        attr="Land";
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(attr,0,0);
        return cs;
    }
    public CardSprite DrawCardDetail() {
        // kamus lokal
        CardSprite cs;
        String imagePath, front, back, attr, elmt, desc, type;

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
        else if (this.element=Element.WATER) {
            elmt="Water";
        }
        imagePath="../../../../resources/com/avatarduel/card/image/land/"+this.name+".png";
        front="../../../../resources/com/avatarduel/generic/image/"+Elmt+"LargeCard.png";
        back="../../../../resources/com/avatarduel/generic/image/BackSmallCard.png";
        
        cs = new CardSprite(front, back, imagePath);

        type="[ Land ]";     
        cs.InsertText(this.name,0,0);   
        cs.InsertText(type,0,0);
        cs.InsertText(this.description,0,0);

        return cs;
    }
}
