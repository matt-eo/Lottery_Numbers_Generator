package org.matt;


import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    private final NumbersGenerator numbersGenerator = new NumbersGenerator("EURO_MILLIONS");
    private String numbersDB;
    //private DataManager dataManager = new DataManager();
    //private DataManagerPostgres dataManager = new DataManagerPostgres();
    //private String dateDB;

    public void generate() {
        HBox elements = (HBox) App.getScene().lookup("#hboxNumbers");
        Label modeLabel = (Label) App.getScene().lookup("#modeLabel");

        if (modeLabel.getText().equals("SET_FOR_LIFE")) {
            numbersGenerator.setMode("SET_FOR_LIFE");
        } else {
            numbersGenerator.setMode("EURO_MILLIONS");
        }

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

    public void verifyNumbers() {
        saveNumbersToString();
        if (CheckNumbers.isPresent(numbersDB)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("WARNING: YOU ALREADY PLAYED THIS NUMBERS");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ALL GOOD!! YOU NEVER PLAYED THESE NUMBERS :)");
            alert.show();
        }
    }

    public void switchMode() {
        RadioButton radioButton = (RadioButton) App.getScene().lookup("#radioButton");
        Label modeLabel = (Label) App.getScene().lookup("#modeLabel");

        if (radioButton.isSelected()) {
            modeLabel.setText("SET_FOR_LIFE");
        } else {
            modeLabel.setText("EUROMILLIONS");
        }
    }

    public void switchToEuroMillFromMenu() {
        Label modeLabel = (Label) App.getScene().lookup("#modeLabel");
        modeLabel.setText("EUROMILLIONS");
    }

    public void switchToSetForLifeFromMenu() {
        Label modeLabel = (Label) App.getScene().lookup("#modeLabel");
        modeLabel.setText("SET_FOR_LIFE");
    }


    private void saveTextToFile(String text, File file) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(text + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Also saving to database
        //dataManager.insertData(numbersDB, dateDB);
    }

    public void saveAsPlayed() {
        File file = new File("my-played-numbers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String text = saveNumbersToString();
        saveTextToFile(text, file);
    }

    private String saveNumbersToString() {
        HBox elements = (HBox) App.getScene().lookup("#hboxNumbers");
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : elements.getChildren()) {
            Label label = (Label) node;
            stringBuilder.append(label.getText()).append(" ");
        }
        numbersDB = stringBuilder.toString();
        LocalDate date = LocalDate.now();
        //dateDB = date.toString();
        stringBuilder.append(" - ").append(date);
        return stringBuilder.toString();
    }

    public void exit() {
        //dataManager.closeConnection();
        Platform.exit();
    }

    /*@FXML
    public void saveAsPlayed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(exFilter);

        File file = fileChooser.showSaveDialog(new Stage());

        String toSave = saveNumbersToString();

        if (file != null) {
            saveTextToFile(toSave, file);
        }

        event.consume();
    }*/
}
