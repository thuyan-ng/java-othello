package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Model;
import atl.g52818.othello.model.Move;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class represents the history of a game
 */
public class TableMove extends TableView<Move> {

    private ObservableList<Move> data;
    private Model game;

    TableMove(Model game, ObservableList<Move> data) {
        this.data = data;
        this.game = game;
        this.setItems(data);

        TableColumn id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn<>("Nom");
        name.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn action = new TableColumn<>("Action");
        action.setCellValueFactory(new PropertyValueFactory<>("action"));

        TableColumn position = new TableColumn<>("Position");
        position.setCellValueFactory(new PropertyValueFactory<>("pos"));

        TableColumn flipped = new TableColumn<>("Prises");
        flipped.setCellValueFactory(new PropertyValueFactory<>("flipped"));

        getColumns().addAll(id, name, action, position, flipped);
    }

    /**
     * Clears the data list
     */
    void clear() {
        data.clear();
    }

    /**
     * Updates the table of moves while nobody surrenders
     */
    void updateTable() {
        if (!game.isSurrender()) {
            data.add(game.getLastMove());
        }
    }

}
