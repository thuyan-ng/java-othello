package atl.g52818.othello.model;

import static atl.g52818.othello.model.PlayerColor.WHITE;
import static atl.g52818.othello.model.PlayerColor.BLACK;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class of Game
 */
public class GameTest {

    public final Board defaultBoard = new Board();

    @Before
    public void setUp() throws Exception {
        defaultBoard.put(new Piece(1, WHITE), new Position(3, 3));
        defaultBoard.put(new Piece(1, BLACK), new Position(3, 4));
        defaultBoard.put(new Piece(1, WHITE), new Position(4, 4));
        defaultBoard.put(new Piece(1, BLACK), new Position(4, 3));
    }

    // -- getBoard()
    @Test
    public void testGetBoardWhenGameBegin() {
        System.out.println("testGetBoardWhenGameBegin");
        Game instance = new Game();
        instance.initialize();
        assertEquals(defaultBoard, instance.getBoard());
    }

    // -- getCurrent()
    @Test
    public void testGetCurrentBlack() {
        System.out.println("testGetCurrent");
        Game instance = new Game();
        instance.initialize();
        assertEquals(BLACK, instance.getCurrent().getColor());
    }

    @Test
    public void testGetCurrentWhite() {
        System.out.println("testGetCurrentWhite");
        Game instance = new Game();
        instance.initialize();
        instance.swapPlayers();
        assertEquals(WHITE, instance.getCurrent().getColor());
    }

    // -- getOpponent()
    @Test
    public void testGetOpponentBlack() {
        System.out.println("testGetOpponentBlack");
        Game instance = new Game();
        instance.initialize();
        instance.swapPlayers();
        assertEquals(BLACK, instance.getOpponent().getColor());
    }

    @Test
    public void testGetOpponentWhite() {
        System.out.println("testGetOpponentWhite");
        Game instance = new Game();
        instance.initialize();
        assertEquals(WHITE, instance.getOpponent().getColor());
    }

    // -- initialize()
    @Test
    public void testInitializeBoard() {
        System.out.println("testInitializeBoard");
        Game instance = new Game();
        instance.initialize();
        assertEquals(defaultBoard, instance.getBoard());
    }

    @Test
    public void testInitializeBlackBag() {
        System.out.println("testInitializeBlackBag");
        Game instance = new Game();
        instance.initialize();
        assertFalse(instance.getCurrent().getPieces().isEmpty());
    }

    @Test
    public void testInitializeWhiteBag() {
        System.out.println("testInitializeWhiteBag");
        Game instance = new Game();
        instance.initialize();
        instance.swapPlayers();
        assertFalse(instance.getCurrent().getPieces().isEmpty());
    }

    // -- getPositions()
    @Test
    public void testGetPositionsWhenInitialize() {
        System.out.println("testGetPositionsWhenInitialize");
        Game instance = new Game();
        instance.initialize();

        List<Position> expResult = new ArrayList<>();
        expResult.add(new Position(5, 4));
        expResult.add(new Position(3, 2));
        expResult.add(new Position(2, 3));
        expResult.add(new Position(4, 5));

        assertEquals(expResult, instance.getPositions(instance.getCurrent()));
    }

    @Test
    public void testGetPositions2ConsecutiveWhitePieces() {
        System.out.println("testGetPositions2ConsecutiveWhitePieces");
        Game instance = new Game();

        instance.getBoard().put(new Piece(0, WHITE), new Position(3, 2));
        instance.getBoard().put(new Piece(0, WHITE), new Position(3, 3));
        instance.getBoard().put(new Piece(0, BLACK), new Position(3, 4));

        List<Position> expResult = new ArrayList<>();
        expResult.add(new Position(3, 1));

        assertEquals(expResult, instance.getPositions(instance.getCurrent()));
    }

