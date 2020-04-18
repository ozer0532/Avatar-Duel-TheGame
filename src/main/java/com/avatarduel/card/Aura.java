// Aura.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

/**
 * Aura merupakan class yang inheritance dari skill. Aura bertanggung jawab dalam representasi kartu skill
 * berjenis aura dalam permainan Avatar Duel.
 */
public class Aura extends Skill {
    // Atribut dari Aura
    private int atk;
    private int def;

    // Constructor
    /**
     * Membuat sebuah Aura baru dengan masukan name, elmt, desc, image, pn, atk, dan def
     * @param name nama dari skill aura
     * @param elmt element dari skill aura
     * @param desc deskripsi dari skill aura
     * @param image path image dari skill aura
     * @param pn jumlah power yang dibutuhkan
     * @param atk nilai atk yang dihasilkan skill aura
     * @param def nilai def yang dihasilkan skill aura
     */
    public Aura(String name, Element elmt, String desc, String image,
    int pn,int atk, int def){
        super(name, elmt, desc, image, pn);
        this.atk = atk;
        this.def = def;
        this.sprite = this.DrawCardSimple(0, 0, true);
    }

    /**
     * Mengganti nilai attack sebelumnya dengan attack yang baru
     * @param atk nilai attack yang baru
     */
    public void setAttack(int atk){
        this.atk = atk;
    }

    /**
     * Mengganti nilai defense sebelumnya dengan defense yang baru
     * @param def nilai defense yang baru
     */
    public void setDefense(int def){
        this.def = def;
    }

    /**
     * Mengembalikan nilai attack dari kartu aura
     * @return nilai attack aura
     */
    public int getAttack(){
        return this.atk;
    }

    /**
     * Mengembalikan nilai defense dari kartu aura
     * @return nilai defense aura
     */
    public int getDefense(){
        return this.def;
    }

    /**
     * Memainkan kartu aura dalam permainan
     * @param gm GameManager sebagai pengatur permainan
     * @param idx index kartu dalam arena
     * @param isPlayedonEnemy true apabila dimainkan ke musuh
     */
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

    /**
     * Mengembalikan true apabila kartu bisa dimainkan dengan kondisi power mencukupi
     * @param p player yang kartu auranya mau dimainkan
     * @return true apabile kartu aura bisa dimainkan
     */
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

    /**
     * Mengembalikan informasi sederhana dari sebuah kartu aura
     * @param x posisi x dari mouse
     * @param y posisi y dari mouse
     * @param isFlipped keadaan kartu terbalik atau tidak
     * @return sebuah sprite berisi informasi detail dari kartu aura
     */
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
    
    /**
     * Mengembalikan informasi detail dari sebuah kartu aura
     * @return sebuah sprite berisi informasi detail dari kartu
     */
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