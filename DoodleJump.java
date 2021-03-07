package doodlejump;

import cartoon.Cartoon;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;


public class DoodleJump {
    private Rectangle _doodle;
    public DoodleJump(BorderPane _root, HBox buttonPane) {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(300, 500);
        gamePane.setStyle("-fx-background-color: #FFFFFF");
        _root.setCenter(gamePane);

        _doodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        _doodle.setFill(Color.LIMEGREEN);
        _root.getChildren().addAll(_doodle);
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new DoodleJump.KeyHandler());
        this.setXLoc(10);

        Button quit = new Button();
        quit.setText("Quit");
        quit.setOnAction(new ClickHandler());
        buttonPane.getChildren().add(quit);
        _root.setBottom(buttonPane);
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);

        this.setUpTimeline();
    }

    public void setXLoc(double x) {
        _doodle.setX(x);
    }

    public double getXLoc() {
        return _doodle.setX();
    }


    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(2), new DoodleJump.TimeHandler());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private class TimeHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {

        }

    }


    private class ClickHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent Event) {
            Platform.exit();
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {

        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();

            switch (keyPressed) {
                case LEFT:
                    _doodle.setXLoc(_doodle.getXLoc() - 10);

                    break;
                case RIGHT:
                    _doodle.setXLoc(_doodle.getXLoc() + 10);

                    break;
            }
            e.consume();
            if (_doodle.getXLoc() < 0) {
                _doodle.setX(499);
            }
            if (_doodle.getXLoc() > 500) {
                _doodle.setX(1);
            }
        }
    }
}
