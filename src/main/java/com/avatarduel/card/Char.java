// Land.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;
import com.avatarduel.sprite.CardText;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Char extends Card {
    private int atk;
    private int def;
    private boolean isDefense;
    private boolean isPoweredUp;

    public Char(String name, Element elmt, String desc, String image,
         int pow, int atk, int def){
        super(name, elmt, desc, image, pow);
        this.atk = atk;
        this.def = def;
        this.sprite = this.DrawCardSimple(0, 0, true);
        this.isDefense = false;
        this.isPoweredUp = false;
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

    public boolean getIsPoweredUp() {
        return this.isPoweredUp;
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

    public void setIsPoweredUp(boolean b) {
        this.isPoweredUp=b;
    }


    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        PlayerArena temp = gm.getCurrentPlayer().getPlayerArena();
        temp.addCharacterCard(idx,this);
        gm.getCurrentPlayer().setPlayerArena(temp);
        gm.getCurrentPlayer().getPlayerHands().remove(this);
        gm.getCurrentPlayer().getPlayerStats().usePower(element, powerNeeded);
    }

    public boolean CanBePlayed(PlayerStats ps){
        if (ps.getRemainingPower(this.element) >= this.powerNeeded) {
            //ps.usePower(super.getElmt(), 1);
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

        attr="ATK/"+this.atk+" | "+"DEF/"+this.def+" | "+"POW/"+this.powerNeeded;
        type="[ Character ]";     
        cs.InsertText(this.name,0,0);   
        cs.InsertText(type,0,0);
        cs.InsertText(this.description,0,0);
        cs.InsertText(attr,0,0);

        return cs;
    }
}