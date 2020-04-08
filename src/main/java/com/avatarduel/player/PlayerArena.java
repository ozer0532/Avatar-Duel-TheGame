package com.avatarduel.player;

import com.avatarduel.card.*;
import com.avatarduel.card.Character;

public class PlayerArena {
    static int MAX_CARD = 8;
    private Character[] charCard;
    private Skill[] skills;

    /* CTOR */
    public PlayerArena(){
        this.charCard = new Character[MAX_CARD];
        this.skills = new Skill[MAX_CARD];
    }

    // Getter
    public Character[] getCharCard(){
        return this.charCard;
    }

    public Skill[] getSkills(){
        return this.skills;
    }

    // Setter
    public void setCharCard(Character[] charCard){
        this.charCard = charCard;
    }

    public void setSkills(Skill[] skills){
        this.skills = skills;
    }

    /* UTIL */
    public void addCharacterCard(Character kartu){
        for (int i = 0; i < MAX_CARD; i++){
            if (charCard[i] == null){
                charCard[i] = kartu;
            }
        }
    }

    public void addCharacterCard(int idx, Character kartu){
        this.charCard[idx-1] = kartu;
    }

    public void addSkillCard(Skill kartu){
        for (int i = 0; i < MAX_CARD; i++){
            if (skills[i] == null){
                skills[i] = kartu;
            }
        }
    }

    public void addSkillCard(int idx, Skill kartu){
        this.skills[idx-1] = kartu;
    }

    public void removeCharacterCard(int idx){
        this.charCard[idx-1] = null;
    }

    public void removeSkillCard(int idx){
        this.skills[idx-1] = null;
    }
}