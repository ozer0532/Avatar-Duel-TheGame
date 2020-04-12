package com.avatarduel.gamestate;

import java.util.ArrayList;
import java.util.List;

public class RoundInfo {
    private List<Character> playedCards;
    private List<Character> cardsAttacked; 

    public RoundInfo(){
        this.playedCards = new ArrayList<>();
        this.cardsAttacked = new ArrayList<>();
    }

    public List<Character> getPlayedCards(){
        return this.playedCards;
    }

    public List<Character> getCardsAttacked(){
        return this.cardsAttacked;
    }

    public void addPlayedCards(Character kartu){
        this.playedCards.add(kartu);
    }

    public void addCardsAttacked(Character kartu){
        this.cardsAttacked.add(kartu);
    }
}
