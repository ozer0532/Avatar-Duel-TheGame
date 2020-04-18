// Destroy.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

/**
 * Destroy merupakan class yang inheritance dari skill. Destroy bertanggung jawab dalam representasi kartu skill
 * berjenis Destroy dalam permainan Avatar Duel.
 */
public class Destroy extends Skill {
    // Constructor
    /**
     * Membuat sebuah Destroy baru dengan masukan name, elmt, desc, image, dan pn
     * @param name nama dari skill Destroy
     * @param elmt element dari skill Destroy
     * @param desc deskripsi dari skill Destroy
     * @param image path image dari skill Destroy
     * @param pn jumlah power yang dibutuhkan
     */
    public Destroy(String name, Element elmt, String desc, String image, int pn){
        super(name, elmt, desc, image, pn);
    }

    // Method Implementation
    /**
     * Memainkan kartu Destroy dalam permainan, menghapus musuh di sisi berlawanan
     * @param gm GameManager sebagai pengatur permainan
     * @param idx index kartu dalam arena
     * @param isPlayedonEnemy true apabila dimainkan ke musuh
     */
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

    /**
     * Mengembalikan true apabila kartu bisa dimainkan dengan kondisi power mencukupi
     * @param p player yang kartu Destroy nya mau dimainkan
     * @return true apabile kartu Destroy bisa dimainkan
     */
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

    /**
     * Mengembalikan informasi sederhana dari sebuah kartu Destroy
     * @param x posisi x dari mouse
     * @param y posisi y dari mouse
     * @param isFlipped keadaan kartu terbalik atau tidak
     * @return sebuah sprite berisi informasi detail dari kartu Destroy
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

        attr="DESTROY / P: " + this.powerNeeded;
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(attr,25,354,"Arial Bold",32);
        return cs;
    }

    /**
     * Mengembalikan informasi detail dari sebuah kartu Destroy
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