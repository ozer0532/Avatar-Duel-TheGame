package com.avatarduel.player;

import java.util.Arrays;
import com.avatarduel.card.Char;
import com.avatarduel.card.Skill;

/**
 * PlayerArena adalah class yang mengatur kondisi
 * arena yang dimiliki player, seperti kartu apa saja
 * yang berada di arena
 */
public class PlayerArena {
    /**
     * Atribut-atribut dari PlayerArena
     */
    static int MAX_CARD = 8;
    private Char[] character;
    private Skill[] skills;

    /* Constructor */
    /**
     * Membuat PlayerArena dengan slot kartu Character 8
     * dan slot kartu Skill 8
     */
    public PlayerArena(){
        this.character = new Char[8];
        this.skills = new Skill[8];

        Arrays.fill(character, null);
        Arrays.fill(skills, null);
    }

    /* SETTER */
    /**
     * Menambahkan kartu Character pada slot kartu Player
     * Jika slot penuh akan memberikan output "Character slot is full"
     * @param charCard kartu Character yang akan ditambahkan
     */
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

    /**
     * Menambahkan kartu Skill pada slot kartu Player
     * Jika slot penuh akan memberikan output "Skill slot is full"
     * @param skillCard kartu Skill yang akan ditambahkan
     */
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

    /**
     * Menambahkan kartu Character pada slot kartu Player di index tertentu
     * @param idx index yang akan ditambahkan
     * @param charCard kartu Character yang akan ditambahkan
     */
    public void addCharacterCard(int idx, Char charCard){
        this.character[idx] = charCard;
    }

    /**
     * Menambahkan kartu Skill pada slot kartu Player di index tertentu
     * @param idx index yang akan ditambahkan
     * @param skillCard kartu Skill yang akan ditambahkan
     */
    public void addSkillCard(int idx, Skill skillCard){
        this.skills[idx] = skillCard;
    }

    /* GETTER */
    /**
     * Getter untuk slot kartu Character
     * @return kondisi slot kartu Character
     */
    public Char[] getCharCard(){
        return this.character;
    }

    /**
     * Getter untuk slot kartu Skill
     * @return kondisi slot kartu Skill
     */
    public Skill[] getSkillCard(){
        return this.skills;
    }

    /**
     * Getter untuk kartu yang berada di slot kartu Character
     * @param idx index slot
     * @return kartu Character di index idx
     */
    public Char getCharCard(int idx){
        return this.character[idx];
    }

    /**
     * Getter untuk kartu yang berada di slot kartu Skill
     * @param idx index slot
     * @return kartu Skill di index idx
     */
    public Skill getSkillCard(int idx){
        return this.skills[idx];
    }

    /* Util */
    /**
     * Mengubah kartu Character di slot tertentu menjadi null
     * @param idx index slot
     */
    public void removeCharCard(int idx){
        this.character[idx] = null;
    }

    /**
     * Mengubah kartu Skill di slot tertentu menjadi null
     * @param idx index slot
     */
    public void removeSkillCard(int idx){
        this.skills[idx] = null;
    }

    /**
     * Menghitung jumlah kartu Character yang berada di slot kartu
     * @return jumlah kartu Character
     */
    public int charCardCount(){
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (character[i] != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Menghitung jumlah kartu Skill yang berada di slot kartu
     * @return jumlah kartu Skill
     */
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