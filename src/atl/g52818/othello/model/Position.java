package atl.g52818.othello.model;

/**
 * This class represents the position of a piece on the board
 *
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructor of Position
     *
     * @param row the row of position
     * @param column the column of position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Calculates the position next to the current position depending on given
     * direction
     *
     * @param direction the direction which defines the next position
     * @return the position next to the current position
     */
    Position next(Direction direction) {
        return new Position(this.row + direction.getRow(), this.column + direction.getColumn());
    }

    /**
     * Getter of the row
     *
     * @return the row of the position
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter of the column
     *
     * @return the column of the position
     */
    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.row;
        hash = 47 * hash + this.column;
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
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

}
