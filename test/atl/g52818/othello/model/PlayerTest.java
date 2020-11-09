package atl.g52818.othello.model;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test class of Player
 */
public class PlayerTest {

    @Test(expected = NullPointerException.class)
    public void testConstructPlayerColorIsNull() {
        System.out.println("testConstructPlayerColorIsNull");
        Player instance = new Player(null, null);
    }
    
    @Test
    public void testGetName(){
        System.out.println("testGetName");
        Player instance = new Player(PlayerColor.BLACK,"Jean");
        assertEquals(instance.getName(), "Jean");
    }
    
    @Test
    public void testSetName(){
        System.out.println("testSetName");
        Player instance = new Player(PlayerColor.BLACK,"");
        instance.setName("Jean");
        assertEquals(instance.getName(), "Jean");
    }

    @Test
    public void testGetPiecesWhenStockEmpty() {
        System.out.println("testGetPiecesWhenStockEmpty");
        Player instance = new Player(PlayerColor.BLACK,"");
        List<Piece> result = instance.getPieces();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPiecesWhenStockFill() {
        System.out.println("testGetPiecesWhenStockFill");

        Player instance = new Player(PlayerColor.BLACK,"");
        instance.addPiece(new Piece(0, PlayerColor.BLACK));
        instance.addPiece(new Piece(0, PlayerColor.BLACK));

        List<Piece> result = instance.getPieces();
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAddPiece() {
        System.out.println("testAddPiece");
        Player instance = new Player(PlayerColor.BLACK,"");
        Piece p = new Piece(0, PlayerColor.BLACK);
        instance.addPiece(p);
        assertTrue(instance.getPieces().contains(p));
    }

    @Test
    public void testGetColorBlack() {
        System.out.println("testGetColorBlack");
        Player instance = new Player(PlayerColor.BLACK,"");
        PlayerColor expResult = PlayerColor.BLACK;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColorWhite() {
        System.out.println("testGetColorWhite");
        Player instance = new Player(PlayerColor.WHITE,"");
        PlayerColor expResult = PlayerColor.WHITE;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetScore() {
        System.out.println("testGetScore");
        Player instance = new Player(PlayerColor.WHITE,"");
        instance.setScore(2);
        assertEquals(2, instance.getScore());
    }

    @Test
    public void equalsTrueMySelf() {
        Player player1 = new Player(PlayerColor.BLACK,"");
        assertTrue(player1.equals(player1));
        assertTrue(player1.hashCode() == player1.hashCode());
    }

    @Test
    public void equalsTrueNoPiece() {
        Player player1 = new Player(PlayerColor.BLACK,"");
        Player player2 = new Player(PlayerColor.BLACK,"");
        assertTrue(player1.equals(player2));
        assertTrue(player1.hashCode() == player2.hashCode());
    }

    @Test
    public void equalsTrueWithPiece() {
        Player player1 = new Player(PlayerColor.BLACK,"");
        Player player2 = new Player(PlayerColor.BLACK,"");
        player1.addPiece(new Piece(1, PlayerColor.BLACK));
        player2.addPiece(new Piece(1, PlayerColor.BLACK));
        assertTrue(player1.equals(player2));
        assertTrue(player1.hashCode() == player2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Player player1 = new Player(PlayerColor.BLACK,"");
        Player player2 = new Player(PlayerColor.WHITE,"");
        assertFalse(player1.equals(player2));
    }

    @Test
    public void equalsFalseDifferentPiece() {
        Player player1 = new Player(PlayerColor.BLACK,"");
        player1.addPiece(new Piece(1, PlayerColor.BLACK));

        Player player2 = new Player(PlayerColor.BLACK,"");
        player2.addPiece(new Piece(2, PlayerColor.BLACK));

        assertFalse(player1.equals(player2));
    }
    
    @Test
    public void equalsFalseOtherObject() {
        Player player1 = new Player(PlayerColor.BLACK,"");
        String player2 = "abcd";
        assertFalse(player1.equals(player2));
    }

    @Test
    public void equalsFalseNull() {
        Player player1 = new Player(PlayerColor.BLACK,"");
        assertFalse(player1.equals(null));
    }

    @Test
    public void testRemoveWhenListEmpty() {
        System.out.println("testRemoveWhenListEmpty");
        Player player = new Player(PlayerColor.BLACK,"");
        Piece piece = new Piece(0, PlayerColor.BLACK);
        player.addPiece(piece);
        player.remove(piece);
        assertEquals(player.getPieces().size(), 0);
    }

    @Test
    public void testRemoveWhenListNotEmpty() {
        System.out.println("testRemoveWhenListNotEmpty");
        Player instance = new Player(PlayerColor.BLACK,"");
        Piece p1 = new Piece(0, PlayerColor.BLACK);
        Piece p2 = new Piece(1, PlayerColor.BLACK);

        instance.addPiece(p1);
        instance.addPiece(p2);
        instance.remove(p1);

        assertFalse(instance.getPieces().contains(p1));
    }

    @Test
    public void testInitializeBag() {
        System.out.println("testInitializeBag");
        Player instance = new Player(PlayerColor.BLACK,"");
        instance.initializeBag();
        assertEquals(30, instance.getPieces().size());
    }

    @Test
    public void testDrawPiece() {
        System.out.println("testDrawPiece");
        Player instance = new Player(PlayerColor.BLACK,"");
        instance.initializeBag();
        Piece piece = instance.drawPiece();
        assertTrue(instance.getPieces().contains(piece));
    }
}
