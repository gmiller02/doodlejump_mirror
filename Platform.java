package doodlejump;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

/**
 * This class is called Platform. It is responsible for creating the platforms of my game. It contains an instance of the
 * class Rectangle, which is what the platforms consist of. It also contains several getter and setter methods that allow the
 * platforms to be animated in my game class.
 */

public class Platform {
    public Rectangle _platform;

    public Platform(Pane platformPane) {
        _platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        _platform.setFill(Color.BLUE);
        platformPane.getChildren().add(_platform);
    }

    /**
     * The setXLoc method creates a double called x that stores the platform's x position. The getXLoc method returns the
     * platform's x position so that it can be acessed from other classes.
     * @param x
     */

    public void setXLoc(double x) {
        _platform.setX(x);
    }

    public double getXLoc() {
        return _platform.getX();
    }

    /**
     * The setYLoc method creates a double called y that stores the platform's y position. The getYLoc method returns the
     *      * platform's y position so that it can be acessed from other classes.
     * @param y
     */

    public void setYLoc(double y) {_platform.setY(y);}

    public double getYLoc() {return _platform.getY();}

    /**
     * Some methods in my game class are only able to be accessed from the Rectangle class. My Platform class contains
     * an instance of a rectangle, but isn't technically a rectangle. Therefore, I created this method which makes sure that
     * I can call methods in my game class on a rectangle, but that rectangle will correspond to my platforms.
     * @return
     */

    public Rectangle getRect() {
        return _platform;
    }
}
