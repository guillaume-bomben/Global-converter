package com.example;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CesarAndTranslateScreen extends VBox {
    private Stage primaryStage;
    private Cesar cesar = new Cesar();
    private Translate translate = new Translate();

    public CesarAndTranslateScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.setSpacing(10); // Espacement entre les éléments
        this.setAlignment(Pos.CENTER); // Centrage des éléments verticalement
        this.setPadding(new Insets(20)); // Padding autour du VBox
        this.display();
    }

    public void display() {

        Label textLabel = new Label("Text to process:");
        this.getChildren().add(textLabel);

        TextField textField = new TextField();
        textField.setPrefWidth(300); // Largeur ajustée du champ de texte
        this.getChildren().add(textField);

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(25);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(5);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        this.getChildren().add(slider);
        


        Label sliderLabel = new Label("Cesar Shift:"+ (int) slider.getValue());
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderLabel.setText("Cesar Shift:" + (int) slider.getValue());
        });
        this.getChildren().add(sliderLabel);

        // Options for ChoiceBoxes
        String[] options = {"Text", "Hexadecimal", "Decimal", "Octal", "Binary"};

        // First ChoiceBox
        ChoiceBox<String> choiceBox1 = new ChoiceBox<>(FXCollections.observableArrayList(options));
        choiceBox1.setValue("Text"); // Valeur par défaut
        this.getChildren().add(choiceBox1);

        // Second ChoiceBox
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>(FXCollections.observableArrayList(options));
        choiceBox2.setValue("Hexadecimal"); // Valeur par défaut
        this.getChildren().add(choiceBox2);

        Label resultLabel = new Label("Result:");
        this.getChildren().add(resultLabel);

        Label resultText = new Label();
        resultText.setWrapText(true); // Permet le retour à la ligne automatique
        resultText.setMaxWidth(300); // Largeur maximale du texte de résultat
        resultText.setStyle("-fx-border-color: lightgrey; -fx-padding: 10px;");
        this.getChildren().add(resultText);

        Button translateButton = new Button("Translate");
        this.getChildren().add(translateButton);

        Button backButton = new Button("Back to Menu");
        this.getChildren().add(backButton);

        translateButton.setOnAction(event -> {
            if (textField.getText().isEmpty()) {
                showAlert("Please enter text to process");
                return;
            }

            String inputText = textField.getText();
            int shiftAmount = (int) slider.getValue();
            String mode1 = choiceBox1.getValue();
            String mode2 = choiceBox2.getValue();

            if (mode1.equals("Text") && !mode2.equals("Text")) {
                // Cesar Encrypt + Translate
                if (!cesar.isValidInput(inputText)) {
                    showAlert("Please enter valid alphabetic text");
                    return;
                }
                String encryptedText = cesar.Encrypt_And_Decrypt("Encrypt", inputText, shiftAmount);
                String translatedText = translate.textTranslate(encryptedText, mode1, mode2);
                resultText.setText(translatedText);
            } else if (mode2.equals("Text") && !mode1.equals("Text")) {
                // Translate + Cesar Decrypt
                String translatedText = translate.textTranslate(inputText, mode1, mode2);
                String decryptedText = cesar.Encrypt_And_Decrypt("Decrypt", translatedText, shiftAmount);
                resultText.setText(decryptedText);
            } else {
                // Default: Cesar Encrypt only
                String encryptedText = cesar.Encrypt_And_Decrypt("Encrypt", inputText, shiftAmount);
                resultText.setText(encryptedText);
            }
        });

        backButton.setOnAction(event -> {
            Menu menu = new Menu();
            menu.show(primaryStage);
            primaryStage.setScene(menu.getScene());
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void show(Stage primaryStage) {
        Scene scene = new Scene(this, 400, 400); // Ajustement de la taille de la scène
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cesar and Translate");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
