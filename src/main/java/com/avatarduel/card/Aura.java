// Aura.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Aura extends Skill {
    // Atribut dari Aura
    private int atk;
    private int def;

    // Constructor
    public Aura(String name, Element elmt, String desc,
    int pn,int atk, int def){
        super(name, elmt, desc, pn);
        this.atk = atk;
        this.def = def;
    }

    // Setter for Aura
    public void setAttack(int atk){
        this.atk = atk;
    }

    public void setDefense(int def){
        this.def = def;
    }

    // Getter for Aura
    public int getAttack(){
        return this.atk;
    }

    public int getDefense(){
        return this.def;
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        PlayerArena temp = gm.getCurrentPlayer().getPlayerArena();
        temp.addSkillCard(idx,this);
        gm.getCurrentPlayer().setPlayerArena(temp);
    }

    public boolean CanBePlayed(PlayerStats ps){
        if (ps.getRemainingPower(super.getElmt()) >= this.powerNeeded){
            return true;
        }
        else {
            return false;
        }
    }

    public CardSprite DrawCardSimple(float x, float y, boolean isFlipped) {
        CardSprite cs;
        String imagePath, front, back, stext;

        //imagePath="../../../../resources/com/avatarduel/card/image/character/"+this.name;
        //front="";
        //back="";
        stext="ATK/"+this.atk+" | "+"DEF/"+this.def+" | "+"POW/"+this.powerNeeded;
        cs = new CardSprite(front, back, imagePath, x, y);
        cs.InsertText(stext,0,0);
        return cs;
    }
    public CardSprite DrawCardDetail() {
        return cs;
    }
}