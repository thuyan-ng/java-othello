package atl.g52818.othello.model;

import atl.g52818.othello.util.Observable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a game of Othello
 */
public class Game extends Observable implements Model {

    final PlayerColor BLACK = PlayerColor.BLACK;
    final PlayerColor WHITE = PlayerColor.WHITE;

    private Player current;
    private Player opponent;
    private Board board;

    private boolean surrender = false;
    private List<Move> history;

    private List<Position> bonusSquares = new ArrayList<>();

    /**
     * Constructor of Game. The current player's color is BLACK and the
     * opponent's is WHITE. A new Board is created.
     */
    public Game() {
        this.current = new Player(BLACK, "");
        this.opponent = new Player(WHITE, "");
        this.board = new Board();
    }

    /**
     * Adds a bonus square of the given position in parameter to the list of
     * bonus squares. To be used only for the tests.
     *
     * @param pos the position of the bonus square to be added
     */
    void setBonusSquares(Position pos) {
        bonusSquares.add(pos);
    }

    /**
     * Getter of the bonus squares positions
     *
     * @return a copy of the list of bonus squares positions
     */
    @Override
    public List<Position> getBonusSquares() {
        List<Position> copy = new ArrayList<>();

        for (Position p : bonusSquares) {
            copy.add(p);
        }

        return copy;
    }

    /**
     * Creates 3 bonus squares randomly where no default piece is placed.
     */
    void createBonusSquares() {
        int randomX;
        int randomY;

        for (int i = 0; i < 3; i++) {
            do {
                randomX = (int) (Math.random() * board.getWidth());
                randomY = (int) (Math.random() * board.getHeight());

            } while (randomX == 3 && (randomY == 3 || randomY == 4)
                    || randomX == 4 && (randomY == 4 || randomY == 3));

            Position randomSquare = new Position(randomX, randomY);
            bonusSquares.add(randomSquare);
        }
    }

    /**
     * Initializes the history list of the game
     */
    void initializeHistory() {
        history = new ArrayList<>();
        history.add(new Move(current.getName(), "Nouvelle partie"));
    }

    /**
     * Getter of surrender
     *
     * @return true if the player has surrendered, false otherwise
     */
    @Override
    public boolean isSurrender() {
        return surrender;
    }

    /**
     * Getter of the last move that a player did
     *
     * @return the last move from history
     */
    @Override
    public Move getLastMove() {
        return history.get(history.size() - 1);
    }

    /**
     * Getter of Board
     *
     * @return the board
     */
    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * Getter of current player
     *
     * @return the current player
     */
    @Override
    public Player getCurrent() {
        return current;
    }

    /**
     * Getter of opponent player
     *
     * @return the opponent player
     */
    @Override
    public Player getOpponent() {
        return opponent;
    }

    /**
     * Initializes the game by : putting 2 black and 2 white pieces on default
     * position and adding 30 pieces in each player's list of pieces.
     *
     * For each player : 1 piece of value 3, 10 pieces of value 2, 20 pieces of
     * value 1, 1 piece of value 0
     */
    @Override
    public void initialize() {

        // Initializing the board
        board.put(new Piece(1, WHITE), new Position(3, 3));
        board.put(new Piece(1, BLACK), new Position(3, 4));
        board.put(new Piece(1, WHITE), new Position(4, 4));
        board.put(new Piece(1, BLACK), new Position(4, 3));

        // Initializing both player's list of pieces
        current.initializeBag();
        opponent.initializeBag();

        // Each player starts with 2 points
        updateScores();

        // Initializing the history of Moves
        initializeHistory();

        // Creating the bonus squares
        createBonusSquares();
    }

    /**
     * For each piece on the board and for all directions, checks throughout
     * ennemy pieces until a free position is found. The position is added to
     * the list of possible positions that the current player stil has.
     *
     * @param player the player
     * @return a list of all possible moves that the player still has.
     */
    @Override
    public List<Position> getPositions(Player player) {

        List<Position> positions = new ArrayList<>();

        for (Position p : board.getTakenSquares(player)) {
            for (Direction d : Direction.values()) {

                Position previous = p;
                Position end = p.next(d);

                while (board.isInside(end) && !board.isFree(end)
                        && !board.isMyOwn(end, player.getColor())) {
                    previous = end;
                    end = end.next(d);
                }
                if (board.isInside(end) && board.isFree(end)
                        && !board.isMyOwn(previous, player.getColor())) {
                    positions.add(end);
                }
            }
        }
        return positions;
    }

    /**
     * Checks if the list of all possible moves of the given player in parameter
     * is empty or not.
     *
     * @param player the player
     * @return true if the current player can still make a move, false otherwise
     */
    @Override
    public boolean hasMoves(Player player) {
        return !getPositions(player).isEmpty();
    }

