package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CesarScreen extends Pane {
    private Stage primaryStage; 
    private Cesar cesar = new Cesar();


    public CesarScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

        Button backButton = new Button("Back to Menu");
        backButton.setLayoutX(300);
        backButton.setLayoutY(40);

        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.show(primaryStage); 
            primaryStage.setScene(menu.getScene()); 
        });


        this.getChildren().addAll(text, textField, button, comboBox, label, slider, result, resultText,backButton);

        button.setOnAction(e -> {
            if (comboBox.getValue() == null || textField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Error: Please fill all the fields");
                alert.showAndWait();
            } else if (!cesar.isValidInput(textField.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Error: Please enter only alphabetic characters");
                alert.showAndWait();
            } else {
                resultText.setText(cesar.Encrypt_And_Decrypt(comboBox.getValue(), textField.getText(), slider.valueProperty().intValue()));
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
}
