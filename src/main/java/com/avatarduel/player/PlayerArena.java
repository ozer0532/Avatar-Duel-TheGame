package com.avatarduel.player;

import java.util.ArrayList;

public class PlayerArena {
    static int MAX_CARD = 8;
    private ArrayList<Character> character;
    private ArrayList<Aura> skills;

    /* CTOR */
    public PlayerArena(){
        this.character = new ArrayList<Character>();
        this.skills = new ArrayList<Aura>();
    }

    /* UTIL */
    public void addCharacterCard(Card kartu){
        this.character.add(kartu);
    }

    public void addCharacterCard(int idx, Card kartu){
        this.character.add(idx-1, kartu);
    }

    public void addSkillCard(Card kartu){
        this.skills.add(kartu);
    }

    public void addSkillCard(int idx, Card kartu){
        this.skills.add(idx-1, kartu);
    }

    public void useCharacterCard(int idx){
        return this.character.get(idx-1);
    }

    public void useAuraCard(int idx){
        return this.skills.get(idx-1);
    }
}