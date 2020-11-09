package atl.g52818.othello.view;

import atl.g52818.othello.model.Board;
import atl.g52818.othello.model.Piece;
import atl.g52818.othello.model.Player;
import atl.g52818.othello.model.PlayerColor;
import atl.g52818.othello.model.Position;
import java.util.Scanner;

/**
 * This class represents the view of othello
 */
public class ViewConsole {

    static final PlayerColor BLACK = PlayerColor.BLACK;
    static final PlayerColor WHITE = PlayerColor.WHITE;

    static final String GREEN = ConsoleColors.GREEN;
    static final String RED = ConsoleColors.RED;
    static final String BLACK_BOLD = ConsoleColors.BLACK_BOLD;
    static final String RESET = ConsoleColors.RESET;

    private Scanner in;

    /**
     * Constructor of View()
     */
    public ViewConsole() {

    }

    /**
     * Display welcome message
     */
    public void displayWelcome() {
        System.out.println("\t WELCOME TO OTHELLO!");
        System.out.println("");
    }

    /**
     * Display goodbye message
     */
    public void quit() {
        System.out.println("Bye bye");
    }

    /**
     * Display error message
     *
     * @param message the error message to be displayed
     */
    public void displayError(String message) {
        System.out.println(RED + message + RESET);
    }

    /**
     * Displays the help menu (list of commands)
     */
    public void displayHelp() {
        System.out.println("List of commands: ");
        System.out.println("- play <x y>");
        System.out.println("- show");
        System.out.println("- quit");
        System.out.println("");
    }

    /**
     * Ask the current player to enter a command
     *
     * @param player the current player
     * @return the command entered
     */
    public String askCommand(Player player) {
        System.out.println(BLACK_BOLD + player.getColor() + " PLAYER's turn." + RESET);
        System.out.println("Please enter your command: ");
        in = new Scanner(System.in);
        return in.nextLine().toLowerCase();
    }

    /**
     * Displays the board of the game: checks all the positions and displays the
     * piece if there is one on that position
     * 
     * @param board the board of the game to be displayed
     */
    public void displayBoard(Board board) {
        System.out.print(" col#  ");
        for (int i = 0; i < board.getWidth(); i++) {
            System.out.print(" |" + i + "|");
        }
        System.out.println();

        for (int row = 0; row < board.getHeight(); row++) {
            System.out.print("row# |" + row + "|");
            for (int column = 0; column < board.getWidth(); column++) {
                Piece piece = board.getPieceAt(new Position(row, column));
                if (piece == null) {
                    System.out.print(" __ ");
                } else {
                    displayPiece(piece);
                }
            }
            System.out.println("");
        }
    }

    /**
     * Displays the given piece in parameter that is represented by its value and
     * its color
     * 
     * @param piece the piece to be displayed
     */
    public void displayPiece(Piece piece) {
        if (piece.getColor() == BLACK) {
            System.out.print(" " + piece.getValue() + "  ");
        } else {
            System.out.print(" " + ConsoleColors.BLACK_BACKGROUND
                    + ConsoleColors.WHITE + piece.getValue() + RESET + "  ");
        }
    }

    /**
     * Displays game over and congratulation message to the winner
     *
     * @param winner the winner of the game
     */
    public void displayWinner(Player winner) {
        System.out.println("");
        System.out.println("Congratulation to the winner: ");

        if (winner.getColor() == BLACK) {
            System.out.println("\tBLACK PLAYER");
        } else {
            System.out.println("\tWHITE PLAYER");
        }
        System.out.println("");
    }

    /**
     * Displays the score of both players
     *
     * @param current the current player
     * @param opponent the opponent player
     */
    public void displayScore(Player current, Player opponent) {
        System.out.println(GREEN + "\t || Score ||");
        System.out.print(current.getColor() + " : " + current.getScore() + " point(s) - ");
        System.out.println(opponent.getColor() + " : " + opponent.getScore() + " point(s)");
        System.out.println("");
    }
}
