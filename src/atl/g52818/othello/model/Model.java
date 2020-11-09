package atl.g52818.othello.model;

import java.util.List;

/**
 * This interface represents the model of Othello
 */
public interface Model {

    /**
     * Initializes the game by : putting 2 black and 2 white pieces on default
     * position and adding 30 pieces in each player's list of pieces.
     *
     * For each player : 1 piece of value 3, 10 pieces of value 2, 20 pieces of
     * value 1, 1 piece of value 0
     */
    void initialize();

    /**
     * For each piece on the board and for all directions, checks throughout
     * ennemy pieces until a free position is found. The position is added to
     * the list of possible positions that the current player stil has.
     *
     * @param player the player
     * @return a list of all possible moves that the player still has.
     */
    List<Position> getPositions(Player player);

    /**
     * Getter of Board
     *
     * @return the board
     */
    Board getBoard();

    /**
     * Getter of current player
     *
     * @return the current player
     */
    Player getCurrent();

    /**
     * Getter of opponent player
     *
     * @return the opponent player
     */
    Player getOpponent();

    /**
     * Checks if the list of all possible moves of the given player in parameter
     * is empty or not.
     *
     * @param player the player
     * @return true if the current player can still make a move, false otherwise
     */
    boolean hasMoves(Player player);

    /**
     * Checks if the game is over or not. It is over if both players can't move,
     * or if both players don't have any piece left.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isOver();

    /**
     * Getter of surrender
     *
     * @return true if the player has surrendered, false otherwise
     */
    boolean isSurrender();

    /**
     * Puts a drawn piece in current player's list of pieces on the square at
     * the given position in parameter. The piece is removed from the list and
     * all captured ennemy pieces are flipped.
     *
     * @param position the position of the square where the drawn piece is to be
     * put
     * @throws IllegalArgumentException if the position is out of board
     * @throws IllegalStateException if there is already a piece on that square
     * @throws IllegalArgumentException if there is no way to capture ennemy
     * piece
     */
    void play(Position position);

    /**
     * Checks who is the winner of the game by comparing both player's score
     *
     * @return the winner of the game
     */
    Player getWinner();

    /**
     * Resets the game by creating a new board, clearing the history of the game,
     * and initializing again the game.
     */
    void reset();

    /**
     * Sets surrender attribute to true
     */
    void surrender();

    /**
     * Swaps players
     */
    void pass();

    /**
     * Sets the name of both players
     *
     * @param currentName the name of the current player
     * @param opponentName the name of the opponent player
     */
    void setPlayersNames(String currentName, String opponentName);

    /**
     * Getter of the last move that a player did
     *
     * @return the last move from history
     */
    Move getLastMove();
    
    /**
     * Getter of the bonus squares positions
     * 
     * @return a copy of the list of bonus squares positions
     */
    public List<Position> getBonusSquares();
}
