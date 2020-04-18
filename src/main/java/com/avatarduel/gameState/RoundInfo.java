// RoundInfo.java
package com.avatarduel.gamestate;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.card.Char;

/**
 * RoundInfo merupakan class yang bertanggung jawab untuk mencatat kartu-kartu yang telah
 * dimainkan dan kartu-kartu yang diserang dalam ronde tersebut
 */

public class RoundInfo {
    private List<Char> playedCards;
    private List<Char> cardsAttacked; 

    /**
     * Membuat RoundInfo baru
     */
    public RoundInfo(){
        this.playedCards = new ArrayList<>();
        this.cardsAttacked = new ArrayList<>();
    }

    /**
     * Mengembalikan list berisi kartu karakter yang telah dimainkan pada ronde tersebut
     * @return playedCards of RoundInfo
     */
    public List<Char> getPlayedCards(){
        return this.playedCards;
    }

    /**
     * Mengembalikan list berisi kartu karakter yang diserang pada ronde tersebut
     * @return cardsAttacked of RoundInfo
     */
    public List<Char> getCardsAttacked(){
        return this.cardsAttacked;
    }

    /**
     * Menambahkan kartu karakter yang telah dimainkan pada ronde tersebut
     * ke dalam list playedCards
     * @param kartu yang telah dimainkan
     */
    public void addPlayedCards(Char kartu){
        this.playedCards.add(kartu);
    }

    /**
     * Menambahkan kartu karakter yang telah diserang pada ronde tersebut
     * ke dalam list cardsAttacked
     * @param kartu yang telah diserang
     */
    public void addCardsAttacked(Char kartu){
        this.cardsAttacked.add(kartu);
    }
}
