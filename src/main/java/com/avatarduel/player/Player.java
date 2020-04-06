package com.avatarduel.player;

import java.util.List;
import java.util.Stack;

public class Player {
    private boolean isTopPlayer;
    private PlayerArena playerArena;
    private List<Card> playerHands;
    private PlayerStats playerStats;
    private Stack<Card> playerDeck; 
}