// Card.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;


/**
 * Card adalah abstract class yang merepresentasikan semua kartu dalam permainan 
 * Avatar Duel. Kelas ini menyimpan atribut-atribut yang dimiliki suatu kartu: 
 * name, element, description, image, sprite, dan powerNeeded.
 * 
 * Kelas ini masih harus diturunkan menjadi 3 tipe yang dimiliki oleh kartu,
 * yaitu Char(character), Land, dan Skill.
 * 
 * Terdapat 4 buah abstract method yang akan diimplementasikan oleh turunan dari kelas Card,
 * yaitu OnCardPlayed, CanBePlayed, DrawCardSimple, dan DrawCardDetail.
 */
public abstract class Card {
    /**
     * Informasi-informasi yang dimiliki Card
     */
    protected String name;
    protected Element element;
    protected String description;
    protected int powerNeeded;

    /**
     * path menuju image yang dimiliki Card
     */
    protected String image;

    /**
     * objek card versi GUI
     */
    protected CardSprite sprite;

    /**
     * Membuat Card baru dengan informasi name, elmt, desc, image, dan pn, dan memasang
     * attribut sprite dengan hasil dari pememanggilan method DrawCardSimple
     * @param name nama kartu
     * @param elmt element yang dimiliki oleh kartu
     * @param desc deskripsi dari kartu
     * @param image path menuju image yang dimiliki kartu
     * @param pow jumlah power yang dibutuhkan untuk menggunakan kartu
     */
    public Card(String name, Element elmt, String desc, String image, int pow) {
        this.name = name;
        this.element = elmt;
        this.description = desc;
        this.image = image;
        this.powerNeeded = pow;
        this.sprite = this.DrawCardSimple(0, 0, true);
    }

    // Setter for Card

    /**
     * Mengganti atribut name pada Card (nama kartu) 
     * @param name nama kartu yang baru
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mengganti atribut element pada Card (elemen kartu)
     * @param elmt elemen kartu yang baru
     */
    public void setElmt(Element elmt) {
        this.element = elmt;
    }

    /**
     * Mengganti atribut description pada Card (deskripsi kartu)
     * @param desc deskripsi kartu yang baru
     */
    public void setDesc(String desc) {
        this.description = desc;
    }

    /**
     * Mengganti atribut sprite pada Card (objek kartu versi GUI)
     * @param spr CardSprite yang baru
     */
    public void setSprite(CardSprite spr) {
        this.sprite = spr;
    }

    /**
     * Mengganti atribut image pada Card (path menuju image yang dimiliki kartu)
     * @param img path yang baru
     */
    public void setImage(String img) {
        this.image = img;
    }

    /**
     * Mengganti atribut powerNeeded pada Card (power yang dibutuhkan untuk menggunakan kartu)
     * @param p jumlah powerNeeded yang baru
     */
    public void setPowerNeeded(int p){
        this.powerNeeded = p;
    }

    // Getter for Card

    /**
     * Mengembalikan name dari Card
     * @return name of Card
     */
    public String getName() {
        return this.name;
    }

    /**
     * Mengembalikan element dari Card
     * @return element of Card
     */
    public Element getElmt() {
        return this.element;
    }

    /**
     * Mengembalikan description dari Card
     * @return description of Card
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Mengembalikan sprite dari Card
     * @return sprite of Card
     */
    public CardSprite getSprite() {
        return this.sprite;
    }

    /**
     * Mengembalikan powerNeeded dari Card
     * @return powerNeeded of Card (jumlah power yang dibutuhkan untuk menggunakan kartu)
     */
    public int getPowerNeeded() {
        return this.powerNeeded;
    }

    // Abstract Methods

    /**
     * Memainkan kartu dalam permainan
     * @param gm GameManager sebagai pengatur permainan
     * @param idx index posisi target dalam arena
     * @param isPlayedonEnemy true apabila dimainkan ke musuh
     */
    public abstract void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy);

    /**
     * Mengembalikan true apabila kartu bisa dimainkan
     * @param p player yang kartunya mau dimainkan
     * @return true apabila kartu dapat dimainkan
     */
    public abstract boolean CanBePlayed(Player p);

    /**
     * Mengembalikan CardSprite sederhana dari kartu
     * @param x posisi x sprite dalam piksel
     * @param y posisi y sprite dalam piksel
     * @param isFlipped keadaan kartu terbalik atau tidak
     * @return sebuah sprite sederhana berisi gambar dan informasi umum dari kartu
     */
    public abstract CardSprite DrawCardSimple(float x, float y, boolean isFlipped);

    /**
     * Mengembalikan CardSprite yang lebih detail dari kartu
     * @return sebuah sprite berisi gambar dan informasi lengkap dari kartu
     */
    public abstract CardSprite DrawCardDetail();
}