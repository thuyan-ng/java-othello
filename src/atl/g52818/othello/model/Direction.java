package atl.g52818.othello.model;

/**
 * This class represents the direction of a move of a piece
 */
public enum Direction {

    NORTH(-1, 0),
    NORTHEAST(-1, 1),
    EAST(0, 1),
    SOUTHEAST(1, 1),
    SOUTH(1, 0),
    SOUTHWEST(1, -1),
    WEST(0, -1),
    NORTHWEST(-1, -1);

    private final int row;
    private final int column;

    /**
     * Constructor of Direction
     *
     * @param row the row of the direction
     * @param column the column of the direction
     */
    private Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Getter of row
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter of column
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }
}
