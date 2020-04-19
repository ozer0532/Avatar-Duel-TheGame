package com.avatarduel.player;

import java.util.ArrayList;
import java.util.Stack;
import com.avatarduel.player.*;
import com.avatarduel.model.*;
import com.avatarduel.card.*;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    @Test
    public void test(){
        Player p = new Player(true);
        Char cA = new Char("nameA", Element.AIR, "descA", "", 10, 10, 10);
        PlayerArena pa = new PlayerArena();
        ArrayList<Card> ph = new ArrayList<Card>();
        PlayerStats ps = new PlayerStats();
        Stack<Card> pd = new Stack<Card>();

        // test ctor
        assertEquals(true, p.getIsTopPlayer());
        assertEquals(pa, p.getPlayerArena());
        assertEquals(ph, p.getPlayerHands());
        assertEquals(ps, p.getPlayerStats());
        assertEquals(pd, p.getPlayerDeck());

        // test setter
        p.setDownPlayer();
        assertEquals(false, p.getIsTopPlayer());
        p.setTopPlayer();
        assertEquals(true, p.getIsTopPlayer());
        ps.setHealth(10);
        p.setPlayerStats(ps);
        pa.addCharacterCard(cA);
        p.setPlayerArena(pa);
        assertEquals(ps, p.getPlayerStats());
        assertEquals(pa, p.getPlayerArena());
        p.addPlayerHands(cA);
        ph.add(cA);
        assertEquals(ph, p.getPlayerHands());

        // test getter
        assertEquals(null, p.getCardFromDeck());
    }
}