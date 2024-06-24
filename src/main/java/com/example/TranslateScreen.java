package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TranslateScreen extends FlowPane {
    private Stage primaryStage;
    private Translate translate = new Translate();

    public TranslateScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.display();
    }

    public void show(Stage primaryStage) {
        Scene scene = new Scene(this, 500, 300); // Adjusted size for better layout
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encryption Cesar Cipher");
        primaryStage.show();
    }

    public void display() {
        VBox mainBox = new VBox(10); // Vertical box to hold all components with spacing
        mainBox.setPadding(new Insets(20)); // Padding around the main VBox


        HBox inputBox = new HBox(10); // Horizontal box for input components
        inputBox.setAlignment(Pos.CENTER_LEFT);

        Label inputLabel = new Label("Sentence to translate:");
        inputLabel.setStyle("-fx-font-weight: bold;");
        inputBox.getChildren().add(inputLabel);

        TextField textField = new TextField("nom");
        textField.setPrefWidth(200); // Adjusted width of text field
        inputBox.getChildren().add(textField);

        ChoiceBox<String> inputModeBox = new ChoiceBox<>();
        inputModeBox.getItems().addAll("Text", "Hexadecimal", "Decimal", "Octal", "Binary");
        inputModeBox.setValue("Text");
        inputBox.getChildren().add(inputModeBox);

        mainBox.getChildren().add(inputBox);

        HBox translateBox = new HBox(10); // Horizontal box for translate components
        translateBox.setAlignment(Pos.CENTER_LEFT);

        Label translateLabel = new Label("Translate mode:");
        translateLabel.setStyle("-fx-font-weight: bold;");
        translateBox.getChildren().add(translateLabel);

        ChoiceBox<String> translateModeBox = new ChoiceBox<>();
        translateModeBox.getItems().addAll("Text", "Hexadecimal", "Decimal", "Octal", "Binary");
        translateModeBox.setValue("Text");
        translateBox.getChildren().add(translateModeBox);

        mainBox.getChildren().add(translateBox);

        Button translateButton = new Button("Translate");
        translateButton.setStyle("-fx-font-size: 14px;");
        mainBox.getChildren().add(translateButton);

        Label answerLabel = new Label("Translation:");
        answerLabel.setStyle("-fx-font-weight: bold;");
        mainBox.getChildren().add(answerLabel);

        Label answer = new Label("");
        answer.setStyle("-fx-border-color: lightgrey; -fx-padding: 10px; -fx-font-size: 16px;");
        mainBox.getChildren().add(answer);

        translateButton.setOnAction(event -> {
            String translatedText = translate.textTranslate(textField.getText(), inputModeBox.getValue(), translateModeBox.getValue());
            if (!translatedText.equals("&")) {
                answer.setText(translatedText);
            } else {
                answer.setText("Error: input doesn't match mode selected");
            }
        });

        Button backButton = new Button("Back to Menu");
        backButton.setStyle("-fx-font-size: 14px;");
        mainBox.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.show(primaryStage);
            primaryStage.setScene(menu.getScene());
        });

        this.getChildren().add(mainBox);
    }
}
