package atl.g52818.othello.model;

import java.util.Objects;

/**
 * This class represents a piece of a player.
 */
public class Piece {

    private int value;
    private PlayerColor color;

    /**
     * Constructor of piece
     *
     * @param value the value of a piece
     * @param color the color of a piece
     * @throws IllegalArgumentException if the value is negative
     */
    Piece(int value, PlayerColor color) {
        if (value < 0) {
            throw new IllegalArgumentException("The value must be positive. "
                    + "Given value: " + value);
        }
        this.value = value;
        this.color = color;
    }

    /**
     * Getter of the value
     *
     * @return integer that represents the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter of the color
     *
     * @return the color of the player
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Flips the color of the piece. Black becomes white and white becomes
     * black.
     */
    void flip() {
        if (color == PlayerColor.BLACK) {
            color = PlayerColor.WHITE;
        } else {
            color = PlayerColor.BLACK;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.value;
        hash = 53 * hash + Objects.hashCode(this.color);
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
        final Piece other = (Piece) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

}
