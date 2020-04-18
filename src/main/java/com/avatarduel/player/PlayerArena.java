package com.avatarduel.player;

import java.util.Arrays;
import com.avatarduel.card.Char;
import com.avatarduel.card.Skill;

public class PlayerArena {
    // Atribut
    static int MAX_CARD = 8;
    private Char[] character;
    private Skill[] skills;

    // Constructor
    public PlayerArena(){
        this.character = new Char[8];
        this.skills = new Skill[8];

        Arrays.fill(character, null);
        Arrays.fill(skills, null);
    }

    /* SETTER */
    public void addCharacterCard(Char charCard){
        boolean flag = false;
        for (int i = 0; i < 8; i++){
            if (this.character[i] == null){
                this.character[i] = charCard;
                flag = true;
                break;
            }
        }
        if (!flag){
            System.out.println("Character slot is full");
        }
    }

    public void addSkillCard(Skill skillCard){
        boolean flag = false;
        for (int i = 0; i < 8; i++){
            if (this.skills[i] == null){
                this.skills[i] = skillCard;
                flag = true;
                break;
            }
        }
        if (!flag){
            System.out.println("Skill slot is full");
        }
    }

    public void addCharacterCard(int idx, Char charCard){
        this.character[idx] = charCard;
    }

    public void addSkillCard(int idx, Skill skillCard){
        this.skills[idx] = skillCard;
    }

    /* GETTER */
    public Char[] getCharCard(){
        return this.character;
    }

    public Skill[] getSkillCard(){
        return this.skills;
    }

    public Char getCharCard(int idx){
        return this.character[idx];
    }

    public Skill getSkillCard(int idx){
        return this.skills[idx];
    }

    public void removeCharCard(int idx){
        this.character[idx] = null;
    }

    public void removeSkillCard(int idx){
        this.skills[idx] = null;
    }

    public int charCardCount(){
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (character[i] != null) {
                count++;
            }
        }
        return count;
    }

    public int skillCardCount(){
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (skills[i] != null) {
                count++;
                System.out.println(skills[i].getName());
                System.out.println(i);
            }
        }
        return count;
    }
}