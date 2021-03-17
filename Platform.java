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

    public void setXLoc(double x) {
        _platform.setX(x);
    }

    public double getXLoc() {
        return _platform.getX();
    }

    public void setYLoc(double y) {_platform.setY(y);}

    public double getYLoc() {return _platform.getY();}

    public Rectangle getRect() {
        return _platform;
    }
}
