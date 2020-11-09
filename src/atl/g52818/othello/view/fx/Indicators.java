package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Game;
import atl.g52818.othello.model.Model;
import atl.g52818.othello.model.Player;
import atl.g52818.othello.model.PlayerColor;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;

/**
 * This class represents the progress indicators of the game
 */
public class Indicators extends GridPane {

    private ProgressBar pb;
    private ProgressIndicator pi;
    private Model game;

    /**
     * Constructor of Indicators. Creates a progress bar and a progress
     * indicator
     *
     * @param game the current game
     */
    Indicators(Game game) {

        pb = new ProgressBar();
        pi = new ProgressIndicator();

        this.game = game;

        Label progressBar = new Label("Noir / Blanc ");

        pb.setStyle("-fx-accent:black");

        Label progressInd = new Label("Partie complétée à: ");

        add(progressBar, 0, 0);
        add(pb, 1, 0);
        add(progressInd, 0, 2);
        add(pi, 1, 2);
    }

    /**
     * Updates the progress bar and the progress indicator.
     */
    void updateIndicators() {
        updateProgressBar();
        updateProgressIndicator();
    }

    private void updateProgressBar() {
        Player player = game.getCurrent().getColor() == PlayerColor.BLACK
                ? game.getCurrent() : game.getOpponent();

        double ratio = game.getBoard().sizeOfTakenSquares(player) / game.getBoard().occupiedSquares();

        pb.setProgress(ratio);
    }

    private void updateProgressIndicator() {
        if (game.isOver() || game.isSurrender()) {
            pi.setProgress(1);
        } else {
            double nbSquares = game.getBoard().getHeight() * game.getBoard().getWidth();
            pi.setProgress(game.getBoard().occupiedSquares() / nbSquares);
        }
    }

}