    @Test
    public void testGetPositionsNextIsWhiteNextNextIsBlack() {
        System.out.println("testGetPositionsNextIsWhiteNextNextIsBlack");
        Game instance = new Game();

        instance.getBoard().put(new Piece(0, BLACK), new Position(3, 2));
        instance.getBoard().put(new Piece(0, WHITE), new Position(3, 3));
        instance.getBoard().put(new Piece(0, BLACK), new Position(3, 4));

        assertEquals(0, instance.getPositions(instance.getCurrent()).size());
    }

    @Test
    public void testGetPositionsNoPositionLeft() {
        System.out.println("testGetPositionsNoPositionLeft");
        Game instance = new Game();

        instance.getBoard().put(new Piece(0, WHITE), new Position(0, 0));
        instance.getBoard().put(new Piece(0, BLACK), new Position(0, 1));

        assertEquals(0, instance.getPositions(instance.getCurrent()).size());
    }

    // -- hasMoves()
    @Test
    public void testHasMovesTrue() {
        System.out.println("testHasMovesTrue");
        Game instance = new Game();
        instance.initialize();
        assertTrue(instance.hasMoves(instance.getCurrent()));
    }

    @Test
    public void testHasMovesFalse() {
        System.out.println("testHasMovesFalse");
        Game instance = new Game();

        instance.getBoard().put(new Piece(0, WHITE), new Position(0, 0));
        instance.getBoard().put(new Piece(0, BLACK), new Position(0, 1));

        assertFalse(instance.hasMoves(instance.getCurrent()));
    }

    // -- isOver()
    @Test
    public void testIsOverWhenGameBegin() {
        System.out.println("testIsOverWhenGameBegin");
        Game instance = new Game();
        instance.initialize();
        assertFalse(instance.isOver());
    }

    @Test
    public void testIsOverWhenNoPieceLeft() {
        System.out.println("testIsOverWhenNoPieceLeft");
        Game instance = new Game();
        instance.initialize();

        instance.getCurrent().getPieces().clear();
        instance.swapPlayers();
        instance.getCurrent().getPieces().clear();

        assertTrue(instance.isOver());
    }

    @Test
    public void testIsOverWhenNobodyCanMoveButStillHavePieces() {
        System.out.println("testIsOverWhenNobodyCanMoveButStillHavePieces");
        Game instance = new Game();
        instance.getCurrent().getPieces().add(new Piece(0, BLACK));
        instance.getOpponent().getPieces().add(new Piece(0, WHITE));

        // On row 0, placing a black piece then a white piece and so on
        for (int i = 0; i < instance.getBoard().getWidth(); i++) {
            if (i % 2 == 0) {
                instance.getBoard().put(new Piece(0, BLACK), new Position(0, i));
            } else {
                instance.getBoard().put(new Piece(0, WHITE), new Position(0, i));
            }
        }
        assertTrue(instance.isOver());
    }

    // -- swapPlayers()
    @Test
    public void testSwapPlayers() {
        System.out.println("testSwapPlayers");
        Game instance = new Game();
        instance.initialize();
        instance.swapPlayers();
        assertEquals(WHITE, instance.getCurrent().getColor());
    }

    @Test
    public void testSwapPlayersWhenIsOver() {
        System.out.println("testSwapPlayersWhenIsOver");
        Game instance = new Game();
        instance.initialize();

        instance.getCurrent().getPieces().clear();
        instance.getOpponent().getPieces().clear();
        instance.swapPlayers();

        assertEquals(BLACK, instance.getCurrent().getColor());
    }

