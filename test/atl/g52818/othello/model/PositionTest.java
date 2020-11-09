package atl.g52818.othello.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test class of Position
 */
public class PositionTest {

    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Position instance = new Position(6, 4);
        int expResult = 6;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        Position instance = new Position(4, 3);
        int expResult = 3;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void equalsTrueMySelf() {
        Position position1 = new Position(-4, 7);
        assertTrue(position1.equals(position1));
        assertTrue(position1.hashCode() == position1.hashCode());
    }

    @Test
    public void equalsTrue() {
        Position position1 = new Position(-4, 7);
        Position position2 = new Position(-4, 7);
        assertTrue(position1.equals(position2));
        assertTrue(position1.hashCode() == position2.hashCode());
    }

    @Test
    public void equalsFalseDifferentRow() {
        Position position1 = new Position(-4, 7);
        Position position2 = new Position(8, 7);
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseDifferentColumn() {
        Position position1 = new Position(2, 7);
        Position position2 = new Position(2, 5);
        assertFalse(position1.equals(position2));
    }
    
    @Test
    public void equalsFalseOtherObject() {
        Position position1 = new Position(2, 96);
        String position2 = "abcd";
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseNull() {
        Position position1 = new Position(2, 96);
        assertFalse(position1.equals(null));
    }

    @Test
    public void testNextNorth() {
        System.out.println("testNextNorth");
        Position position = new Position(1, 1);
        Position expResult = new Position(0, 1);
        Position result = position.next(Direction.NORTH);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextNorthEast() {
        System.out.println("testNextNorthEast");
        Position position = new Position(1, 1);
        Position expResult = new Position(0, 2);
        Position result = position.next(Direction.NORTHEAST);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextEast() {
        System.out.println("testNextWest");
        Position position = new Position(1, 1);
        Position expResult = new Position(1, 2);
        Position result = position.next(Direction.EAST);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextSouthEast() {
        System.out.println("testNextSouthEast");
        Position position = new Position(1, 1);
        Position expResult = new Position(2, 2);
        Position result = position.next(Direction.SOUTHEAST);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextSouth() {
        System.out.println("testNextSouth");
        Position position = new Position(1, 1);
        Position expResult = new Position(2, 1);
        Position result = position.next(Direction.SOUTH);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextSouthWest() {
        System.out.println("testNextSouthWest");
        Position position = new Position(1, 1);
        Position expResult = new Position(2, 0);
        Position result = position.next(Direction.SOUTHWEST);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextWest() {
        System.out.println("testNextWest");
        Position position = new Position(1, 1);
        Position expResult = new Position(1, 0);
        Position result = position.next(Direction.WEST);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextNorthWest() {
        System.out.println("testNextNorthWest");
        Position position = new Position(1, 1);
        Position expResult = new Position(0, 0);
        Position result = position.next(Direction.NORTHWEST);
        assertEquals(expResult, result);
    }

}
