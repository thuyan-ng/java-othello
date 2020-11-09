package atl.g52818.othello.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the board of Othello
 */
public class Board {

    private final Piece[][] board;
    private final int width = 8;
    private final int height = 8;

    /**
     * Default constructor of Board, all squares void.
     */
    Board() {
        board = new Piece[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                board[row][column] = null;
            }
        }
    }

    /**
     * Getter of width
     *
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter of height
     *
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter of piece at a given position in parameter
     *
     * @param position the position of the piece
     * @return the piece at the given position in parameter
     * @throws IllegalArgumentException if the position is out of bound
     */
    public Piece getPieceAt(Position position) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("Position out of board. "
                    + "Given position: " + "(" + position.getRow() + ','
                    + position.getColumn() + ")");
        }

        return board[position.getRow()][position.getColumn()];
    }

    /**
     * Checks if the row of the given position is between 0 and the height of
     * the board, and if the column of the given position is between 0 and the
     * width of the board.
     *
     * @param position the position to be checked
     * @return true if the position is inside the board, false otherwise
     */
    boolean isInside(Position position) {
        return (position.getRow() >= 0 && position.getRow() < height
                && position.getColumn() >= 0 && position.getColumn() < width);
    }

    /**
     * Puts a given piece in parameter on the square at the given position in
     * parameter
     *
     * @param piece the piece to be put
     * @param position the position of the square where the piece is to be put
     *
     * @throws IllegalArgumentException if the given position is out of bound
     * @throws NullPointerException if the given piece is null
     * @throws IllegalArgumentException if the given position is not free
     */
    void put(Piece piece, Position position) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("Position out of board. "
                    + "Given position: " + "(" + position.getRow() + ','
                    + position.getColumn() + ")");
        }
        if (piece == null) {
            throw new NullPointerException("The piece can't be null.");
        }
        if (!isFree(position)) {
            throw new IllegalArgumentException("The position is not free. "
                    + "Given position: " + "(" + position.getRow() + ','
                    + position.getColumn() + ")");
        }
        board[position.getRow()][position.getColumn()] = piece;
    }

    /**
     * Checks if a square at given position is free or not
     *
     * @param position the position of the square to be checked
     * @return true if the square at position is free, false otherwise
     * @throws IllegalArgumentException if the given position is out of bound
     */
    boolean isFree(Position position) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("Position out of board. "
                    + "Given position: " + "(" + position.getRow() + ','
                    + position.getColumn() + ")");
        }

        return board[position.getRow()][position.getColumn()] == null;
    }

    /**
     * Checks if the piece on a given position is the same color as given color.
     *
     * To be used to check if a player still has moves
     *
     * @param position the position of the piece to be checked
     * @param color the color of the player to be compared with the piece on the
     * board
     * @return true if the color of the piece is the same as player's color,
     * false otherwise
     * @throws IllegalArgumentException if the given position is out of bound
     */
    boolean isMyOwn(Position position, PlayerColor color) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("Position out of board. "
                    + "Given position: " + "(" + position.getRow() + ','
                    + position.getColumn() + ")");
        }

        return board[position.getRow()][position.getColumn()].getColor() == color;
    }

    /**
     * For each square of the board, checks if there is a piece on it and if the
     * color of the piece is the same as the given player's.
     *
     * @param player the current player
     * @return the list of positions of player's pieces
     */
    List<Position> getTakenSquares(Player player) {

        List<Position> positions = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {

                if (board[row][column] != null
                        && board[row][column].getColor() == player.getColor()) {
                    positions.add(new Position(row, column));
                }
            }
        }
        return positions;
    }

    /**
     * Getter of the size of the number of taken squares by the given player in
     * parameter
     *
     * @param player the player
     * @return the number of occupied squares by the player's pieces
     */
    public int sizeOfTakenSquares(Player player) {
        return getTakenSquares(player).size();
    }

    /**
     * Calculates the score of a given player in parameter
     *
     * @param player the player whose score is to be calculated
     * @return the score of the player
     */
    int score(Player player) {
        int score = 0;
        for (Position p : this.getTakenSquares(player)) {
            int value = this.getPieceAt(p).getValue();
            score += value;
        }
        return score;
    }

    /**
     * Calculates the number of empty squares
     *
     * @return the number of empty squares
     */
    int emptySquares() {
        int nb = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (isFree(new Position(i, j))) {
                    nb++;
                }
            }
        }
        return nb;
    }

    /**
     * Getter of occupied squares.
     *
     * @return the number of occupied squares
     */
    public double occupiedSquares() {
        return (height * width) - emptySquares();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Arrays.deepHashCode(this.board);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }

}
