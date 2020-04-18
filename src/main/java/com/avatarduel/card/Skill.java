// Aura.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;

import javafx.scene.image.Image;

import com.avatarduel.gamemanager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public abstract class Skill extends Card {
    // Constructor
    public Skill(String name, Element elmt, String desc, String image, int pn){
        super(name, elmt, desc, image, pn);
    }
    // Abstract Methods
    public abstract void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy);
    public abstract boolean CanBePlayed(Player ps);
    public abstract CardSprite DrawCardSimple(float x, float y, boolean isFlipped);
    public abstract CardSprite DrawCardDetail();
}