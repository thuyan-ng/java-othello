package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Game;
import atl.g52818.othello.model.Move;
import atl.g52818.othello.model.Player;
import atl.g52818.othello.util.Observer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents the main class of Othello.
 */
public class MainFX extends Application implements Observer {

    private Game game;
    private Othellier othellier;
    private ScoresBoard scores;

    private Indicators indicators;
    private TableMove tableMove;
    private Buttons buttons;

    private Text winner;

    /**
     * MainFX class of Othello application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application by asking both players' names, then starting the
     * game
     *
     * @param primaryStage the stage to be used to display
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game();
        game.addObserver(this);

        AskNames input = new AskNames();
        Button startBtn = new Button("Commencer la partie");
        input.add(startBtn, 0, 3);

        VBox root = setRoot(input);

        HBox hBox = setHBox();

        startBtn.setOnMouseClicked(e -> {

            root.getChildren().clear();

            game.setPlayersNames(input.getCurrentName(), input.getOpponentName());
            game.initialize();

            hBox.getChildren().addAll(createLeftBox(), createRightBox());

            root.getChildren().addAll(new MenuClass(game), hBox);

            update();

        });

        setPrimaryStage(primaryStage, root);
    }

    private VBox setRoot(AskNames input) {
        VBox root = new VBox(input);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(900, 600);
        return root;
    }

    private VBox createLeftBox() {
        InfoPlayer current = new InfoPlayer(game.getCurrent());
        InfoPlayer opponent = new InfoPlayer(game.getOpponent());

        scores = new ScoresBoard(current, opponent);
        othellier = new Othellier(game);
        indicators = new Indicators(game);

        VBox vBox = new VBox(10, scores, othellier, indicators);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        return vBox;
    }

    private VBox createRightBox() {
        ObservableList<Move> data = FXCollections.observableArrayList();
        tableMove = new TableMove(game, data);
        buttons = new Buttons(game, tableMove);
        winner = new Text();

        VBox vBox = new VBox(10, tableMove, buttons, winner);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        return vBox;
    }

    /**
     * Updates the board, scores, indicators, history and displays the winner if
     * the game is over or if a player has surrendered.
     */
    @Override
    public void update() {
        othellier.updateOthellier();
        scores.updateScores();
        indicators.updateIndicators();
        tableMove.updateTable();
        checkGameOver();
    }

    private void checkGameOver() {
        if (game.isSurrender()) {
            handleGameOver(game.getOpponent());
        } else if (game.isOver()) {
            handleGameOver(game.getWinner());
        } else {
            othellier.setDisable(false);
            winner.setText("");
        }
    }

    private void handleGameOver(Player player) {
        winner.setText(player.getName() + " a gagné!");
        winner.setFont(Font.font(30));
        othellier.setDisable(true);
        buttons.disablePassAbandon();
        indicators.updateIndicators();
    }

    private HBox setHBox() {
        HBox root = new HBox();
        root.setPrefSize(900, 550);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        return root;
    }

    private void setPrimaryStage(Stage primaryStage, VBox root) {
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("OTHELLO");
        primaryStage.show();
    }

    /*
    
    private Timeline timeline;
    private Label timerLabel = new Label();
    private static final Integer STARTTIME = 60;
    private Integer timeSeconds = STARTTIME;
    
    
    private void timer() {
        timerLabel.setText(timeSeconds.toString());
        timerLabel.setStyle("-fx-font-size: 3em;");

        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds = STARTTIME;

        // update timerLabel
        timerLabel.setText(timeSeconds.toString());

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), handle()));

        timeline.playFromStart();
    }

    private EventHandler handle() {
        EventHandler e = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                timeSeconds--;
                // update timerLabel
                timerLabel.setText(
                        timeSeconds.toString());
                if (timeSeconds <= 0) {
                    timeline.stop();
                }
            }
        };
        return e;
    }

     */
}
