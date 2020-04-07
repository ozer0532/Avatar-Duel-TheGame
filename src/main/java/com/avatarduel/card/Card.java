// Card.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

abstract class Card {
    private String name;
    private Element element;
    private String description;
    private CardSprite sprite;

    public Card(String name, Element elmt, String desc, CardSprite spr) {
        this.name = name;
        this.element = elmt;
        this.description = desc;
        this.sprite = spr;
    }

    // Setter for Card
    public void setName(String name) {
        this.name = name;
    }
    public void setElmt(Element elmt) {
        this.element = elmt;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }
    public void setSprite(CardSprite spr) {
        this.sprite = spr;
    }

    // Getter for Card
    public String getName() {
        return this.name;
    }
    
    public Element getElmt() {
        return this.element;
    }

    public String getDesc() {
        return this.description;
    }
    public CardSprite getSprite() {
        return this.sprite;
    }

    // Abstract Methods
    public abstract void OnCardPlayed(GameManager gm);
    public abstract boolean CanBePlayed(PlayerStats ps);

    // Public Methods
    public void DrawCardSimple(float x, float y, boolean isFlipped){
        // do nothing
    }
    public void DrawCardDetail(){
        // do nothing
    }

}