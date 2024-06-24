package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CesarScreen extends VBox {
    private Stage primaryStage;
    private Cesar cesar = new Cesar();

    public CesarScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.setSpacing(10); // Espacement entre les éléments
        this.setAlignment(Pos.CENTER); // Centrage des éléments verticalement
        this.setPadding(new Insets(20)); // Padding autour du VBox
        this.display();
    }

    public void display() {

        Label textLabel = new Label("Text to encrypt:");
        this.getChildren().add(textLabel);

        TextField textField = new TextField();
        textField.setPrefWidth(300); // Largeur ajustée du champ de texte
        this.getChildren().add(textField);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Encrypt", "Decrypt");
        comboBox.setValue("Encrypt"); // Valeur par défaut
        this.getChildren().add(comboBox);

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

        Label sliderLabel = new Label("Shift amount:");
        this.getChildren().add(sliderLabel);

        Label resultLabel = new Label("Result:");
        this.getChildren().add(resultLabel);

        Label resultText = new Label();
        resultText.setWrapText(true); // Permet le retour à la ligne automatique
        resultText.setMaxWidth(300); // Largeur maximale du texte de résultat
        resultText.setStyle("-fx-border-color: lightgrey; -fx-padding: 10px;");
        this.getChildren().add(resultText);

        Button encryptDecryptButton = new Button("Encrypt/Decrypt");
        this.getChildren().add(encryptDecryptButton);

        Button backButton = new Button("Back to Menu");
        this.getChildren().add(backButton);

        encryptDecryptButton.setOnAction(e -> {
            String mode = comboBox.getValue();
            String inputText = textField.getText().trim();
            int shiftAmount = (int) slider.getValue();

            if (inputText.isEmpty()) {
                showAlert("Error: Please enter some text");
            } else if (!inputText.matches("[a-zA-Z]+")) {
                showAlert("Error: Please enter only alphabetic characters");
            } else {
                String encryptedText = cesar.Encrypt_And_Decrypt(mode, inputText, shiftAmount);
                resultText.setText(encryptedText);
            }
        });

        backButton.setOnAction(e -> {
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
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encryption Cesar Cipher");
        primaryStage.show();
    }
}
