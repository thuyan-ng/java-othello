package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Game;
import atl.g52818.othello.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 */
public class Window extends VBox implements Observer{
    
    private Game game;
    private InfoTable infoTableCur;
    private InfoTable infoTableOp;

    Window(Game game) {
        this.game = game;
        game.addObserver(this);
        
        HBox columns = addColums();
        
        infoTableCur = new InfoTable(game, game.getCurrent());
        infoTableOp = new InfoTable(game, game.getOpponent());
        
        getChildren().addAll(columns, infoTableCur, infoTableOp);
        
        setPrefSize(250, 100);
        
        settings();
    }

    private void settings() {
        Stage newWindow = new Stage();
        newWindow.setTitle("Informations");
        newWindow.setScene(new Scene(this));
        newWindow.show();
    }

    private HBox addColums() {
        Label nameLabel = new Label("Nom");
        nameLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
        
        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
        
        Label nbPiecesLabel = new Label("Nombre de jetons");
        nbPiecesLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
        
        HBox columns = new HBox(30, nameLabel, scoreLabel, nbPiecesLabel);
        return columns;
    }
    
    /**
     * Updates the information of the current and opponent players
     */
    @Override
    public void update(){
        infoTableCur.updateInfoTable();
        infoTableOp.updateInfoTable();
    }

}
