package atl.g52818.othello.model;

/**
 * This class represents a move of a Player.
 */
public class Move {
    
    private static int nb = 0;
    private int id;
    private String playerName;
    private String action;
    private String pos;
    private Integer flipped;

    /**
     * Constructor of a Move
     * 
     * @param playerName the name of a player
     * @param action the action that the player did : play
     * @param pos the position of the piece to be put
     * @param flipped the number of flipped pieces
     */
    Move(String playerName, String action, Position pos, Integer flipped) {
        this.id = nb;
        this.playerName = playerName;
        this.action = action;
        this.pos = pos.getRow() + " - " + pos.getColumn();
        this.flipped = flipped;
        nb++;
    }

    /**
     * Constructor of a Move
     * 
     * @param playerName the name of a player
     * @param action the action that the player did : start a game or pass
     */
    Move(String playerName, String action) {
        this.id = nb;
        this.playerName = playerName;
        this.action = action;
        nb++;
    }

    /**
     * Getter of id
     * 
     * @return the id of a move
     */
    public int getId() {
        return id;
    }

    /**
     * Getter of playerName
     * 
     * @return the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getter of action
     * 
     * @return the action that the player did
     */
    public String getAction() {
        return action;
    }

    /**
     * Getter of position
     * 
     * @return the position of the piece to be put
     */
    public String getPos() {
        return pos;
    }

    /**
     * Getter of flipped
     * 
     * @return the number of flipped pieces
     */
    public Integer getFlipped() {
        return flipped;
    }

    /**
     * Setter of nb
     * 
     * @param nb the number to be set
     */
    static void setNb(int nb) {
        Move.nb = nb;
    }

    /**
     * Setter of id
     * 
     * @param id the id to be set
     */
    void setId(int id) {
        this.id = id;
    }

    /**
     * Setter of playerName
     * 
     * @param playerName the name of the player to be set
     */
    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Setter of action
     * 
     * @param action the action to be set
     */
    void setAction(String action) {
        this.action = action;
    }

    /**
     * Setter of position
     * 
     * @param pos the position to be set
     */
    void setPos(Position pos) {
        this.pos = pos.getRow() + " - " + pos.getColumn();
    }

    /**
     * Setter of flipped
     * 
     * @param flipped the number of flipped pieces to be set
     */
    void setFlipped(Integer flipped) {
        this.flipped = flipped;
    }
    
    
}
