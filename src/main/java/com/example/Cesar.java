package com.example;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Cesar extends Pane{
    private final ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));

    public Cesar() {
        this.display();
    }

    public void display() {
        Label text = new Label("Text to encrypt:");
        text.setLayoutX(10);
        text.setLayoutY(10);

        TextField textField = new TextField();
        textField.setLayoutX(10);
        textField.setLayoutY(40);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Encrypt", "Decrypt");
        comboBox.setLayoutX(10);
        comboBox.setLayoutY(70);

        Button button = new Button("Encrypt/Decrypt");
        button.setLayoutX(175);
        button.setLayoutY(40);

        Label label = new Label("0");
        label.setLayoutX(10);
        label.setLayoutY(100);

        Slider slider = new Slider();
        slider.setLayoutX(30);
        slider.setLayoutY(100);
        slider.setMin(0);
        slider.setMax(25);
        slider.setValue(0);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(String.valueOf(newValue.intValue()));
        });

        Label result = new Label("Result:");
        result.setLayoutX(10);
        result.setLayoutY(130);

        Label resultText = new Label();
        resultText.setLayoutX(50);
        resultText.setLayoutY(130);

        this.getChildren().addAll(text, textField, button, comboBox,label, slider, result, resultText);

        button.setOnAction(e -> {
            if(comboBox.getValue() == null || textField.getText().isEmpty()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Error: Please fill all the fields");
                alert.showAndWait();
            }
            else if(!isValidInput(textField.getText())) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Error: Please enter only alphabetic characters");
                alert.showAndWait();
            }
            else{
                resultText.setText(Encrypt_And_Decrypt(comboBox.getValue(), textField.getText(), slider.valueProperty().intValue()));
            }
        });
    }


    public void show(Stage primaryStage) {
        Scene scene = new Scene(this, 400, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encryption Cesar Cipher");
        primaryStage.show();
    }

    public String Encrypt_And_Decrypt(String type, String text, int key) {
        StringBuilder result = new StringBuilder();
        if (type.equals("Encrypt")) {
            for (char character : text.toCharArray()) {
                if (alphabet.contains(character)) {
                    int index = alphabet.indexOf(character);
                    int newIndex = (index + key) % 26;
                    result.append(alphabet.get(newIndex));
                } else {
                    result.append(character); // Caractères non alphabétiques
                }
            }
        } else if (type.equals("Decrypt")) {
            for (char character : text.toCharArray()) {
                if (alphabet.contains(character)){
                    int index = alphabet.indexOf(character);
                    int newIndex = (index - key) % 26;
                    if (newIndex < 0) {
                        newIndex = 26 + newIndex;
                    }
                    result.append(alphabet.get(newIndex));
                } else {
                    result.append(character); // Caractères non alphabétiques
                }
            }
        }
        System.out.println(result.toString());
        return result.toString();
    }

    private boolean isValidInput(String text) {
        return text.matches("[a-zA-Z ]+");
    }
}
