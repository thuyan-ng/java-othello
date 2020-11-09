package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Piece;
import atl.g52818.othello.model.PlayerColor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class represents the colored piece
 */
public class PieceFX extends StackPane {

    /**
     * Constructor of PieceFX : a circle of the player's color and the value of
     * the piece
     *
     * @param piece the piece that PieceFX represents
     */
    PieceFX(Piece piece) {

        Circle c = new Circle(22.0);
        c.setStroke(Color.BLACK);
        c.setFill(piece.getColor() == PlayerColor.BLACK
                ? Color.BLACK : Color.WHITE);

        Text value = new Text(Integer.toString(piece.getValue()));
        value.setFont(Font.font(30.0));
        value.setFill(Color.BROWN);

        getChildren().addAll(c, value);
    }

    /**
     * Constructor of PieceFX : a circle of the player's color. To be used when
     * a player hovers over a square.
     *
     * @param color the color of the player
     */
    PieceFX(PlayerColor color) {

        Circle c = new Circle(22.0);
        c.setStroke(Color.BLACK);
        c.setFill(color == PlayerColor.BLACK
                ? Color.BLACK : Color.WHITE);

        getChildren().add(c);
    }

}
