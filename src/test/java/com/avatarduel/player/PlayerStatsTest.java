
import com.avatarduel.player.*;
import com.avatarduel.model.*;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerStatsTest {
    @Test
    public void test(){
        PlayerStats PS1 = new PlayerStats();
    
        assertEquals(80, PS1.getHealth());
        assertEquals(0, PS1.getPower(Element.AIR));
        assertEquals(0, PS1.getPower(Element.WATER));
        assertEquals(0, PS1.getPower(Element.EARTH));
        assertEquals(0, PS1.getPower(Element.FIRE));
        assertEquals(0, PS1.getPower(Element.ENERGY));
        assertEquals(0, PS1.getRemainingPower(Element.AIR));
        assertEquals(0, PS1.getRemainingPower(Element.WATER));
        assertEquals(0, PS1.getRemainingPower(Element.EARTH));
        assertEquals(0, PS1.getRemainingPower(Element.FIRE));
        assertEquals(0, PS1.getRemainingPower(Element.ENERGY));
        assertFalse(PS1.getPlayedLandThisRound());

        PS1.setHealth(100);
        PS1.setPower(Element.AIR, 3);
        PS1.setPower(Element.WATER, 3);
        PS1.setPower(Element.EARTH, 3);
        PS1.setPower(Element.FIRE, 3);
        PS1.setPower(Element.ENERGY, 3);
        PS1.setRemainingPower(Element.AIR, 2);
        PS1.setRemainingPower(Element.WATER, 2);
        PS1.setRemainingPower(Element.EARTH, 2);
        PS1.setRemainingPower(Element.FIRE, 2);
        PS1.setRemainingPower(Element.ENERGY, 2);

        assertEquals(100, PS1.getHealth());
        assertEquals(3, PS1.getPower(Element.AIR));
        assertEquals(3, PS1.getPower(Element.WATER));
        assertEquals(3, PS1.getPower(Element.EARTH));
        assertEquals(3, PS1.getPower(Element.FIRE));
        assertEquals(3, PS1.getPower(Element.ENERGY));
        assertEquals(2, PS1.getRemainingPower(Element.AIR));
        assertEquals(2, PS1.getRemainingPower(Element.WATER));
        assertEquals(2, PS1.getRemainingPower(Element.EARTH));
        assertEquals(2, PS1.getRemainingPower(Element.FIRE));
        assertEquals(2, PS1.getRemainingPower(Element.ENERGY));

        PS1.incrementPower(Element.AIR);
        assertEquals(4, PS1.getPower(Element.AIR));

        PS1.usePower(Element.AIR, 2);
        assertEquals(1, PS1.getRemainingPower(Element.AIR));

        PS1.resetStats();
        assertEquals(PS1.getPower(Element.AIR), PS1.getRemainingPower(Element.AIR));
        assertEquals(PS1.getPower(Element.WATER), PS1.getRemainingPower(Element.WATER));
        assertEquals(PS1.getPower(Element.EARTH), PS1.getRemainingPower(Element.EARTH));
        assertEquals(PS1.getPower(Element.FIRE), PS1.getRemainingPower(Element.FIRE));
        assertEquals(PS1.getPower(Element.ENERGY), PS1.getRemainingPower(Element.ENERGY));
    }
}