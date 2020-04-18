package com.avatarduel.gamestate;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.card.Char;

public class RoundInfo {
    private List<Char> playedCards;
    private List<Char> cardsAttacked; 

    public RoundInfo(){
        this.playedCards = new ArrayList<>();
        this.cardsAttacked = new ArrayList<>();
    }

    public List<Char> getPlayedCards(){
        return this.playedCards;
    }

    public List<Char> getCardsAttacked(){
        return this.cardsAttacked;
    }

    public void addPlayedCards(Char kartu){
        this.playedCards.add(kartu);
    }

    public void addCardsAttacked(Char kartu){
        this.cardsAttacked.add(kartu);
    }
}
