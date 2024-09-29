package org.example.fxdemo.control;

import javafx.scene.control.TextField;

public class FloatingPointTextField extends TextField {

    public FloatingPointTextField() {
        // Add listeners for restricting input to valid floating point characters
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue + " " + newValue);
            if (!newValue.matches("[0-9]*\\.?[0-9]*")) {
                setText(oldValue);
            }
        });

        // Add a focus listener to parse the value on focus lost
        this.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Lost focus
                try {
                    if (!getText().isEmpty()) {
                        double value = Double.parseDouble(getText());
                        setText(String.format("%.2f", value)); // Set formatted value
                    }
                } catch (NumberFormatException e) {
                    setText(""); // Clear invalid input
                }
            }
        });
    }

    @Override
    public void replaceText(int start, int end, String text) {
        System.out.println("replaceText " + start + " " + end + " " + text);
        if (text.matches("[0-9.]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        System.out.println("replaceSelection " + text);
        if (text.matches("[0-9.]") || text.isEmpty()) {
            super.replaceSelection(text);
        }
    }
}