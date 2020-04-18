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
        PlayerArena temp;
        if (!isPlayedonEnemy) {
            temp = gm.getCurrentPlayer().getPlayerArena();
        } else {
            temp = gm.getOppositePlayer().getPlayerArena();
        }
        if (temp.getCharCard(idx) != null) {
            temp.addSkillCard(idx,this);
            gm.getCurrentPlayer().getPlayerHands().remove(this);
            gm.getCurrentPlayer().getPlayerStats().usePower(element, powerNeeded);
        }
    }

    public boolean CanBePlayed(Player p){
        PlayerStats ps = p.getPlayerStats();
        PlayerArena pa = p.getPlayerArena();
        if (ps.getRemainingPower(this.element) >= this.powerNeeded) {
            if (pa.charCardCount() + pa.skillCardCount() < 6) {
                return true;
            }
        }
        System.out.println(pa.charCardCount());
        System.out.println(pa.skillCardCount());
        return false;
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
        cs.SetImagePos(36, 69);
        
        if (this.atk>=0) {
            attack="+"+this.atk;
        }
        else {
            attack="" + this.atk;
        }
        
        if (this.def>=0) {
            defense="+"+this.def;
        }
        else {
            defense=this.def+"";
        }

        attr="ATK/"+attack+" | "+"DEF/"+defense+" | "+"POW/"+this.powerNeeded;
        type="[ Skill ]";
        
        cs.InsertText(this.name,33,35);   
        cs.InsertText(type,20,353);
        cs.InsertText(this.description,25,380);
        cs.InsertText(attr,25,545);

        return cs;
    }
}