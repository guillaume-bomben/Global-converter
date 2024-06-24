package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends VBox {

    public Menu() {
        this.setSpacing(20); // Espacement entre les éléments
        this.setAlignment(Pos.CENTER); // Centrer les éléments verticalement
        this.setPadding(new Insets(20)); // Padding autour du VBox
        this.display();
    }

    public void display() {
        Label label = new Label("Choose an option:");
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        this.getChildren().add(label);

        Button ASCIIButton = createMenuButton("ASCII");
        ASCIIButton.setOnAction(e -> {
            TranslateScreen translate = new TranslateScreen((Stage) this.getScene().getWindow());
            translate.show((Stage) this.getScene().getWindow());
        });
        this.getChildren().add(ASCIIButton);

        Button cesarButton = createMenuButton("Cesar");
        cesarButton.setOnAction(e -> {
            CesarScreen cesar = new CesarScreen((Stage) this.getScene().getWindow());
            cesar.show((Stage) this.getScene().getWindow());
        });
        this.getChildren().add(cesarButton);

        Button cesarAndASCIIButton = createMenuButton("Cesar and ASCII");
        cesarAndASCIIButton.setOnAction(e -> {
            CesarAndTranslateScreen cesarAndTranslate = new CesarAndTranslateScreen((Stage) this.getScene().getWindow());
            cesarAndTranslate.show((Stage) this.getScene().getWindow());
        });
        this.getChildren().add(cesarAndASCIIButton);

        Button translateFilesButton = createMenuButton("Translate files");
        translateFilesButton.setOnAction(e -> {
            TranslateFilesScreen translateFiles = new TranslateFilesScreen((Stage) this.getScene().getWindow());
            translateFiles.show((Stage) this.getScene().getWindow());
        });
        this.getChildren().add(translateFilesButton);

        Button quitButton = createMenuButton("Quit");
        quitButton.setOnAction(e -> {
            System.exit(0);
        });
        this.getChildren().add(quitButton);
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200); // Largeur fixe pour les boutons
        button.setPrefHeight(40); // Hauteur fixe pour les boutons
        button.setStyle("-fx-font-size: 14px;");
        return button;
    }

    public void show(Stage primaryStage) {
        Scene scene = new Scene(this, 400, 400); // Ajuster la taille de la scène pour une meilleure présentation
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}
