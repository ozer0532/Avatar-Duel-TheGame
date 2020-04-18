// Land.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

/**
 * Land adalah turunan dari Card. Land adalah class yang merepresentasikan kartu bertipe Land pada
 * permainan Avatar Duel.
 * 
 * Kelas ini mengimplementasikan semua abstract method yang dimiliki oleh kelas Card.
 */
public class Land extends Card {

    /**
     * Membuat Land Card baru dengan informasi name, elmt, desc, dan image
     * @param name nama dari kartu land
     * @param elmt jenis elemen dari kartu land
     * @param desc deskripsi yang dimiliki oleh kartu land
     * @param image path menuju image yang dimiliki oleh kartu land
     */
    public Land(String name, Element elmt, String desc, String image){
        super(name, elmt, desc, image, 0);
    }


    /**
     * Mengembalikan true jika elemen valid, yaitu elemen yang dimiliki oleh kartu land adalah salah
     * satu dari kelima elemen pada permainan (AIR, WATER, FIRE, EARTH, ENERGY)
     * @return true jika elemen valid
     */
    public boolean isElementValid() {
        return ((this.element == Element.AIR) || (this.element == Element.WATER) || (this.element == Element.FIRE) || (this.element == Element.EARTH) || (this.element == Element.ENERGY));
    }

    // Method Implementation

    /**
     * Memainkan kartu dalam permainan, menghapus diri dari tangan currentplayer dan menambah power
     * dari player sesuai dengan element yang dimiliki kartu land
     * @param gm GameManager sebagai pengatur permainan
     * @param idx index kartu target dalam arena
     * @param isPlayedonEnemy true apabila dimainkan ke musuh
     */
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy) {
        if (this.isElementValid()) {
            PlayerStats temp = gm.getCurrentPlayer().getPlayerStats();
            temp.incrementPower(this.element);
            temp.setPlayedLandThisRound(true);
            gm.getCurrentPlayer().getPlayerHands().remove(this);
            gm.addToDiscardPile(this);
        }
    }

    /**
     * Mengembalikan true apabila kartu land bisa dimainkan, yaitu jika player p belum memainkan
     * kartu land pada round tertentu.
     * @param p player yang kartunya mau dimainkan
     * @return true apabila kartu dapat dimainkan
     */
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

    /**
     * Mengembalikan CardSprite sederhana dari kartu land
     * @param x posisi x sprite dalam piksel
     * @param y posisi y sprite dalam piksel
     * @param isFlipped keadaan kartu terbalik atau tidak
     * @return sebuah sprite sederhana berisi gambar dan informasi umum dari kartu land
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
        attr="LAND";
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(attr,25,354,"Arial Bold",32);
        return cs;
    }

    /**
     * Mengembalikan CardSprite yang lebih detail dari kartu
     * @return sebuah sprite berisi gambar dan informasi lengkap dari kartu land
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

        type="[ Land ]";
        attr="POW/+1";
        cs.InsertText(this.name,33,35);   
        cs.InsertText(type,20,353);
        cs.InsertText(this.description,25,380);
        cs.InsertText(attr,25,545);

        return cs;
    }
}
