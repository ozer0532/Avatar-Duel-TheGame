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
}