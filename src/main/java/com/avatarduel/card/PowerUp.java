// PowerUp.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

/**
 * PowerUp merupakan class yang inheritance dari skill. PowerUp bertanggung jawab dalam representasi kartu skill
 * berjenis PowerUp dalam permainan Avatar Duel.
 */
public class PowerUp extends Skill {
    // Constructor
    /**
     * Membuat sebuah PowerUp baru dengan masukan name, elmt, desc, image, dan pn
     * @param name nama dari skill PowerUp
     * @param elmt element dari skill PowerUp
     * @param desc deskripsi dari skill PowerUp
     * @param image path image dari skill PowerUp
     * @param pn jumlah power yang dibutuhkan
     */
    public PowerUp(String name, Element elmt, String desc, String image, int pn){
        super(name, elmt, desc, image, pn);
    }

    // Method Implementation
    /**
     * Memainkan kartu PowerUp dalam permainan
     * @param gm GameManager sebagai pengatur permainan
     * @param idx index kartu dalam arena
     * @param isPlayedonEnemy true apabila dimainkan ke musuh
     */
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        PlayerArena temp=gm.getCurrentPlayer().getPlayerArena();
        if (temp.getCharCard(idx) != null) {
            temp.addSkillCard(idx,this);
            gm.getCurrentPlayer().getPlayerHands().remove(this);
            gm.getCurrentPlayer().getPlayerStats().usePower(element, powerNeeded);
        }
    }

    /**
     * Mengembalikan true apabila kartu bisa dimainkan dengan kondisi power mencukupi
     * @param p player yang kartu powerUp nya mau dimainkan
     * @return true apabile kartu powerUp bisa dimainkan
     */
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

    /**
     * Mengembalikan informasi sederhana dari sebuah kartu PowerUp
     * @param x posisi x dari mouse
     * @param y posisi y dari mouse
     * @param isFlipped keadaan kartu terbalik atau tidak
     * @return sebuah sprite berisi informasi detail dari kartu PowerUp
     */
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

    /**
     * Mengembalikan informasi detail dari sebuah kartu PowerUp
     * @return sebuah sprite berisi informasi detail dari kartu
     */
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

        attr="POW/"+this.powerNeeded;
        type="[ Skill ]"; 

        cs.InsertText(this.name,33,35);   
        cs.InsertText(type,20,353);
        cs.InsertText(this.description,25,380);
        cs.InsertText(attr,25,545);

        return cs;
    }
}