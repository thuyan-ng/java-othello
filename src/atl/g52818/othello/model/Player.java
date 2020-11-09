package atl.g52818.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a player.
 */
public class Player {

    private String name;
    private PlayerColor color;
    private List<Piece> pieces;
    private int score;

    /**
     * Constructor of player
     *
     * @param color color of each piece
     * @throws NullPointerException if the color is null
     */
    Player(PlayerColor color, String name) {
        if (color == null) {
            throw new NullPointerException("The color can't be null.");
        }
        this.color = color;
        this.pieces = new ArrayList<>();
        this.name = name;
    }

    /**
     * Getter of name
     * 
     * @return the name of a player
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * 
     * @param name the name of a player
     */
    void setName(String name) {
        this.name = name;
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
     * Getter of the list of player's pieces
     *
     * @return the list of player's pieces
     */
    List<Piece> getPieces() { 
        return pieces;
    }

    /**
     * Appends a piece to the list of the player's pieces
     *
     * @param piece the piece to be added to the list
     */
    void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    /**
     * Removes the given piece from player's list of pieces
     *
     * @param piece the piece to be removed
     */
    void remove(Piece piece) {
        this.pieces.remove(piece);
    }

    /**
     * Initializes the list of the current player
     *
     * For each player : 1 piece of value 3, 10 pieces of value 2, 20 pieces of
     * value 1, 1 piece of value 0
     *
     */
    void initializeBag() {
        this.pieces.clear(); //if there is any remaining pieces from previous game
        
        for (int value = 0; value <= 3; value++) {
            // Defining the number of pieces of which value
            int count;
            if (value == 1) {
                count = 18;
            } else if (value == 2) {
                count = 10;
            } else {
                count = 1;
            }

            for (int i = 0; i < count; i++) {
                addPiece(new Piece(value, color));
            }
        }
    }

    /**
     * Draws a piece from current player's bag of pieces and removes it from player's bag.
     *
     * @return the drawn piece
     */
    Piece drawPiece() {
        int random = (int) (Math.random() * pieces.size());
        Piece piece = pieces.get(random);
        getPieces().remove(piece);
        return piece;
    }

    /**
     * Setter of score
     *
     * @param score the score of the player
     */
    void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter of score
     *
     * @return the score of the current player
     */
    public int getScore() {
        return score;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.color);
        hash = 43 * hash + Objects.hashCode(this.pieces);
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
        final Player other = (Player) obj;
        if (this.color != other.color) {
            return false;
        }
        if (!Objects.equals(this.pieces, other.pieces)) {
            return false;
        }
        return true;
    }

}
