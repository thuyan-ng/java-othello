package atl.g52818.othello.controller;

import atl.g52818.othello.model.Model;
import atl.g52818.othello.model.Position;
import atl.g52818.othello.view.ViewConsole;

/**
 * This class represents the controller of othello
 */
public class Controller {

    private Model game;
    private ViewConsole view;

    /**
     * Constructor of Controller
     *
     * @param game the game model
     * @param view the game display
     * @throws IllegalArgumentException if the game or view is null
     */
    public Controller(Model game, ViewConsole view) {
        if (game == null || view == null) {
            throw new IllegalArgumentException("Game and view can't be null");
        }
        this.game = game;
        this.view = view;
    }

    /**
     * Initialize the game and the view
     */
    public void initialize() {
        this.game.initialize();
        this.view.displayWelcome();
    }

    /**
     * Start the game if ready, display list of commands, and while the game
     * isn't over, display the board and ask a command. When game is over,
     * display game over
     */
    public void startGame() {

        boolean quit = false;

        displayBoardAndScores();
        view.displayHelp();

        while (!game.isOver() && !quit) {

            String entry = view.askCommand(game.getCurrent());
            String[] cmd = entry.split(" ");

            switch (cmd[0]) {
                case "quit":
                    quit = true;
                    break;
                case "show":
                    view.displayBoard(game.getBoard());
                    break;
                case "play":
                    checkAndPlay(cmd);
                    displayBoardAndScores();
                    break;
                default:
                    view.displayError("The command you entered is not valid. Try again.");
            }
        }

        displayBoardAndScores();
        view.displayWinner(game.getWinner());
        view.quit();
    }

    /**
     * Checks if the chosen square is legal and if the player entered a valid entry.
     * 
     * @param cmd the entry of the player
     * @throws IllegalArgumentException if the player can't put a piece on the given square
     * @throws IllegalStateException if there is already a piece on the given square
     * @throws ArrayIndexOutOfBoundsException if the entry is not correctly formated
     */
    private void checkAndPlay(String[] cmd) {

        try {
            int x = Integer.parseInt(cmd[1]);
            int y = Integer.parseInt(cmd[2]);

            game.play(new Position(x, y));

        } catch (IllegalArgumentException e) {
            view.displayError("You cannot put a piece on that square.");
        } catch (IllegalStateException e) {
            view.displayError("There is already a piece on that square.");
        } catch (ArrayIndexOutOfBoundsException e) {
            view.displayError("play must be followed by a row and a column.");
        }
    }

    /**
     * Displays the board and the scores
     */
    private void displayBoardAndScores() {
        view.displayBoard(game.getBoard());
        view.displayScore(game.getCurrent(), game.getOpponent());
    }
}
