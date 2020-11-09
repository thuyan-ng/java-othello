package atl.g52818.othello.view.fx;

import atl.g52818.othello.model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * This class represents the menu of the game 
 */
public class MenuClass extends MenuBar{

    MenuClass(Game game) {
        
        Menu menu = new Menu("File");
        MenuItem exit = new MenuItem("exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        MenuItem newWindow = new MenuItem("open info");
        newWindow.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Window nw = new Window(game);
            }
        });
        
        menu.getItems().addAll(newWindow, exit);
        
        getMenus().addAll(menu);
        
    }
 
}
