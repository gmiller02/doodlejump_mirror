package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;

/**
 * This class creates the object Doodle. Doodle is a simple rectangle shape, so this class mainly contains the different
 * methods that help animate the Doodle, such as several getter and setter methods that caculate its position and velocity.
 * This class also contains a Keyhandler that allows the Doodle to be moved with arrow keys. Finally, it also contains an instance
 * variable called _velocity that helps to caculate the Doodle's velocity. 
 */

public class Doodle {
    private Rectangle _doodle;
    private double _velocity;


    public Doodle(Pane doodlePane) {
        _doodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        _doodle.setFill(Color.LIMEGREEN);
        doodlePane.getChildren().addAll(_doodle);

        doodlePane.addEventHandler(KeyEvent.KEY_PRESSED, new Doodle.KeyHandler());
        doodlePane.setFocusTraversable(true);

        this.setXLoc(150);
        this.setYLoc(350);

        _velocity = 0;

    }

    /**
     * Creates the variable x, which corresponds to the value of the doodle's x position, and sets said position.
     * @param x
     */

    public void setXLoc(double x) {
        _doodle.setX(x);
    }

    /**
     * Returns the value of the Doodle's x position so that other classes are able to access the value.
     * @return
     */

    public double getXLoc() {
        return _doodle.getX();
    }

    /**
     Creates the variable y, which corresponds to the value of the doodle's y position, and sets said position.
     */

    public void setYLoc(double y) {_doodle.setY(y);}

    /**
     * Returns the value of the Doodle's y position so that other classes are able to access the value.
     * @return
     */

    public double getYLoc() {return _doodle.getY();}

    /**
     * Sets the value of the _velocity instance variable to a velocity caculated using the given constants.
     */

    public void cauclateVelocity() {
        _velocity = _velocity + Constants.GRAVITY * Constants.DURATION;
    }

    /**
     * Uses the instance variable _velocity to caculate the potential position of the Doodle's y position.
     */

    public void caculatePosition() {Doodle.this.setYLoc(this.getYLoc() + _velocity * Constants.DURATION);
    }

    /**
     * Sets the y position of the Doodle to the value caculated in the caculatePosition method.
     */

    public void setPosition() {
        this.caculatePosition();
    }

    /**
     * Sets the instance variable _velocity equal to a double called v.
     * @param v
     */

    public void setVelocity(double v) {
        _velocity = v;
    }

    /**
     * Returns the instance variable _velocity so it can be accesed by other classes.
     * @return
     */

    public double getVelocity() {
        return _velocity;
    }

    /**
     * This KeyHandler extends the EventHandler class and is responsible for making the Doodle move right when the right
     * arrow key is pressed and move left when the left arrow key is pressed. It also uses the x getter method to ensure the
     * Doodle wraps around and comes back onto the screen when it leaves the window.
     */


    private class KeyHandler implements EventHandler<KeyEvent> {

        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();

            switch (keyPressed) {
                case LEFT:
                    Doodle.this.setXLoc(Doodle.this.getXLoc() - 30);
                    break;
                case RIGHT:
                    Doodle.this.setXLoc(Doodle.this.getXLoc() + 30);
                    break;
            }
            e.consume();
            if (Doodle.this.getXLoc() < 0) {
                _doodle.setX(499);
            }
            if (Doodle.this.getXLoc() > 500) {
                _doodle.setX(1);
            }
        }
    }
}
