package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TrafficLightController {

    public void setManualTrafficLight(ActionEvent actionEvent) {
    }

    private enum TrafficLightColor {
        STOP, // Red
        HOLD, // Yellow
        GO    // Green
    }

    private TrafficLightColor currentColor = TrafficLightColor.STOP;
    private Timeline timeline;

    @FXML
    private Rectangle redLight;
    @FXML
    private Rectangle yellowLight;
    @FXML
    private Rectangle greenLight;
    @FXML
    private Button stopButton;
    @FXML
    private Button holdButton;
    @FXML
    private Button goButton;
    @FXML
    private Button resetButton;

    @FXML
    public void initialize() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> onTimerChange()) // Timer triggers onTimerChange() every 3 seconds.
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // Start the timeline when the app is initialized.
        updateTrafficLights(); // Initialize with STOP (Red).

        // Set button actions for manual override
        stopButton.setOnAction(e -> setManualTrafficLight(TrafficLightColor.STOP));
        holdButton.setOnAction(e -> setManualTrafficLight(TrafficLightColor.HOLD));
        goButton.setOnAction(e -> setManualTrafficLight(TrafficLightColor.GO));

        // Reset button to restart the cycle
        resetButton.setOnAction(e -> resetTrafficLights());
    }

    public void onTimerChange() {
        switch (currentColor) {
            case STOP:
                currentColor = TrafficLightColor.HOLD;
                break;
            case HOLD:
                currentColor = TrafficLightColor.GO;
                break;
            case GO:
                currentColor = TrafficLightColor.STOP;
                break;
        }
        updateTrafficLights();
    }

    private void updateTrafficLights() {
        // Set the lights to their respective colors
        switch (currentColor) {
            case STOP:
                redLight.setFill(Color.RED);
                yellowLight.setFill(Color.GRAY);
                greenLight.setFill(Color.GRAY);
                break;
            case HOLD:
                redLight.setFill(Color.GRAY);
                yellowLight.setFill(Color.YELLOW);
                greenLight.setFill(Color.GRAY);
                break;
            case GO:
                redLight.setFill(Color.GRAY);
                yellowLight.setFill(Color.GRAY);
                greenLight.setFill(Color.GREEN);
                break;
        }
    }

    // Method to manually set the traffic light color
    private void setManualTrafficLight(TrafficLightColor color) {
        currentColor = color;
        updateTrafficLights();
    }

    // Optionally, you can add a method to stop the timeline and reset the lights:
    @FXML
    public void stopTrafficLight() {
        timeline.stop();
        currentColor = TrafficLightColor.STOP;
        updateTrafficLights();
    }

    // Reset the traffic lights to start the cycle over
    @FXML
    private void resetTrafficLights() {
        currentColor = TrafficLightColor.STOP;
        updateTrafficLights();
        timeline.play(); // Restart the timeline after reset
    }
}
