package atl.g52818.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class of Direction
 */
public class DirectionTest {

    @Test
    public void testValues() {
        System.out.println("values");
        Direction[] expResult = {Direction.NORTH, Direction.NORTHEAST,
            Direction.EAST, Direction.SOUTHEAST, Direction.SOUTH,
            Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST};
        Direction[] result = Direction.values();
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "NORTH";
        Direction expResult = Direction.NORTH;
        Direction result = Direction.valueOf(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRow() {
        System.out.println("testGetRow");
        Direction instance = Direction.SOUTH;
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumn() {
        System.out.println("testGetColumn");
        Direction instance = Direction.EAST;
        int expResult = 1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

}
