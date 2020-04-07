// ArenaClickInfo.java

package com.avatarduel.gameState;

public class ArenaClickInfo {
    // chosenCard -> kartu yang dipilih
    private Card chosenCard;
    // isCharacter -> True kalo slot karakter, false kalo slot skill
    private boolean isCharacter;
    // isTopPlayer -> True kalo bagian arena yang di klik di belah atas
    private boolean isTopPlayer;
    // idx -> 0 sampai 7 kiri ke kanan
    private int idx;
    // characterSlotOccupied -> True kalo di kolom itu ada karakter
    private boolean characterSlotOccupied;
    // True kalo di kolom itu ada skill (aura / power up)
    private boolean skillSlotOccupied;

    // Constructor
    public ArenaClickInfo(){
        // Do Nothing
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