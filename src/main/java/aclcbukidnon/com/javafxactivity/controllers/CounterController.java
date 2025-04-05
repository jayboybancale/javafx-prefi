package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CounterController {

    @FXML
    private Label labelCount;

    private int count = 0; // Counter variable to track the count

    @FXML
    protected void onPlusClick() {
        count++; // Increment the counter
        updateLabel(); // Update the label to reflect the new counter value
    }

    @FXML
    protected void onMinusClick() {
        count--; // Decrement the counter
        updateLabel(); // Update the label to reflect the new counter value
    }

    private void updateLabel() {
        labelCount.setText(String.valueOf(count)); // Set the label text to the counter value
    }
}