    // -- play()
    @Test
    public void testPlayPieceOnBoard() {
        System.out.println("testPlayPieceOnBoard");
        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(2, 3));
        assertEquals(instance.getBoard().getPieceAt(new Position(2, 3)).getColor(),
                instance.getOpponent().getColor());
    }

    @Test
    public void testPlayPieceWasRemovedFromBag() {
        System.out.println("testPlayPieceWasRemovedFromBag");
        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(2, 3));
        assertEquals(29, instance.getOpponent().getPieces().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayWhenPositionOutsideTheBoard() {
        System.out.println("testPlayWhenPositionOutsideTheBoard");
        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(-2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayWhenNoWayToCaptureEnnemyPiece() {
        System.out.println("testPlayWhenNoWayToCaptureEnnemyPiece");
        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(0, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayWhenPositionIsNotFree() {
        System.out.println("testPlayWhenPositionIsNotFree");
        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(3, 3));
    }

    // -- flipAndCount()
    @Test
    public void testFlipAllCapture1PieceEast() {
        System.out.println("testFlipAllCapture1PieceEast");
        Game instance = new Game();
        instance.initialize();
        int flipped = instance.flipAndCount(new Position(2, 3));
        assertEquals(BLACK, instance.getBoard().getPieceAt(new Position(3, 3)).getColor());
        assertEquals(flipped, 1);
    }

    @Test
    public void testFlipAllCapture2PiecesEast() {
        System.out.println("testFlipAllCapture2PiecesEast");

        Game instance = new Game();
        instance.initialize();

        instance.getBoard().put(new Piece(0, WHITE), new Position(3, 2));
        int flipped = instance.flipAndCount(new Position(3, 1));

        assertEquals(BLACK, instance.getBoard().getPieceAt(new Position(3, 3)).getColor());
        assertEquals(BLACK, instance.getBoard().getPieceAt(new Position(3, 2)).getColor());
        assertEquals(flipped, 2);
    }

    @Test
    public void testFlipAllCapture2PiecesSouthEast() {
        System.out.println("testFlipAllCapture2PiecesSouthEast");

        Game instance = new Game();
        instance.initialize();
        instance.getBoard().put(new Piece(0, WHITE), new Position(4, 2));
        instance.getBoard().put(new Piece(0, BLACK), new Position(2, 2));
        instance.play(new Position(5, 5));

        assertEquals(BLACK, instance.getBoard().getPieceAt(new Position(3, 3)).getColor());
        assertEquals(BLACK, instance.getBoard().getPieceAt(new Position(4, 4)).getColor());
        assertEquals(instance.getLastMove().getFlipped(), (Integer) 2);
    }

    // -- canCapture()
    @Test
    public void testCanCaptureTrue() {
        System.out.println("testCanCaptureTrue");
        Game instance = new Game();
        instance.initialize();
        assertTrue(instance.canCapture(new Position(3, 2), Direction.EAST));
    }

    @Test
    public void testCanCaptureWrongPositionRightDirection() {
        System.out.println("testCanCaptureWrongPositionRightDirection");
        Game instance = new Game();
        instance.initialize();
        assertFalse(instance.canCapture(new Position(3, 5), Direction.EAST));
    }

    @Test
    public void testCanCaptureRightPositionWrongDirection() {
        System.out.println("testCanCaptureRightPositionWrongDirection");
        Game instance = new Game();
        instance.initialize();
        assertFalse(instance.canCapture(new Position(3, 2), Direction.SOUTH));
    }

    @Test
    public void testCanCaptureNorthWestDirection() {
        System.out.println("testCanCaptureNorthWestDirection");
        Game instance = new Game();
        instance.initialize();
        assertFalse(instance.canCapture(new Position(5, 4), Direction.NORTHWEST));
    }

    @Test
    public void testCanCaptureNorthDirection() {
        System.out.println("testCanCaptureNorthDirection");
        Game instance = new Game();
        instance.initialize();
        assertTrue(instance.canCapture(new Position(5, 4), Direction.NORTH));
    }

    // -- updateScore()
    @Test
    public void testUpdateScoreInitialize() {
        System.out.println("testUpdateScoreInitialize");

        Game instance = new Game();
        instance.initialize();
        instance.updateScores();

        assertEquals(2, instance.getCurrent().getScore());
        assertEquals(2, instance.getOpponent().getScore());
    }

    @Test
    public void testUpdateScoreDifferentScore() {
        System.out.println("testUpdateScoreDifferentScore");

        Game instance = new Game();
        instance.initialize();
        instance.getBoard().put(new Piece(1, BLACK), new Position(3, 2));
        instance.updateScores();

        assertEquals(3, instance.getCurrent().getScore());
        assertEquals(2, instance.getOpponent().getScore());
    }

    // -- checkBonusSquares()
    @Test
    public void testCheckBonusSquaresBlack() {
        System.out.println("testCheckBonusSquaresBlack");
        Game instance = new Game();
        instance.initialize();

        Position bonus = new Position(4, 5);
        instance.setBonusSquares(bonus);
        instance.getBoard().put(new Piece(1, BLACK), bonus);

        instance.updateScores();

        assertEquals(6, instance.getCurrent().getScore());
        assertEquals(2, instance.getOpponent().getScore());
    }

    @Test
    public void testCheckBonusSquaresWhite() {
        System.out.println("testCheckBonusSquaresWhite");
        Game instance = new Game();
        instance.initialize();

        Position bonus = new Position(3, 5);
        instance.setBonusSquares(bonus);
        instance.getBoard().put(new Piece(1, WHITE), bonus);

        instance.updateScores();

        assertEquals(6, instance.getOpponent().getScore());
        assertEquals(2, instance.getCurrent().getScore());
    }

    // -- getWinner()
    @Test
    public void testGetWinnerCurrent() {
        System.out.println("testGetWinnerCurrent");

        Game instance = new Game();
        instance.initialize();
        instance.getBoard().put(new Piece(1, BLACK), new Position(3, 2));
        instance.updateScores();

        assertEquals(instance.getCurrent(), instance.getWinner());
    }

    @Test
    public void testGetWinnerOpponent() {
        System.out.println("testGetWinnerOpponent");

        Game instance = new Game();
        instance.initialize();
        instance.getBoard().put(new Piece(1, WHITE), new Position(3, 2));
        instance.updateScores();

        assertEquals(instance.getOpponent(), instance.getWinner());
    }

    // -- isSurrender()
    @Test
    public void testIsSurrender() {
        System.out.println("testIsSurrender");

        Game instance = new Game();
        instance.initialize();
        assertFalse(instance.isSurrender());
    }

    // -- surrender()
    @Test
    public void testIsSurrenderTrue() {
        System.out.println("testIsSurrender");

        Game instance = new Game();
        instance.initialize();
        instance.surrender();
        assertTrue(instance.isSurrender());
    }

    // -- reset()
    @Test
    public void testResetBoard() {
        System.out.println("testResetBoard");

        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(2, 3));
        instance.reset();

        assertEquals(instance.getBoard(), defaultBoard);
    }

    @Test
    public void testResetPlayer() {
        System.out.println("testResetPlayer");

        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(2, 3));
        instance.reset();

        assertEquals(instance.getCurrent().getPieces().size(), 30);
    }

    @Test
    public void testResetSurrender() {
        System.out.println("testResetSurrender");

        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(2, 3));
        instance.reset();

        assertFalse(instance.isSurrender());
    }

    @Test
    public void testResetStaticId() {
        System.out.println("testResetStaticId");

        Game instance = new Game();
        instance.initialize();
        instance.play(new Position(2, 3));
        instance.reset();

        assertEquals(instance.getLastMove().getId(), 0);
    }

    // -- pass()
    @Test
    public void testPass() {
        System.out.println("testPass");

        Game instance = new Game();
        instance.initialize();
        instance.pass();

        assertEquals(instance.getLastMove().getAction(), "Passe son tour");
        assertEquals(instance.getCurrent().getColor(), PlayerColor.WHITE);
    }

    // -- setPlayersNames()
    @Test
    public void testSetPlayersNames() {
        System.out.println("testSetPlayersNames");

        Game instance = new Game();
        instance.initialize();
        instance.setPlayersNames("Jean", "Marc");

        assertEquals(instance.getCurrent().getName(), "Jean");
        assertEquals(instance.getOpponent().getName(), "Marc");
    }

}
