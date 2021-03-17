package doodlejump;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class instantiantes my DoodleJump class, which is my game class. It instantiates and passes in the panes that make
 * up my game into my DoodleJump class as well, including the 'main' pane that I add all the other panes to, _root.
 */

public class PaneOrganizer {
    private BorderPane _root;

    public PaneOrganizer() {
        _root = new BorderPane();
        _root.setStyle("-fx-background-color: #FFFFFF;");
        new DoodleJump(_root, new HBox(), new Pane(), new Pane());
    }

    /**
     * This method returns _root so that root can be accessed in other classes.
     */

    public BorderPane getRoot() {
        return _root;
    }


}
