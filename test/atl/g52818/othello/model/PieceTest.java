package atl.g52818.othello.model;

import static atl.g52818.othello.model.PlayerColor.BLACK;
import static atl.g52818.othello.model.PlayerColor.WHITE;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test class of Piece
 */
public class PieceTest {

    @Test
    public void testConstructPieceWhenRankIsZero() {
        System.out.println("testConstructPieceWhenRankIsZero");
        Piece instance = new Piece(0, BLACK);
        assertEquals(BLACK, instance.getColor());
        assertEquals(0, instance.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPieceWhenRankIsNegative() {
        System.out.println("testConstructPieceWhenRankIsNegative");
        Piece instance = new Piece(-1, BLACK);
    }

    @Test
    public void testConstructPieceWhenRankIsOne() {
        System.out.println("testConstructPieceWhenRankIsOne");
        Piece instance = new Piece(1, BLACK);
        assertEquals(BLACK, instance.getColor());
        assertEquals(1, instance.getValue());
    }

    @Test
    public void testGetValue() {
        System.out.println("testGetValue");
        Piece instance = new Piece(0, BLACK);
        assertEquals(0, instance.getValue());
    }

    @Test
    public void testGetColorBlack() {
        System.out.println("testGetColorBlack");
        Piece instance = new Piece(0, BLACK);
        PlayerColor expResult = BLACK;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColorWhite() {
        System.out.println("testGetColorWhite");
        Piece instance = new Piece(1, WHITE);
        PlayerColor expResult = WHITE;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    @Test
    public void testFlipWhiteToBlack() {
        System.out.println("testFlip");
        Piece instance = new Piece(1, WHITE);
        instance.flip();
        assertEquals(BLACK, instance.getColor());
    }

    @Test
    public void testFlipBlackToWhite() {
        System.out.println("testFlip");
        Piece instance = new Piece(1, BLACK);
        instance.flip();
        assertEquals(WHITE, instance.getColor());
    }

    @Test
    public void equalsTrueMySelf() {
        Piece piece1 = new Piece(1, BLACK);
        assertTrue(piece1.equals(piece1));
        assertTrue(piece1.hashCode() == piece1.hashCode());
    }

    @Test
    public void equalsTrue() {
        Piece piece1 = new Piece(1, BLACK);
        Piece piece2 = new Piece(1, BLACK);
        assertTrue(piece1.equals(piece2));
        assertTrue(piece1.hashCode() == piece2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Piece piece1 = new Piece(1, BLACK);
        Piece piece2 = new Piece(1, WHITE);
        assertFalse(piece1.equals(piece2));
    }

    @Test
    public void equalsFalseDifferentRank() {
        Piece piece1 = new Piece(1, BLACK);
        Piece piece2 = new Piece(2, WHITE);
        assertFalse(piece1.equals(piece2));
    }
    
    @Test
    public void equalsFalseOtherObject() {
        Piece piece = new Piece(0, PlayerColor.BLACK);
        String string = "abcd";
        assertFalse(piece.equals(string));
    }

    @Test
    public void equalsFalseNull() {
        Piece piece = new Piece(0, PlayerColor.BLACK);
        assertFalse(piece.equals(null));
    }

}
