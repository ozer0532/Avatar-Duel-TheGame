// Skill.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

/**
 * Skill merupakan abstract class sekaligus turunan dari Card. Skill Card melambangkan kartu skill 
 * dalam permainan Avatar Duel. Skill ini masih harus diturunkan mengingat terdapat 3 jenis skill 
 * dalam permainan, yaitu Aura, Destroy, dan Land.
 */
public abstract class Skill extends Card {
    /**
     * Membuat Skill Card baru dengan informasi name, elmt, desc, image, dan pn
     * @param name nama dari kartu skill
     * @param elmt element yang digunakan dalam kartu skill
     * @param desc deskripsi yang dimiliki oleh kartu skill
     * @param image sebuah path melambangkan kartu yang akan digambarkan
     * @param pn jumlah power yang diinginkan
     */
    public Skill(String name, Element elmt, String desc, String image, int pn){
        super(name, elmt, desc, image, pn);
    }
    
    /**
     * Memainkan kartu skill dalam permainan
     * @param gm GameManager sebagai pengatur permainan
     * @param idx index kartu dalam arena
     * @param isPlayedonEnemy true apabila dimainkan ke musuh
     */
    public abstract void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy);
    
    /**
     * Mengembalikan true apabila kartu bisa dimainkan dengan kondisi power mencukupi
     * @param ps player yang kartu skillnya mau dimainkan
     * @return true apabila kartu skill bisa dimainkan
     */
    public abstract boolean CanBePlayed(Player ps);

    /**
     * Mengembalikan informasi sederhana dari sebuah kartu skill
     * @param x posisi x dari mouse
     * @param y posisi y dari mouse
     * @param isFlipped keadaan kartu terbalik atau tidak
     * @return sebuah sprite berisi informasi detail dari kartu skill
     */
    public abstract CardSprite DrawCardSimple(float x, float y, boolean isFlipped);

    /**
     * Mengembalikan informasi detail dari sebuah kartu skill
     * @return sebuah sprite berisi informasi detail dari kartu
     */
    public abstract CardSprite DrawCardDetail();
}