    /**
     * Checks if the game is over or not. It is over if both players can't move,
     * or if the board is full.
     *
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isOver() {

        boolean noMoves = !hasMoves(current) && !hasMoves(opponent);
        boolean boardFull = current.getPieces().isEmpty() && opponent.getPieces().isEmpty();

        return (noMoves || boardFull);
    }

    /**
     * Swaps attributes of current and opponent players if the game is not over
     */
    void swapPlayers() {
        if (!hasMoves(opponent)) {
            throw new IllegalStateException("The opponent player can't move. "
                    + "It's current player's turn.");
        }
        if (!this.isOver()) {
            Player tmp = this.current;
            this.current = this.opponent;
            this.opponent = tmp;
        }
    }

    /**
     * Puts a drawn piece in current player's list of pieces on the square at
     * the given position in parameter. The piece is removed from the list and
     * all captured ennemy pieces are flipped.
     *
     * @param position the position of the square where the drawn piece is to be
     * put
     * @throws IllegalArgumentException if the position is out of board
     * @throws IllegalStateException if there is already a piece on that square
     * @throws IllegalArgumentException if there is no way to capture ennemy
     * piece
     */
    @Override
    public void play(Position position) {
        if (!this.getPositions(current).contains(position)) {
            throw new IllegalArgumentException("No way to capture any ennemy piece");
        }

        int flipped = flipAndCount(position);
        board.put(current.drawPiece(), position);

        history.add(new Move(current.getName(), "Place une pièce", position, flipped));
        updateScores();

        if (!isOver()) {
            swapPlayers();
        }

        notifyObservers();
    }

    /**
     * For all directions, flips and counts all ennemy pieces that will be
     * captured by putting a piece of current player on the square at the given
     * position in parameter
     *
     * @param position the position of the piece that will capture ennemy pieces
     * @return the number of flipped pieces
     */
    int flipAndCount(Position position) {
        int count = 0;

        for (Direction d : Direction.values()) {
            Position p = position.next(d);

            if (canCapture(position, d)) {

                while (board.getTakenSquares(opponent).contains(p)) {
                    board.getPieceAt(p).flip();
                    count++;
                    p = p.next(d);
                }
            }
        }
        return count;
    }

    /**
     * Checks if a piece at a given position can capture any ennemy piece in a
     * given direction. If no direction checking is done, all ennemy pieces
     * surrounding the current piece will be flipped.
     *
     * @param p the position of the piece
     * @param d the direction to be checked
     * @return true if a piece can capture any ennemy piece, false otherwise
     */
    boolean canCapture(Position p, Direction d) {
        Position end = p.next(d);
        boolean can = false;

        while (board.isInside(end) && board.getTakenSquares(opponent).contains(end)) {
            end = end.next(d);
            can = true;
        }
        return can && board.isInside(end) && board.getTakenSquares(current).contains(end);
    }

    /**
     * Updates the score of both players If there is only one winner, the empty
     * squares bonus points will be added to his/her score
     */
    void updateScores() {
        checkBonusSquares();

        if (isOver()) {
            Player winner = getWinner();
            winner.setScore(winner.getScore() + board.emptySquares());
        }
    }

    private void checkBonusSquares() {
        int scoreCur = board.score(current);
        int scoreOp = board.score(opponent);

        for (Position posBonus : bonusSquares) {

            if (board.getTakenSquares(current).contains(posBonus)) {
                scoreCur += 3;
            }
            if (board.getTakenSquares(opponent).contains(posBonus)) {
                scoreOp += 3;
            }
        }

        current.setScore(scoreCur);
        opponent.setScore(scoreOp);
    }

    /**
     * Checks who is the winner of the game by comparing both player's score
     *
     * @return the winner of the game
     */
    @Override
    public Player getWinner() {
        return current.getScore() > opponent.getScore() ? current : opponent;
    }

    /**
     * Resets the game by creating a new board, clearing the history of the
     * game, and initializing again the game.
     */
    @Override
    public void reset() {
        board = new Board();
        surrender = false;
        bonusSquares.clear();

        Move.setNb(0);
        initialize();

        notifyObservers();
    }

    /**
     * Sets surrender attribute to true
     */
    @Override
    public void surrender() {
        this.surrender = true;

        notifyObservers();
    }

    /**
     * Swaps players
     */
    @Override
    public void pass() {
        history.add(new Move(current.getName(), "Passe son tour"));
        swapPlayers();

        notifyObservers();
    }

    /**
     * Sets the name of both players
     *
     * @param currentName the name of the current player
     * @param opponentName the name of the opponent player
     */
    @Override
    public void setPlayersNames(String currentName, String opponentName) {
        current.setName(currentName);
        opponent.setName(opponentName);

        if (currentName.equals("")) {
            current.setName("NOIR");
        }
        if (opponentName.equals("")) {
            opponent.setName("BLANC");
        }
    }

}
