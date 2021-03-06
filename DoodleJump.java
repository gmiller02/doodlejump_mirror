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

/**
 * This is my game class, which is called DoodleJump. It contains the following instance variables: an instancce of the
 * Doodle class called _realDoodle, an arrayList called _doodlejumpPlatforms, a pane called _gamePane, and a rectangle
 * called _bottom.
 * It also instantiates the following local variables: A platform called topPlatform which I use to generate the other
 * platforms, a button called quit which quits the program, and a label called label which is set to visible when the doodle
 * falls off the screen.
 */


public class DoodleJump {
    private Doodle _realDoodle;
    private ArrayList<Platform> _doodlejumpPlatforms;
    private Pane _gamePane;
    private Timeline _timeline;

    public DoodleJump(BorderPane _root, HBox buttonPane, HBox labelPane, Pane doodlePane, Pane platformPane) {
        _gamePane = new Pane();
        _gamePane.setPrefSize(300, 500);
        _gamePane.setStyle("-fx-background-color: #FFFFFF");
        _root.setCenter(_gamePane);


        _realDoodle = new Doodle(_gamePane);



        _doodlejumpPlatforms = new ArrayList<Platform>();

        Platform topPlatform = new Platform(_gamePane);
        topPlatform.setXLoc(150);
        topPlatform.setYLoc(400);
        _doodlejumpPlatforms.add(topPlatform);


        Button quit = new Button();
        quit.setText("Quit");
        quit.setOnAction(new ClickHandler());
        buttonPane.getChildren().add(quit);
        _root.setBottom(buttonPane);
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);

        DoodleJump.this.generatePlatforms();

        this.setUpTimeline();

    }

    /**
     * This method is responsible for making my 'Game Over' screen. It contains a label that is only visible when the
     * doodle falls below the bottom of the screen.
     */

    public void checkIfGameIsOver() {
        if (_realDoodle.getYLoc() > 600) {
            Label label = new Label();
            label.setText("Game Over");
            _gamePane.getChildren().add(label);
            label.setLayoutX(150);
            label.setLayoutY(300);
            _timeline.pause();
        }
    }

    /**
     * This method animates the Doodle and makes it able to bounce off the platforms. For every plaform in the Arraylist,
     * if the doodle is falling and it intersects with an instance of the platform, it's normal velocity, which was set
     * to zero in the Doodle class, will be changed to the rebound velocity and it will bounce upwards.
     */


    public void bounce() {
        for (Platform platform: _doodlejumpPlatforms) {
            if (_realDoodle.getVelocity() > 0 && platform.getRect().intersects(_realDoodle.getXLoc(), _realDoodle.getYLoc(),Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT)) {
                _realDoodle.setVelocity(Constants.REBOUND_VELOCITY);
            }
        }
    }

    /**
     * This method is responsible for using the ArrayList to generate an infinite amount of platforms for the Doodle
     * to bounce off of. It uses a while statement to caculate a random x and y value, and then a new platform is added
     * to the game and the ArrayList at the randomly generated x and y value. It creates several doubles that are used
     * in the caculations.
     */


    public void generatePlatforms() {
        // The below statement makes sure that the topPlatform is the first platform generated in the ArrayList
        Platform topPlatform = _doodlejumpPlatforms.get(_doodlejumpPlatforms.size() - 1);
        double xlow;
        double xhigh;
        double ylow;
        double yhigh;
        double xCoord;
        double yCoord;

        while (topPlatform.getYLoc() > 0) {
            // The below statements all set the value of the above double variables. These variables hold the boundries
            // in which the platforms have to be generated between, and the actual coordinates of the new platforms.
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

    /**
     * This class is responsible for animating the scrolling of my platforms. It contains two doubles, one of which stands
     * for the distance above the midpoint, and the other stands for the new position of the platform. If the platform
     * reaches a certain point on the screen, all of the platforms will scroll down and new platforms will be added and
     * removed from the array as the game scrolls.
     */

    public void verticalScrolling() {
        double yAboveMidpoint;
        double platformPosition;

        // the bottom if statement iterates through the platforms when the doodle is above the midpoint. if it is,
        // the platforms will move the same distance above the midpoint as the doodle.
        if (_realDoodle.getYLoc() <= 250) {
            yAboveMidpoint = 250 - _realDoodle.getYLoc();
            for (Platform platform: _doodlejumpPlatforms) {
                platformPosition = platform.getYLoc() + yAboveMidpoint;
                platform.setYLoc(platformPosition);
            }
            _realDoodle.setYLoc(250);
        }
        else if (_realDoodle.getYLoc() > 250) {
            _realDoodle.setPosition();
        }

        // The bottom loop removes the platforms graphically and logically once the platforms fall out of the screen's
        // bounds.

        while (_doodlejumpPlatforms.get(0).getYLoc() > 600) {
            _gamePane.getChildren().remove(_doodlejumpPlatforms.get(0).getRect());
            _doodlejumpPlatforms.remove(0);
        }

        DoodleJump.this.generatePlatforms();
    }


    /**
     * This method sets up the timeline of my game and instantiates the TimeHandler class. Each KeyFame of my animation
     * lasts for 0.016 of a second and this animation goes on indefinitly.
     */

    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new DoodleJump.TimeHandler());
        _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    /**
     * This class extends the EventHandler class and is responsible for managing every frame of my game function.
     * At every step of my TimeHandler, the position and velocity of my doodle is caculated, and it is able to bounce off
     * of the platforms. This class also calls the vertical scroling method, which makes the game move upwards as the
     * doodle moves upwards.
     */


    private class TimeHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

            _realDoodle.cauclateVelocity();
            _realDoodle.caculatePosition();

            DoodleJump.this.bounce();

            DoodleJump.this.verticalScrolling();

            DoodleJump.this.checkIfGameIsOver();

        }

    }

    /**
     * This class extends the EventHandler class and enables the quit button to quit the game when clicked on.
     */

    private class ClickHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent Event) {
            System.exit(0);
        }
    }


}
