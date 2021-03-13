package doodlejump;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class Platform {
    public Rectangle _platform;

    public Platform(Pane platformPane) {
        _platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        _platform.setFill(Color.BLUE);
        platformPane.getChildren().addAll(_platform);
    }
}
