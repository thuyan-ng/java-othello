package atl.g52818.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class of Board
 */
public class BoardTest {

    // -- getWidth()
    @Test
    public void testGetWidth() {
        System.out.println("testGetWidth");
        Board instance = new Board();
        assertEquals(8, instance.getWidth());
    }

    // -- getHeight()
    @Test
    public void testGetHeight() {
        System.out.println("testGetHeight");
        Board instance = new Board();
        assertEquals(8, instance.getHeight());
    }

    // -- getPieceAt()
    @Test(expected = IllegalArgumentException.class)
    public void testGetPieceAtWhenPositionOutside() {
        System.out.println("testGetPieceAtWhenPositionOutside");

        Position position = new Position(-1, 2);
        Board instance = new Board();
        instance.getPieceAt(position);
    }

    @Test
    public void testGetPieceAtWhenSquareFill() {
        System.out.println("testGetPieceAtWhenSquareFill");

        Board instance = new Board();
        Position position = new Position(0, 0);
        Piece piece = new Piece(1, PlayerColor.BLACK);
        instance.put(piece, position);
        Piece expResult = new Piece(1, PlayerColor.BLACK);

        assertEquals(expResult, instance.getPieceAt(position));
    }

    @Test
    public void testGetPieceAtWhenSquareEmpty() {
        System.out.println("testGetPieceAtWhenSquareEmpty");

        Board instance = new Board();
        Position position = new Position(0, 0);
        Piece expResult = instance.getPieceAt(position);
        assertNull(expResult);
    }

    // -- isInside()
    @Test(expected = NullPointerException.class)
    public void testIsInsideWhenPositionIsNull() {
        System.out.println("testIsInsideWhenPositionIsNull");

        Board instance = new Board();
        instance.isInside(null);
    }

