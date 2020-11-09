package atl.g52818.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class of PlayerColor
 */
public class PlayerColorTest {

    @Test
    public void testValues() {
        System.out.println("values");
        PlayerColor[] expResult = {PlayerColor.BLACK, PlayerColor.WHITE};
        PlayerColor[] result = PlayerColor.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "BLACK";
        PlayerColor expResult = PlayerColor.BLACK;
        PlayerColor result = PlayerColor.valueOf(name);
        assertEquals(expResult, result);
    }

}
