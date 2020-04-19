// Char.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;
import com.avatarduel.sprite.CardText;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;


/**
 * Char adalah turunan dari Card. Char Card adalah class yang merepresentasikan kartu bertipe character
 * pada permainan Avatar Duel. Kelas ini memiliki atribut atk, def, dan boolean isDefense.
 * 
 * Kelas ini mengimplementasikan semua abstract method yang dimiliki oleh kelas Card.
 */
public class Char extends Card {

    /**
     * jumlah atribut attack yang dimiliki oleh Char Card
     */
    private int atk;

    /**
     * jumlah atribut defense yang dimiliki oleh Char Card
     */
    private int def;

    /**
     * boolean yang merepresentasikan posisi kartu: bernilai true jika dalam posisi bertahan, dan false
     * jika dalam posisi menyerang
     */
    private boolean isDefense;

    /**
     * Membuat Char Card baru dengan informasi name, elmt, desc, image, pow, atk, dan def
     * @param name nama dari kartu character
     * @param elmt elemen dari kartu character
     * @param desc deskripsi dari kartu character
     * @param image path menuju image yang dimiliki oleh kartu character
     * @param pow jumlah powerNeeded yang dibutuhkan oleh kartu character untuk digunakan
     * @param atk jumlah attack yang dimiliki kartu character
     * @param def jumlah defense yang dimiliki kartu character
     */
    public Char(String name, Element elmt, String desc, String image,
         int pow, int atk, int def){
        super(name, elmt, desc, image, pow);
        this.atk = atk;
        this.def = def;
        this.sprite = this.drawCardSimple(0, 0, true);
        this.isDefense = false;
    }

    /**
     * Mengembalikan nilai atribut atk yang dimiliki oleh kartu karakter
     * @return nilai attack dari Char
     */
    public int getAttack(){
        return this.atk;
    }

    /**
     * Mengembalikan nilai attack total dari kartu saat kartu sedang terkena efek dari aura,
     * yaitu jumlah attack pada char card ditambah jumlah attack yang dimiliki Aura ar
     * @param ar kartu Aura yang mempengaruhi Char Card
     * @return nilai attack total yang dimiliki Char Card setelah ditambah attack yang dimiliki ar
     */
    public int getAttack(Aura ar){
        return this.atk + ar.getAttack();
    }
    
    /**
     * Mengembalikan nilai atribut def yang dimiliki oleh kartu karakter
     * @return nilai defense dari Char
     */
    public int getDefense(){
        return this.def;
    }   

    /**
     * Mengembalikan nilai defense total dari kartu saat kartu sedang terkena efek dari aura,
     * yaitu jumlah defense pada char card ditambah jumlah defense yang dimiliki Aura ar
     * @param ar kartu Aura yang mempengaruhi Char Card
     * @return nilai defense total yang dimiliki Char Card dibawah pengaruh ar 
     */
    public int getDefense(Aura ar){
        if (this.isDefense) {
            return this.def + ar.getDefense();
        }
        else {
            return this.getAttack(ar);
        }
    }

    /**
     * mengembalikan nilai isDefense yang dimiliki oleh Char Card
     * @return isDefense of Char Card
     */
    public boolean getIsDefense() {
        return this.isDefense;
    }


    /**
     * mengganti nilai atribut atk pada Char Card (jumlah attack yang dimiliki kartu)
     * @param atk nilai atk yang baru
     */
    public void setAttack(int atk) {
        this.atk=atk;
    }

    /**
     * mengganti nilai atribut def pada Char Card (jumlah defense yang dimiliki kartu)
     * @param def nilai def yang baru
     */
    public void setDefense(int def) {
        this.def=def;
    }

    /**
     * mengganti nilai atribut isDefense pada Char Card (boolean yang merepresentasikan apakah kartu dalam posisi bertahan)
     * @param isdef niali isDefense yang baru
     */
    public void setIsDefense(boolean isdef) {
        this.isDefense=isdef;
    }


    // Method Implementation

    /**
     * Memainkan kartu dalam permainan, menghapus kartu dari tangan current player dan menaruhnya ke posisi idx pada arena
     * @param gm GameManager sebagai pengatur permainan
     * @param idx index posisi target dalam arena
     * @param isPlayedonEnemy true apabila dimainkan ke musuh
     */
    public void onCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        PlayerArena temp = gm.getCurrentPlayer().getPlayerArena();
        temp.addCharacterCard(idx,this);
        gm.getCurrentPlayer().getPlayerHands().remove(this);
        gm.getCurrentPlayer().getPlayerStats().usePower(element, powerNeeded);
    }

    /**
     * Mengembalikan true apabila kartu dapat dimainkan
     * @param p player yang kartunya mau dimainkan
     * @return true apabila kartu dapat dimainkan
     */
    public boolean canBePlayed(Player p){
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
     * Mengembalikan CardSprite sederhana dari kartu kakrakter
     * @param x posisi x sprite dalam piksel
     * @param y posisi y sprite dalam piksel
     * @param isFlipped keadaan kartu terbalik atau tidak
     * @return sebuah sprite sederhana berisi gambar dan informasi umum dari kartu karakter
     */
    public CardSprite drawCardSimple(float x, float y, boolean isFlipped) {
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
        cs.insertText(attr,25,354,"Arial Bold",32);
        return cs;
    }

    /**
     * Mengembalikan CardSprite yang lebih detail dari kartu karakter
     * @return sebuah sprite berisi gambar dan informasi lengkap dari kartu karakter
     */
    public CardSprite drawCardDetail() {
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
        cs.setImagePos(36, 69);

        attr="ATK/"+this.atk+" | "+"DEF/"+this.def+" | "+"POW/"+this.powerNeeded;
        type="[ Character ]";
        cs.insertText(this.name,33,35);   
        cs.insertText(type,20,353);
        cs.insertText(this.description,25,380);
        cs.insertText(attr,25,545);

        return cs;
    }
}