// Destroy.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class Destroy extends Skill {
    // Constructor
    public Destroy(String name, Element elmt, String desc, CardSprite sprite, int pn){
        super(name, elmt, desc, sprite, pn);
    }

    // Method Implementation
    public void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy){
        // hapus musuh di sisi berlawanan
        if (isPlayedonEnemy) {
            gm.getOppositePlayer().getPlayerArena().removeCharCard(idx);
        }
        else {
            gm.getCurrentPlayer().getPlayerArena().removeSkillCard(idx);
        }
        
    }

    public boolean CanBePlayed(PlayerStats ps){
        if (ps.getRemainingPower(super.getElmt()) >= this.powerNeeded){
            return true;
        }
        else {
            return false;
        }
    }
}