    @Test
    public void testIsInsideWhenCornerUpLeft() {
        System.out.println("testIsInsideWhenCornerUpLeft");

        Position position = new Position(0, 0);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenCornerUpRight() {
        System.out.println("testIsInsideWhenCornerUpRight");

        Position position = new Position(0, 7);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenCornerDownleft() {
        System.out.println("testIsInsideWhenCornerDownleft");

        Position position = new Position(7, 0);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenCornerDownRight() {
        System.out.println("testIsInsideWhenCornerDownRight");

        Position position = new Position(7, 7);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenBoundaryUp() {
        System.out.println("testIsInsideWhenBoundaryUp");

        Position position = new Position(0, 1);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenBoundaryDown() {
        System.out.println("testIsInsideWhenBoundaryDown");

        Position position = new Position(7, 2);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenBoundaryLeft() {
        System.out.println("testIsInsideWhenBoundaryLeft");

        Position position = new Position(1, 0);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenBoundaryRight() {
        System.out.println("testIsInsideWhenBoundaryRight");

        Position position = new Position(3, 7);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryUp() {
        System.out.println("testIsInsideWhenOutsideBoundaryUp");

        Position position = new Position(-1, 1);
        Board instance = new Board();
        assertFalse(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryDown() {
        System.out.println("testIsInsideWhenOutsideBoundaryDown");

        Position position = new Position(9, 2);
        Board instance = new Board();
        assertFalse(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryLeft() {
        System.out.println("testIsInsideWhenOutsideBoundaryLeft");

        Position position = new Position(1, -1);
        Board instance = new Board();
        assertFalse(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryRight() {
        System.out.println("testIsInsideWhenOutsideBoundaryRight");

        Position position = new Position(3, 9);
        Board instance = new Board();
        assertFalse(instance.isInside(position));
    }

    @Test
    public void testIsInsideWhenInside() {
        System.out.println("testIsInsideWhenInside");

        Position position = new Position(2, 1);
        Board instance = new Board();
        assertTrue(instance.isInside(position));
    }

    // -- put()
    @Test
    public void testPutOnePiece() {
        System.out.println("testPutOnePiece");

        Piece piece = new Piece(2, PlayerColor.BLACK);
        Position position = new Position(0, 0);
        Board instance = new Board();
        instance.put(piece, position);

        assertEquals(piece, instance.getPieceAt(position));
    }

    @Test(expected = NullPointerException.class)
    public void testPutWhenPieceIsNull() {
        System.out.println("testPutWhenPieceIsNull");

        Piece piece = null;
        Position position = new Position(2, 2);
        Board instance = new Board();
        instance.put(piece, position);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPuWhenPositionIsOutside() {
        System.out.println("testPuWhenPositionIsOutside");

        Piece piece = new Piece(1, PlayerColor.BLACK);
        Position position = new Position(9, 2);
        Board instance = new Board();
        instance.put(piece, position);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPuWhenPositionIsNotFree() {
        System.out.println("testPuWhenPositionIsNotFree");

        Piece piece = new Piece(1, PlayerColor.BLACK);
        Position position = new Position(4, 2);
        Board instance = new Board();

        // putting a first piece on square at (4,2)
        instance.put(new Piece(0, PlayerColor.BLACK), position);

        // putting a second piece on an already occupied square
        instance.put(piece, position);
    }

    // -- isFree()
    @Test
    public void testIsFreeTrue() {
        System.out.println("testIsFreeTrue");

        Position position = new Position(0, 0);
        Board instance = new Board();
        assertTrue(instance.isFree(position));
    }

    @Test
    public void testIsFreeFalse() {
        System.out.println("testIsFreeTrue");

        Position position = new Position(0, 0);
        Board instance = new Board();
        instance.put(new Piece(0, PlayerColor.BLACK), position);
        assertFalse(instance.isFree(position));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFreeWhenPositionOutside() {
        System.out.println("testIsFreeWhenPositionOutside");

        Position position = new Position(-1, 2);
        Board instance = new Board();
        instance.isFree(position);
    }

    // -- isMyOwn()
    @Test
    public void testIsMyOwnTrue() {
        System.out.println("testIsMyOwnTrue");

        Position position = new Position(0, 0);
        Board instance = new Board();
        instance.put(new Piece(0, PlayerColor.BLACK), position);
        assertTrue(instance.isMyOwn(position, PlayerColor.BLACK));
    }

    @Test
    public void testIsMyOwnFalse() {
        System.out.println("testIsMyOwnFalse");

        Position position = new Position(0, 0);
        Board instance = new Board();
        instance.put(new Piece(0, PlayerColor.WHITE), position);
        assertFalse(instance.isMyOwn(position, PlayerColor.BLACK));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsMyOwnhenPositionOutside() {
        System.out.println("testIsMyOwnWhenPositionOutside");

        Position position = new Position(-1, 2);
        Board instance = new Board();
        instance.isMyOwn(position, PlayerColor.BLACK);
    }

    // -- getTakenSquares()
    @Test
    public void testGetTakenSquare() {
        System.out.println("testGetTakenSquare");

        Board instance = new Board();
        Player player = new Player(PlayerColor.BLACK, "");
        Position position = new Position(2, 1);
        // putting a black piece on the board
        instance.put(new Piece(0, PlayerColor.BLACK), position);

        assertTrue(instance.getTakenSquares(player).contains(position));
    }

    @Test
    public void testGetTakenSquareBothColorsOnTheBoard() {
        System.out.println("testGetTakenSquareBothColorsOnTheBoard");

        Board instance = new Board();
        Player player = new Player(PlayerColor.BLACK,"");
        Position position = new Position(2, 1);

        instance.put(new Piece(0, PlayerColor.BLACK), position);

        // putting a white piece on a square at another position
        instance.put(new Piece(0, PlayerColor.WHITE), new Position(3, 0));

        assertTrue(instance.getTakenSquares(player).contains(position));
    }

    // -- score()
    @Test
    public void testScore() {
        System.out.println("testScore");
        Board instance = new Board();
        Player player = new Player(PlayerColor.BLACK,"");
        instance.put(new Piece(0, player.getColor()), new Position(2, 1));
        instance.put(new Piece(3, player.getColor()), new Position(1, 1));
        instance.put(new Piece(3, PlayerColor.WHITE), new Position(0, 1));
        assertEquals(3, instance.score(player));
    }

    // -- emptySquares()
    @Test
    public void testEmptySquares() {
        System.out.println("testEmptySquares");
        Board instance = new Board();
        instance.put(new Piece(0, PlayerColor.BLACK), new Position(2, 1));
        instance.put(new Piece(3, PlayerColor.BLACK), new Position(1, 1));
        instance.put(new Piece(3, PlayerColor.WHITE), new Position(0, 1));
        assertEquals(61, instance.emptySquares());
    }

    @Test
    public void testEmptySquaresWhenNoEmptySquare() {
        System.out.println("testEmptySquaresWhenNoEmptySquare");
        Board instance = new Board();
        for (int i = 0; i < instance.getWidth(); i++) {
            for (int j = 0; j < instance.getHeight(); j++) {
                instance.put(new Piece(0, PlayerColor.BLACK), new Position(i, j));
            }
        }
        assertEquals(0, instance.emptySquares());
    }
    
        
    // -- occupiedSquares()
    @Test
    public void testOccupiedSquares(){
        System.out.println("testOccupiedSquares");
        Board instance = new Board();
        instance.put(new Piece(0, PlayerColor.BLACK), new Position(0,0));
        assertEquals(instance.occupiedSquares(), 1, 0.0);
    }
    
    // -- sizeOfTakenSquares()
    @Test
    public void testSizeOfTakenSquares(){
        System.out.println("testSizeOfTakenSquares");
        
        Player current = new Player(PlayerColor.BLACK,"");
        Board instance = new Board();
        
        instance.put(new Piece(0, PlayerColor.BLACK), new Position(0,0));
        assertEquals(instance.sizeOfTakenSquares(current), 1);
    }
    
    // -- hasCode() & equals()
    
    @Test
    public void equalsTrueMySelf() {
        Board board = new Board();
        assertTrue(board.equals(board));
        assertTrue(board.hashCode() == board.hashCode());
    }
    
    @Test
    public void equalsTrue() {
        Board board1 = new Board();
        Board board2 = new Board();
        assertTrue(board1.equals(board2));
        assertTrue(board1.hashCode() == board2.hashCode());
    }
    
    @Test
    public void equalsFalse() {
        Board board1 = new Board();
        Board board2 = new Board();
        board2.put(new Piece(0, PlayerColor.BLACK),new Position(3,3));
        assertFalse(board1.equals(board2));
    }
    
    @Test
    public void equalsFalseOtherObject() {
        Board board1 = new Board();
        String string = "abcd";
        assertFalse(board1.equals(string));
    }

    @Test
    public void equalsFalseNull() {
        Board board1 = new Board();
        assertFalse(board1.equals(null));
    }

}
