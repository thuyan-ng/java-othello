package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Model;
import atl.g52818.othello.model.Piece;
import atl.g52818.othello.model.Position;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents a SquareFX
 */
public class SquareFX extends StackPane {

    private Model game;
    private Color color;

    /**
     * Constructor of SquareFX : a rectangle that is clickable.
     *
     * @param game the current game
     * @param p the position of the square
     */
    SquareFX(Model game, Position p) {
        this.game = game;

        Rectangle r = new Rectangle(50, 50);
        r.setStroke(Color.BLACK);
        
        color = game.getBonusSquares().contains(p) ? Color.YELLOW : Color.GREEN;
        
        r.setFill(color);
        
        getChildren().add(r);

        setOnMouseClicked(e -> handleMouseClicked(p));

        if (game.getBoard().getPieceAt(p) == null) {
            setOnMouseEntered(e -> setBackground(p, r));
        }
    }
    
    /**
     * Creates a PieceFX and puts it on the SquareFX.
     *
     * @param piece the piece to be put
     */
    void put(Piece piece) {
        PieceFX pieceFX = new PieceFX(piece);
        getChildren().addAll(pieceFX);
    }

    private void setBackground(Position p, Rectangle r) {

        r.setFill(game.getPositions(game.getCurrent()).contains(p)
                ? Color.YELLOWGREEN : Color.RED);

        PieceFX piece = new PieceFX(game.getCurrent().getColor());
        getChildren().add(piece);

        setOnMouseExited(e -> resetSquare(r,piece));
    }

    private void resetSquare(Rectangle r, PieceFX piece) {
        r.setFill(color);
        getChildren().remove(piece);
    }

    private void handleMouseClicked(Position pos) {
        try {
            if (!game.isOver()) {
                game.play(pos);
            }
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }
}
