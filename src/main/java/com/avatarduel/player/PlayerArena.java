package com.avatarduel.player;

import java.util.Arrays;
import com.avatarduel.card.Char;
import com.avatarduel.card.Aura;

public class PlayerArena {
    // Atribut
    static int MAX_CARD = 8;
    private Char[] character;
    private Aura[] skills;

    // Constructor
    public PlayerArena(){
        this.character = new Char[8];
        this.skills = new Aura[8];

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

    // ini buat apa ya?
    public void addCharacterCard(int idx, Char charCard){
        this.character[idx-1] = charCard;
    }

    public void addSkillCard(Aura auraCard){
        boolean flag = false;

        for (int i = 0; i < 8; i++){
            if (this.skills[i] == null){
                this.skills[i] = auraCard;
                flag = true;
                break;
            }
        }

        if (!flag){
            System.out.println("Skill slot is full");
        }
    }

    // ini buat apa ya?
    public void addSkillCard(int idx, Aura auraCard){
        this.skills[idx-1] = auraCard;
    }

    /* GETTER */
    public void useCharacterCard(int idx){
        // ini mau di remove dari array?
    }

    public void useAuraCard(int idx){
        // ini mau di remove dari array?
    }

    /* DEBUG */
    public static void main(String[] args){
        
    }
}