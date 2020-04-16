// Aura.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Aura extends Skill {
    // Atribut dari Aura
    private int atk;
    private int def;

    // Constructor
    public Aura(String name, Element elmt, String desc, String image,
    int pn,int atk, int def){
        super(name, elmt, desc, image, pn);
        this.atk = atk;
        this.def = def;
        this.sprite = this.DrawCardSimple(0, 0, true);
    }

    // Setter for Aura
    public void setAttack(int atk){
        this.atk = atk;
    }

    public void setDefense(int def){
        this.def = def;
    }

    // Getter for Aura
    public int getAttack(){
        return this.atk;
    }

    public int getDefense(){
        return this.def;
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        PlayerArena temp = gm.getCurrentPlayer().getPlayerArena();
        temp.addSkillCard(idx,this);
        gm.getCurrentPlayer().setPlayerArena(temp);
        gm.getCurrentPlayer().getPlayerHands().remove(this);
        gm.getCurrentPlayer().getPlayerStats().usePower(element, powerNeeded);
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
        String imagePath, front, back, attr, elmt, attack, defense;
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

        if (this.atk >= 0) {
            attack = "+" + this.atk;
        } else {
            attack = "" + this.atk;
        }

        if (this.def >= 0) {
            defense = "+" + this.def;
        } else {
            defense = "" + this.def;
        }

        attr="A: "+attack+" / "+"D: "+defense+" / "+"P: "+this.powerNeeded;
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(attr,25,354,"Arial Bold",32);

        return cs;
    }
    public CardSprite DrawCardDetail() {
        // kamus lokal
        CardSprite cs;
        String imagePath, front, back, attr, elmt, desc, type, effect, attack, defense, pow;
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
        
        if (this.atk>=0) {
            attack="+"+this.atk+" ATK";
        }
        else {
            attack=this.atk+" ATK";
        }
        
        if (this.def>=0) {
            defense="+"+this.def+" DEF";
        }
        else {
            defense=this.def+" DEF";
        }

        effect="Aura";
        attr=attack+" "+defense;
        pow="POW/"+this.powerNeeded;
        type="[ Skill ]"; 

        cs.InsertText(this.name,0,0); 
        cs.InsertText(effect,0,0);  
        cs.InsertText(type,0,0);
        cs.InsertText(this.description,0,0);
        cs.InsertText(attr,0,0);
        cs.InsertText(pow,0,0);

        return cs;
    }
}