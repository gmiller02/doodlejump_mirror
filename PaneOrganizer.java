package doodlejump;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaneOrganizer {
    private BorderPane _root;


    public PaneOrganizer() {
        _root = new BorderPane();
        _root.setStyle("-fx-background-color: #FFFFFF;");
        new DoodleJump(_root, new HBox());
    }

    public BorderPane getRoot() {
        return _root;
    }


}