package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * This class represents an HBox of buttons
 */
public class Buttons extends HBox {

    private Button pass = new Button("Passer");
    private Button abandon = new Button("Abandonner");

    /**
     * Constructor of Buttons
     * 
     * @param game the current game
     * @param table the current table of moves of the game
     */
    Buttons(Game game, TableMove table) {
        
        pass.setOnMouseClicked(e -> game.pass());
        
        abandon.setOnMouseClicked(e -> {
            game.surrender();
            disablePassAbandon();
        });

        Button reset = new Button("Recommencer");
        reset.setOnMouseClicked(e -> {
            table.clear();
            game.reset();
            enablePassAbandon();
        });

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(pass, abandon, reset);
    }

    private void enablePassAbandon() {
        pass.setDisable(false);
        abandon.setDisable(false);
    }

    /**
     * Disables pass and abandon buttons
     */
    void disablePassAbandon() {
        pass.setDisable(true);
        abandon.setDisable(true);
    }
}
