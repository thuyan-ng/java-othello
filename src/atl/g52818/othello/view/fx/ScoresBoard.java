package atl.g52818.othello.view.fx;

import javafx.scene.layout.HBox;

/**
 * This class represents the board where the info of both players are displayed.
 */
public class ScoresBoard extends HBox {
    
    private InfoPlayer infoCurrent;
    private InfoPlayer infoOpponent;

    /**
     * Constructor of PlayersBoard.
     * 
     * @param current the current player
     * @param opponent the opponent player
     */
    ScoresBoard(InfoPlayer current, InfoPlayer opponent) {
        this.infoCurrent = current;
        this.infoOpponent = opponent;
        
        setSpacing(5);
        getChildren().addAll(infoCurrent, infoOpponent);
    }
    
    /**
     * Updates the score of both players
     */
    void updateScores(){
        infoCurrent.updateScore();
        infoOpponent.updateScore();
    }
}
