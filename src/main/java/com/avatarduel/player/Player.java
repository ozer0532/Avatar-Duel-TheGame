package com.avatarduel.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.avatarduel.card.Card;

/**
 * Player adalah class yang mengatur segala
 * informasi mengenai pemain, seperti kartu yang
 * ada di hand dan deck, stats player, kartu yang 
 * ada di arena player dan posisi player dalam permaianan
 */
public class Player {
    /**
     * Atribut-atribut dari Player
     */
    private boolean isTopPlayer;
    private PlayerArena playerArena;
    private ArrayList<Card> playerHands;
    private PlayerStats playerStats;
    private Stack<Card> playerDeck; 

    /**
     * Membuat Player berdasarkan masukan input topPlayer
     * @param topPlayer posisi player dalam permainan
     */
    public Player(boolean topPlayer){
        this.isTopPlayer = topPlayer;
        this.playerArena = new PlayerArena();
        this.playerHands = new ArrayList<Card>();
        this.playerStats = new PlayerStats();
        this.playerDeck = new Stack<Card>();
    }

    // Setter for Player
    /**
     * Setter untuk atribut topPLayer
     * menandakan player berada di posisi atas
     */
    public void setTopPlayer(){
        this.isTopPlayer = true;
    }

    /**
     * Setter untuk atribut topPlayer
     * menandakan player berada di posisi bawah
     */
    public void setDownPlayer(){
        this.isTopPlayer = false;
    }

    /**
     * Setter untuk atribut playerStats
     * @param ps status player
     */
    public void setPlayerStats(PlayerStats ps){
        this.playerStats = ps;
    }
    
    /**
     * Menambah kartu di hand player
     * @param kartu kartu yang akan di tambahkan ke hand player
     */
    public void addPlayerHands(Card kartu){
        this.playerHands.add(kartu);
    }
    
    /**
     * Setter untuk atribut playerArena
     * @param pa kondisi arena yang dimiliki player
     */
    public void setPlayerArena(PlayerArena pa) {
        this.playerArena=pa;
    }

    // Getter for Player
    /**
     * Getter untuk atribut topPLayer
     * @return true apabila player berada di posisi atas
     */
    public boolean getIsTopPlayer(){
        return this.isTopPlayer;
    }
    
    /**
     * Getter untuk atribut playerArena
     * @return kondisi arena yang dimiliki player
     */
    public PlayerArena getPlayerArena(){
        return this.playerArena;
    }

    /**
     * Getter untuk atribut playerHands
     * @return kartu yang berada di hand player
     */
    public List<Card> getPlayerHands(){
        return this.playerHands;
    }

    /**
     * Getter untuk atribut playerStats
     * @return status player
     */
    public PlayerStats getPlayerStats(){
        return this.playerStats;
    }
    
    /**
     * Getter untuk atribut playerDeck
     * @return deck player
     */
    public Stack<Card> getPlayerDeck(){
        return this.playerDeck;
    }
    
    /**
     * Getter untuk kartu yang berada di deck
     * @return kartu yang berada di tumpukan paling atas di deck player
     */
    public Card getCardFromDeck(){
        return this.playerDeck.pop();
    }
}