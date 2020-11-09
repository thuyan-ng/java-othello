package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Model;
import atl.g52818.othello.model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * This class represents a grid of information: name of the player, 
 * his/her score and the number of their disks on the board
 */
public class InfoTable extends HBox {
    
    private Model game;
    private Player player;
    
    private Text curName = new Text();
    private Text curScore = new Text();
    private Text curNbPieces = new Text();
    
    /**
     * Constructor of InfoTable
     * 
     * @param game the current game
     */
    InfoTable(Model game, Player player) {
        this.game = game;
        this.player = player;
        
        setSpacing(40);

        getChildren().addAll(curName, curScore, curNbPieces);
        
        updateInfoTable();
    }
    
    /**
     * Updates the information on the table
     */
    void updateInfoTable(){
        
        curName.setText(player.getName());
        curScore.setText(Integer.toString(player.getScore()));
        curNbPieces.setText(Integer.toString(game.getBoard().sizeOfTakenSquares(player)));
        
    }
    
}
