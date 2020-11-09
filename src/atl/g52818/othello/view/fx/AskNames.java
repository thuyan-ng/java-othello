package atl.g52818.othello.view.fx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the saving process of the players and their color
 */
public class AskNames extends GridPane {

    private TextField currentName;
    private TextField opponentName;

    /**
     * Constructor of AskNAmes
     */
    AskNames() {

        add(addIntro(), 0, 0);

        Label playerBlack = new Label("Nom du joueur NOIR ");
        add(playerBlack, 0, 1);

        currentName = new TextField();
        add(currentName, 1, 1);

        Label playerWhite = new Label("Nom du joueur BLANC ");
        add(playerWhite, 0, 2);

        opponentName = new TextField();
        add(opponentName, 1, 2);

        setVgap(15);
        setAlignment(Pos.CENTER);
    }

    private Text addIntro() {
        Text intro = new Text("Bienvenue sur Othello");
        intro.setFont(Font.font(null, FontWeight.BOLD, 15));

        return intro;
    }

    /**
     * Getter of currentName
     *
     * @return the name of the current player
     */
    String getCurrentName() {
        return currentName.getText();
    }

    /**
     * Getter of opponentName
     *
     * @return the name of the opponent player
     */
    String getOpponentName() {
        return opponentName.getText();
    }
}
