package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CalculatorController {

    @FXML
    private Label display;

    @FXML
    private VBox root;

    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";

    @FXML
    public void initialize() {
        root.lookupAll(".button").forEach(node -> {
            Button btn = (Button) node;
            btn.setOnAction(e -> handleButtonPress(btn.getText()));
        });
    }

    private void handleButtonPress(String text) {
        switch (text) {
            case "CLEAR" -> clear();
            case "BCKSPC" -> backspace();
            case "+", "-", "*", "/" -> setOperator(text);
            case "=" -> calculate();
            default -> appendInput(text);
        }
    }

    private void appendInput(String value) {
        if (value.equals(".") && currentInput.contains(".")) return;
        currentInput += value;
        display.setText(currentInput);
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            previousInput = currentInput;
            currentInput = "";
            operator = op;
        }
    }

    private void calculate() {
        if (previousInput.isEmpty() || currentInput.isEmpty()) return;
        try {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> num2 != 0 ? num1 / num2 : 0;
                default -> 0;
            };
            display.setText(formatResult(result));
            currentInput = formatResult(result);
            previousInput = "";
            operator = "";
        } catch (Exception e) {
            display.setText("Error");
        }
    }

    private void clear() {
        currentInput = "";
        previousInput = "";
        operator = "";
        display.setText("0");
    }

    private void backspace() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            display.setText(currentInput.isEmpty() ? "0" : currentInput);
        }
    }

    private String formatResult(double result) {
        if (result == (int) result) {
            return String.valueOf((int) result);
        } else {
            return String.valueOf(result);
        }
    }
}