package com.example;


import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CesarAndTranslateScreen extends Pane{
    private Stage primaryStage; 
    private Cesar cesar = new Cesar();
    private Translate translate = new Translate();

    public CesarAndTranslateScreen(Stage PrimaryStage) {
        this.primaryStage = PrimaryStage;
        this.display();
    }

    public void display(){
        Label text = new Label("Text to encrypt:");
        text.setLayoutX(10);
        text.setLayoutY(10);
        this.getChildren().add(text);

        TextField textField = new TextField();
        textField.setLayoutX(10);
        textField.setLayoutY(40);
        this.getChildren().add(textField);

        Label label = new Label("0");
        label.setLayoutX(175);
        label.setLayoutY(40);
        this.getChildren().add(label);

        Slider slider = new Slider();
        slider.setLayoutX(200);
        slider.setLayoutY(40);
        slider.setMin(0);
        slider.setMax(25);
        slider.setValue(0);
        this.getChildren().add(slider);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(String.valueOf(newValue.intValue()));
        });

        // Options for ChoiceBoxes
        String[] options = {"Text", "Hexadecimal", "Decimal", "Octal", "Binary"};

        // First ChoiceBox
        ChoiceBox<String> choiceBox1 = new ChoiceBox<>(FXCollections.observableArrayList(options));
        choiceBox1.setLayoutX(10);
        choiceBox1.setLayoutY(80);
        choiceBox1.setValue("Text"); // Default value to ensure at least one is "Text"
        this.getChildren().add(choiceBox1);

        // Second ChoiceBox
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>(FXCollections.observableArrayList(options));
        choiceBox2.setLayoutX(150);
        choiceBox2.setLayoutY(80);
        choiceBox2.setValue("Hexadecimal"); // Default to any other value
        this.getChildren().add(choiceBox2);

        // Add listeners to ensure one of them is always "Text"
        choiceBox1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("Text") && !choiceBox2.getValue().equals("Text")) {
                choiceBox2.setValue("Text");
            }
        });

        choiceBox2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("Text") && !choiceBox1.getValue().equals("Text")) {
                choiceBox1.setValue("Text");
            }
        });

        Label result = new Label("Result:");
        result.setLayoutX(10);
        result.setLayoutY(110);
        this.getChildren().add(result);

        Label resultText = new Label();
        resultText.setLayoutX(50);
        resultText.setLayoutY(110);
        this.getChildren().add(resultText);

        Button backButton = new Button("Back to Menu");
        backButton.setLayoutX(300);
        backButton.setLayoutY(150);
        this.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.show(primaryStage); 
            primaryStage.setScene(menu.getScene()); 
        });

        Button button = new Button("Translate");
        button.setLayoutX(10);
        button.setLayoutY(150);
        this.getChildren().add(button);

        button.setOnAction(event -> {
            if (textField.getText().isEmpty()) {
                resultText.setText("Please enter a text to translate");
                return;
            }
            else if (choiceBox1.getValue().equals("Text") && !choiceBox2.getValue().equals("Text")) {
                if (!cesar.isValidInput(textField.getText())) {
                    resultText.setText("Please enter a valid text to translate");
                    return;
                }
                else{
                String translatedText = cesar.Encrypt_And_Decrypt("Encrypt", textField.getText(), (int) slider.getValue());
                String resultString = translate.textTranslate(translatedText, choiceBox1.getValue(), choiceBox2.getValue());
                resultText.setText(resultString);
                }
            }
            else if (choiceBox2.getValue().equals("Text") && !choiceBox1.getValue().equals("Text")) {
                String translatedText = translate.textTranslate(textField.getText(), choiceBox1.getValue(), choiceBox2.getValue());
                System.out.println(translatedText);
                String resultString = cesar.Encrypt_And_Decrypt("Decrypt", translatedText, (int) slider.getValue());
                resultText.setText(resultString);
            }
            else {
                String translatedText = cesar.Encrypt_And_Decrypt("Encrypt", textField.getText(), (int) slider.getValue());
                resultText.setText(translatedText);
            }
        });
    }

    public void show(Stage primaryStage) {
        Scene scene = new Scene(this, 400, 300);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cesar and Translate");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
