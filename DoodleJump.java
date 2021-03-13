package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class DoodleJump {
    private Doodle _realDoodle;
    private Rectangle _testPlatform;
    private ArrayList<Platform> _doodlejumpPlatforms;

    public DoodleJump(BorderPane _root, HBox buttonPane, Pane doodlePane) {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(300, 500);
        gamePane.setStyle("-fx-background-color: #FFFFFF");
        _root.setCenter(gamePane);


        _realDoodle = new Doodle(gamePane);
        _root.getChildren().addAll(doodlePane);
        gamePane.getChildren().addAll(doodlePane);

        _testPlatform = new Rectangle(10,250,Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        _testPlatform.setFill(Color.BLUEVIOLET);
        gamePane.getChildren().addAll(_testPlatform);

        _doodlejumpPlatforms = new ArrayList<Platform>();
        for (int i=0; i<20; i++) {
            _doodlejumpPlatforms.add(new Platform());
        }

        Button quit = new Button();
        quit.setText("Quit");
        quit.setOnAction(new ClickHandler());
        buttonPane.getChildren().add(quit);
        _root.setBottom(buttonPane);
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);


        this.setUpTimeline();

    }

    public void bounce() {
        if (_realDoodle.getVelocity() > 0 && _testPlatform.intersects(_realDoodle.getXLoc(), _realDoodle.getYLoc(),Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT)) {
            _realDoodle.setVelocity(Constants.REBOUND_VELOCITY);
        }
    }

    public void addPlatforms() {
        
    }



    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new DoodleJump.TimeHandler());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private class TimeHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

            _realDoodle.cauclateVelocity();
            _realDoodle.caculatePosition();

            DoodleJump.this.bounce();

        }

    }



    private class ClickHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent Event) {
            Platform.exit();
        }
    }


}
