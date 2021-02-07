package org.matt;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

import java.util.List;

public class Controller {

    private NumbersGenerator numbersGenerator = new NumbersGenerator("EURO_MILLIONS");

    public void generate() {
        HBox elements = (HBox) App.getScene().lookup("#hboxNumbers");

        numbersGenerator.setMode("EURO_MILLIONS");
        List<String> numbers = numbersGenerator.getNumbers();

        int counter = 0;
        for (Node node : elements.getChildren()) {
            Label label = (Label) node;
            if (label.getText().equals("*")) {
                continue;
            }
            label.setText(numbers.get(counter));
            counter++;
        }
    }

    public void switchMode() {
        RadioButton radioButton = (RadioButton) App.getScene().lookup("#radioButton");
        Label modeLabel = (Label) App.getScene().lookup("#modeLabel");

        if (radioButton.isSelected()) {
            modeLabel.setText("SET FOR LIFE");
        } else {
            modeLabel.setText("EUROMILLIONS");
        }
    }
}
