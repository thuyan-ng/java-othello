package atl.g52818.othello.main;

import atl.g52818.othello.controller.Controller;
import atl.g52818.othello.model.Game;
import atl.g52818.othello.view.ViewConsole;

/**
 * This class represents the main class of Othello in console mode
 */
public class MainConsole {
    
    public static void main(String[] args) {
        
        Game game = new Game();
        ViewConsole view = new ViewConsole();
        Controller controller = new Controller(game, view);
        controller.initialize();
        controller.startGame();
        
    }
    
}
