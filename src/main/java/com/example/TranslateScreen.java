package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TranslateScreen extends FlowPane{
    private Stage primaryStage;
    private Translate translate = new Translate();

    public TranslateScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.display();
    }

    public void show(Stage primaryStage) {
        Scene scene = new Scene(this, 400, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encryption Cesar Cipher");
        primaryStage.show();
    }

    public void display(){
        Label label = new Label("Sentence to translate :");
        this.getChildren().add(label);        
        label.setStyle("-fx-padding: 0 10 0 0;");

        TextField text = new TextField("nom");
        this.getChildren().add(text);

        Label cheat = new Label("");
        this.getChildren().add(cheat);        
        cheat.setStyle("-fx-padding: 15px;");

        Button button = new Button("Translate");
        this.getChildren().add(button);

        Label inputLabel = new Label("Input mode :");
        this.getChildren().add(inputLabel);        
        inputLabel.setStyle("-fx-padding: 0 10 0 0;");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Text", "Hexadecimal", "Decimal", "Octal", "Binary");
        choiceBox.setValue("Text");
        this.getChildren().add(choiceBox);

        Label cheat2 = new Label("");
        this.getChildren().add(cheat2);        
        cheat2.setStyle("-fx-padding: 0 200 0 0;");

        Label translateLabel = new Label("Translate mode :");
        this.getChildren().add(translateLabel);        
        translateLabel.setStyle("-fx-padding: 0 10 0 0;");

        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox2.getItems().addAll("Text", "Hexadecimal", "Decimal", "Octal", "Binary");
        choiceBox2.setValue("Text");
        this.getChildren().add(choiceBox2);

        Label cheat3 = new Label("");
        this.getChildren().add(cheat3);        
        cheat3.setStyle("-fx-padding: 0 180 0 0;");

        Label answer = new Label("sdfgzedrf");
        this.getChildren().add(answer);        
        answer.setStyle("-fx-top: 60vh;");

        Button backButton = new Button("Back to Menu");
        this.getChildren().add(backButton);
        backButton.setStyle("-fx-padding: 0 0 0 10;");

        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.show(primaryStage); 
            primaryStage.setScene(menu.getScene()); 
        });

        button.setOnAction(event -> {
            String translatedText = translate.textTranslate(text.getText(), choiceBox.getValue(), choiceBox2.getValue());
            if (translatedText != "&"){
                answer.setText(translatedText);
            }
            else if (translatedText == "&"){
                answer.setText("Error : input doesn't match mode selected");
            }
        });
        
    }
}
