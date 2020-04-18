// Land.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;
import com.avatarduel.sprite.CardText;

import javafx.scene.image.Image;

import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Char extends Card {
    private int atk;
    private int def;
    private boolean isDefense;

    public Char(String name, Element elmt, String desc, String image,
         int pow, int atk, int def){
        super(name, elmt, desc, image, pow);
        this.atk = atk;
        this.def = def;
        this.sprite = this.DrawCardSimple(0, 0, true);
        this.isDefense = false;
    }

    // Getter for Character
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
        if (this.isDefense) {
            return this.def + ar.getDefense();
        }
        else {
            return this.getAttack(ar);
        }
    }

    public boolean getIsDefense() {
        return this.isDefense;
    }


    // Setter for Character;
    public void setAttack(int atk) {
        this.atk=atk;
    }

    public void setDefense(int def) {
        this.def=def;
    }

    public void setIsDefense(boolean isdef) {
        this.isDefense=isdef;
    }


    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        PlayerArena temp = gm.getCurrentPlayer().getPlayerArena();
        temp.addCharacterCard(idx,this);
        gm.getCurrentPlayer().getPlayerHands().remove(this);
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
        attr="A: "+this.atk+" / "+"D: "+this.def+" / "+"P: "+this.powerNeeded;
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

        attr="ATK/"+this.atk+" | "+"DEF/"+this.def+" | "+"POW/"+this.powerNeeded;
        type="[ Character ]";
        cs.InsertText(this.name,33,35);   
        cs.InsertText(type,20,353);
        cs.InsertText(this.description,25,380);
        cs.InsertText(attr,25,545);

        return cs;
    }
}