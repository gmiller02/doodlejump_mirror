package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.lang.Math;


public class DoodleJump {
    private Doodle _realDoodle;
    private ArrayList<Platform> _doodlejumpPlatforms;
    private Pane _gamePane;
    private Rectangle _bottom;

    public DoodleJump(BorderPane _root, HBox buttonPane, Pane doodlePane, Pane platformPane) {
        _gamePane = new Pane();
        _gamePane.setPrefSize(300, 500);
        _gamePane.setStyle("-fx-background-color: #FFFFFF");
        _root.setCenter(_gamePane);


        _realDoodle = new Doodle(_gamePane);
        _root.getChildren().addAll(doodlePane);
        _gamePane.getChildren().addAll(doodlePane);

        _bottom = new Rectangle(0,500, 400, 200);
        _bottom.setFill(Color.GRAY);
        _gamePane.getChildren().addAll(_bottom);



        _doodlejumpPlatforms = new ArrayList<Platform>();

        Platform topPlatform = new Platform(_gamePane);
        topPlatform.setXLoc(10);
        topPlatform.setYLoc(400);
        _doodlejumpPlatforms.add(topPlatform);


        Button quit = new Button();
        quit.setText("Quit");
        quit.setOnAction(new ClickHandler());
        buttonPane.getChildren().add(quit);
        _root.setBottom(buttonPane);
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);

        Label label = new Label();
        label.setText("Game Over");
        //labelPane.getChildren().add(label);
        label.setVisible(false);
        if (_realDoodle.getYLoc() > 600) {
            label.setVisible(true);
        }
        //labelPane.setAlignment(Pos.CENTER);

        DoodleJump.this.generatePlatforms();

        this.setUpTimeline();

    }




    public void bounce() {
        for (Platform platform: _doodlejumpPlatforms) {
            if (_realDoodle.getVelocity() > 0 && platform.getRect().intersects(_realDoodle.getXLoc(), _realDoodle.getYLoc(),Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT)) {
                _realDoodle.setVelocity(Constants.REBOUND_VELOCITY);
            }
        }
    }


    public void generatePlatforms() {
        Platform topPlatform = _doodlejumpPlatforms.get(_doodlejumpPlatforms.size() - 1);
        double xlow;
        double xhigh;
        double ylow;
        double yhigh;
        double xCoord;
        double yCoord;

        while (topPlatform.getYLoc() > 0) {
            xlow = Math.max(0, topPlatform.getXLoc() - Constants.X_OFFSET);
            xhigh = Math.min(300 - Constants.PLATFORM_WIDTH, topPlatform.getXLoc() + Constants.X_OFFSET);
            xCoord = xlow + Math.random() * (xhigh - xlow);

            ylow = topPlatform.getYLoc() - Constants.Y_OFFSET_MIN;
            yhigh = topPlatform.getYLoc() - Constants.Y_OFFSET_MAX;
            yCoord = ylow + Math.random() * (yhigh - ylow);

            Platform platform = new Platform(_gamePane);
            platform.setXLoc(xCoord);
            platform.setYLoc(yCoord);
            _doodlejumpPlatforms.add(platform);
            topPlatform = platform;

        }

    }

    public void verticalScrolling() {
        double yAboveMidpoint;
        double platformPosition;

        for (Platform platform: _doodlejumpPlatforms) {
            if (_realDoodle.getYLoc() < 250) {
                yAboveMidpoint = 250 - _realDoodle.getYLoc();
                _realDoodle.setYLoc(250);
                platformPosition = platform.getYLoc() + yAboveMidpoint;
                platform.setYLoc(platformPosition);
            }
            else if (_realDoodle.getYLoc() > 250) {
                _realDoodle.setPosition();
            }

            if (platform.getYLoc() > 500) {
                _doodlejumpPlatforms.remove(platform);
            }
        }

        DoodleJump.this.generatePlatforms();
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

            //DoodleJump.this.verticalScrolling();
        }

    }



    private class ClickHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent Event) {
            System.exit(0);
        }
    }


}
