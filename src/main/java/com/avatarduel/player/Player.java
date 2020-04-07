// Player.java

package com.avatarduel.player;

import java.util.List;
import java.util.Stack;

public class Player {
    private boolean isTopPlayer;
    private PlayerArena playerArena;
    private List<Card> playerHands;
    private PlayerStats playerStats;
    private Stack<Card> playerDeck; 

    // Constructor
    public Player(){
        this.isTopPlayer = false;
        this.playerArena = new PlayerArena();
        this.playerHands = new List<Card>();
        this.playerStats = new PlayerStats();
        this.playerDeck = new Stack<Card>();
    }

    // Setter for Player
    public void setTopPlayer(){
        this.isTopPlayer = true;
    }

    public void setDownPlayer(){
        this.isTopPlayer = false;
    }
    
    public void addPlayerHands(Card kartu){
        this.playerHands.add(kartu);
    }
    
    // Getter for Player
    public boolean getIsTopPlayer(){
        return this.isTopPlayer;
    }
    
    public PlayerArena getPlayerArena(){
        return this.playerArena;
    }

    public List<Card> getPlayerHands(){
        return this.playerHands;
    }

    public PlayerStats getPlayerStats(){
        return this.playerStats;
    }
    
    public Stack<Card> getPlayerDeck(){
        return this.playerDeck;
    }
    
    public Card getCardFromDeck(){
        return this.playerDeck.pop();
    }
}