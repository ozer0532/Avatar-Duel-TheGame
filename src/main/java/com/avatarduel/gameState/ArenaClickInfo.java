// ArenaClickInfo.java
package com.avatarduel.gamestate;

import com.avatarduel.card.*;

/**
 * ArenaClickInfo merupakan class yang menyimpan data dari event klik di arena yang selanjutnya
 * atributnya dapat digunakan melalui method getter yang disediakan
 */

public class ArenaClickInfo {
    private Card chosenCard;
    private boolean isCharacter;
    private boolean isTopPlayer;
    private int idx;
    private boolean characterSlotOccupied;
    private boolean skillSlotOccupied;
    private boolean isEnemy;

    /**
     * Membuat ArenaClickInfo baru berdasarkan masukan Card, index di arena, serta boolean-boolean untuk pengecekan
     * @param chosenCard kartu yang dipilih
     * @param isCharacter boolean : true jika slot karakter, false jika slot skill
     * @param isTopPlayer boolean : true jika bagian arena yang di klik berada di belahan atas
     * @param idx index di arena
     * @param cso boolean : true jika di kolom tersebut ada karakter, false jika tidak ada
     * @param sso boolean : true jika di kolom tersebut ada skill, false jika tidak ada
     * @param enemy boolean : true jika musuh di klik, false jika tidak
     */
    public ArenaClickInfo(Card chosenCard, boolean isCharacter, boolean isTopPlayer, int idx, boolean cso, boolean sso, boolean enemy){
        this.chosenCard = chosenCard;
        this.isCharacter = isCharacter;
        this.isTopPlayer = isTopPlayer;
        this.idx = idx;
        this.characterSlotOccupied = cso;
        this.skillSlotOccupied = sso;
        this.isEnemy = enemy;
    }

    /**
     * Mengembalikan kartu yang dipilih dari ArenaClickInfo
     * @return chosenCard of ArenaClickInfo
     */
    public Card getChosenCard(){
        return chosenCard;
    }

    /**
     * Mengembalikan nilai boolean isCharacter dari slot di arena yang dipilih
     * @return isCharacter of ArenaClickInfo
     */
    public boolean getIsCharacter(){
        return isCharacter;
    }

    /**
     * Mengembalikan nilai boolean isTopPlayer dari bagian arena yang dipilih
     * @return isTopPlayer of ArenaClickInfo
     */
    public boolean getIsTopPlayer(){
        return isTopPlayer;
    }

    /**
     * Mengembalikan indeks dari posisi slot kartu
     * @return idx of ArenaClickInfo
     */
    public int getIdx(){
        return idx;
    }

    /**
     * Mengembalikan nilai boolean characterSlotOccupied dari slot karakter yang dipilih
     * @return characterSlotOccupied of ArenaClickInfo
     */
    public boolean getCharacterSlotOccupied(){
        return characterSlotOccupied;
    }

    /**
     * Mengembalikan nilai boolean skillSlotOccupied dari slot skill yang dipilih
     * @return skillSlotOccupied of ArenaClickInfo
     */
    public boolean getSkillSlotOccupied(){
        return skillSlotOccupied;
    }

    /**
     * Mengmbalikan nilai boolean isEnemy dari belahan arena yang dipilih
     * @return isEnemy of ArenaClickInfo
     */
    public boolean getIsEnemy() {
        return isEnemy;
    }
}