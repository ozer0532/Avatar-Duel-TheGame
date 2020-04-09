// ArenaClickInfo.java

package com.avatarduel.gameState;

import com.avatarduel.card.*;

public class ArenaClickInfo {
    private Card chosenCard; // chosenCard -> kartu yang dipilih
    private boolean isCharacter; // isCharacter -> True kalo slot karakter, false kalo slot skill
    private boolean isTopPlayer; // isTopPlayer -> True kalo bagian arena yang di klik di belah atas
    private int idx; // idx -> 0 sampai 7 kiri ke kanan
    private boolean characterSlotOccupied; // characterSlotOccupied -> True kalo di kolom itu ada karakter
    private boolean skillSlotOccupied; // True kalo di kolom itu ada skill (aura / power up)

    // Constructor
    public ArenaClickInfo(Card chosenCard, boolean isCharacter, boolean isTopPlayer, int idx, boolean cso, boolean sso){
        this.chosenCard = chosenCard;
        this.isCharacter = isCharacter;
        this.isTopPlayer = isTopPlayer;
        this.idx = idx;
        this.characterSlotOccupied = cso;
        this.skillSlotOccupied = sso;
    }

    // Setter for ArenaClickInfo()
    public void setChosenCard(Card chosenCard){
        this.chosenCard = chosenCard;
    }

    public void setCharacterCard(){
        this.isCharacter = true;
    }

    public void setSkillCard(){
        this.isCharacter = false;
    }

    public void setTopPlayer(){
        this.isTopPlayer = true;
    }

    public void setDownPlayer(){
        this.isTopPlayer = false;
    }

    public void setPosition(int idx){
        this.idx = idx;
    }

    public void setCharacterSlotOccupied(){
        this.characterSlotOccupied = true;
    }

    public void setSkillSlotOccupied(){
        this.skillSlotOccupied = true;
    }

    // Getter for ArenaClickInfo
    public Card getChosenCard(){
        return chosenCard;
    }

    public boolean getIsCharacter(){
        return isCharacter;
    }

    public boolean getIsTopPlayer(){
        return isTopPlayer;
    }

    public int getIdx(){
        return idx;
    }

    public boolean getCharacterSlotOccupied(){
        return characterSlotOccupied;
    }

    public boolean getSkillSlotOccupied(){
        return skillSlotOccupied;
    }
}