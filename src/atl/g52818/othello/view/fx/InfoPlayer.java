package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Player;
import atl.g52818.othello.model.PlayerColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the information of a player: his/her name, color and
 * score
 */
public class InfoPlayer extends GridPane {

    private Player player;
    private Text score;

    /**
     * Constructor of InfoPlayer: name, color and score
     *
     * @param player the player
     */
    InfoPlayer(Player player) {
        this.player = player;

        setPrefSize(200, 50);
        setHgap(25);
        setAlignment(Pos.CENTER);

        addName();

        addPion();

        addScore();

        setBackground();
    }

    private void setBackground() {
        Color color = player.getColor() == PlayerColor.BLACK
                ? Color.LIGHTCORAL : Color.ANTIQUEWHITE;

        setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void addScore() {
        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
        add(scoreLabel, 2, 0);
        score = new Text(Integer.toString(player.getScore()));
        add(score, 2, 1);
    }

    private void addPion() {
        Label pionLabel = new Label("Pion");
        pionLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
        add(pionLabel, 1, 0);
        Circle playerPion = new Circle(5);
        playerPion.setFill(player.getColor() == PlayerColor.BLACK ? Color.BLACK : Color.WHITE);
        add(playerPion, 1, 1);
    }

    private void addName() {
        Label nameLabel = new Label("Nom");
        nameLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
        add(nameLabel, 0, 0);
        Text nameText = new Text(player.getName());
        add(nameText, 0, 1);
    }

    /**
     * Updates the score of the player
     */
    void updateScore() {
        this.score.setText(Integer.toString(player.getScore()));
    }

}
