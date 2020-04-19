package com.avatarduel.player;

import java.util.Arrays;
import com.avatarduel.player.*;
import com.avatarduel.model.*;
import com.avatarduel.card.*;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PlayerArenaTest {
    @Test
    public void test(){
        PlayerArena pa = new PlayerArena();
        Char[] charExpected = new Char[8];
        Skill[] skillExpected = new Skill[8];
        Char cA = new Char("nameA", Element.AIR, "descA", "", 10, 10, 10);
        Char cB = new Char("nameB", Element.FIRE, "descB", "", 10, 10, 10);
        Char cC = new Char("nameC", Element.WATER, "descC", "", 10, 10, 10);

        Destroy sA = new Destroy("nameA", Element.AIR, "descA", "", 10);
        Aura sB = new Aura("nameB", Element.FIRE, "descB", "", 10, 10, 10);
        PowerUp sC = new PowerUp("nameC", Element.WATER, "descB", "", 10);

        // ctor test
        Arrays.fill(charExpected, null);
        Arrays.fill(skillExpected, null);
        assertArrayEquals(charExpected, pa.getCharCard());
        assertArrayEquals(skillExpected, pa.getSkillCard());

        // add test
        pa.addCharacterCard(cA);
        pa.addCharacterCard(cB);
        pa.addCharacterCard(7, cC);
        pa.addSkillCard(sA);
        pa.addSkillCard(sB);
        pa.addSkillCard(7, sC);
        assertEquals(cA, pa.getCharCard(0));
        assertEquals(cB, pa.getCharCard(1));
        assertEquals(cC, pa.getCharCard(7));
        assertEquals(sA, pa.getSkillCard(0));
        assertEquals(sB, pa.getSkillCard(1));
        assertEquals(sC, pa.getSkillCard(7));

        // remove test
        pa.removeCharCard(0);
        pa.removeSkillCard(0);
        assertNull(pa.getCharCard(0));
        assertNull(pa.getSkillCard(0));

        // count test
        assertEquals(2, pa.charCardCount());
        assertEquals(2, pa.charCardCount());
    }
}