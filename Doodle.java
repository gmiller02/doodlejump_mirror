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
import javafx.util.Duration;

public class Doodle {
    private Rectangle _doodle;
    private double _velocity;

    public Doodle(Pane doodlePane) {
        _doodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        _doodle.setFill(Color.LIMEGREEN);
        doodlePane.getChildren().addAll(_doodle);

        doodlePane.addEventHandler(KeyEvent.KEY_PRESSED, new Doodle.KeyHandler());
        doodlePane.setFocusTraversable(true);

        this.setXLoc(10);

    }

    public void setXLoc(double x) {
        _doodle.setX(x);
    }

    public double getXLoc() {
        return _doodle.getX();
    }

    public void setYLoc(double y) {_doodle.setY(y);}

    public double getYLoc() {return _doodle.getY();}

    public void cauclateVelocity() {
        _velocity = _velocity + Constants.GRAVITY * Constants.DURATION;
    }


    public void caculatePosition() {
        Doodle.this.setYLoc(this.getYLoc() + _velocity * Constants.DURATION);
    }

    public void setVelocity(double v) {
        _velocity = v;

    }


    public double getVelocity() {
        return _velocity;
    }



    private class KeyHandler implements EventHandler<KeyEvent> {

        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();

            switch (keyPressed) {
                case LEFT:
                    Doodle.this.setXLoc(Doodle.this.getXLoc() - 20);
                    break;
                case RIGHT:
                    Doodle.this.setXLoc(Doodle.this.getXLoc() + 20);
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
