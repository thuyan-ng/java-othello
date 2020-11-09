package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Model;
import atl.g52818.othello.model.Piece;
import atl.g52818.othello.model.Position;
import javafx.scene.layout.GridPane;

/**
 * This class represents the othellier (the board of the game)
 */
public class Othellier extends GridPane {

    private Model game;

    /**
     * Constructor of Othellier
     *
     * @param game the current game
     */
    Othellier(Model game) {
        this.game = game;
        updateOthellier();
    }

    /**
     * Updates the othellier
     */
    void updateOthellier() {

        this.getChildren().clear();

        for (int row = 0; row < game.getBoard().getHeight(); row++) {
            for (int col = 0; col < game.getBoard().getWidth(); col++) {

                Position p = new Position(row, col);

                Piece piece = game.getBoard().getPieceAt(p);
                SquareFX square = new SquareFX(game, p);

                if (piece != null) {
                    square.put(piece);
                }
                this.add(square, col, row);
            }
        }
    }
}
