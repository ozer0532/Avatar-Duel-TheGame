
import com.avatarduel.player.*;
import com.avatarduel.model.*;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerStatsTest2 {
    @Test
    public void test(){
        PlayerStats PS1 = new PlayerStats();
    
        assertEquals(80, PS1.getHealth());
        assertEquals(0, PS1.getPower(Element.AIR));
        assertEquals(0, PS1.getPower(Element.WATER));
        assertEquals(0, PS1.getPower(Element.EARTH));
        assertEquals(0, PS1.getPower(Element.FIRE));
        assertEquals(0, PS1.getPower(Element.ENERGY));
    }
}