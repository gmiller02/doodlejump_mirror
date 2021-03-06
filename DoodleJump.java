package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;


public class DoodleJump {
    private Rectangle _doodle;
    public DoodleJump(BorderPane _root) {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(300, 500);
        gamePane.setStyle("-fx-background-color: #FFFFFF");
        _root.setCenter(gamePane);

        _doodle = new Rectangle();

        Pane buttonPane = new Pane();
        Button quit = new Button();
        quit.setText("Quit");
        quit.setOnAction(new DoodleJump.ClickHandler());
        buttonPane.getChildren().add(quit);

        this.setUpTimeline();
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
}
