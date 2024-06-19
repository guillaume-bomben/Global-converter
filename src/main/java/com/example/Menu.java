package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Menu extends Pane {

    public Menu() {
        this.display();
    }

    public void display() {
        int buttonWidth = 120; 
        int buttonHeight = 10;
        int xAlign = 140;

        Label label = new Label("Choose an option:");
        label.setLayoutX(xAlign);
        label.setLayoutY(50);
        this.getChildren().add(label);

        Button ASCIIButton = new Button("ASCII");
        ASCIIButton.setPrefSize(buttonWidth, buttonHeight);
        ASCIIButton.setLayoutX(xAlign);
        ASCIIButton.setLayoutY(75);
        this.getChildren().add(ASCIIButton);

        Button cesarButton = new Button("Cesar");
        cesarButton.setPrefSize(buttonWidth, buttonHeight);
        cesarButton.setLayoutX(xAlign);
        cesarButton.setLayoutY(125);
        this.getChildren().add(cesarButton);

        Button cesarAndASCIIButton = new Button("Cesar and ASCII");
        cesarAndASCIIButton.setPrefSize(buttonWidth, buttonHeight);
        cesarAndASCIIButton.setLayoutX(xAlign);
        cesarAndASCIIButton.setLayoutY(175);
        this.getChildren().add(cesarAndASCIIButton);

        Button quitButton = new Button("Quit");
        quitButton.setPrefSize(buttonWidth, buttonHeight);
        quitButton.setLayoutX(xAlign);
        quitButton.setLayoutY(225);
        this.getChildren().add(quitButton);

        ASCIIButton.setOnAction(e -> {
            TranslateScreen translate = new TranslateScreen((Stage) this.getScene().getWindow());
            translate.show((Stage) this.getScene().getWindow()); 
        });
        cesarButton.setOnAction(e -> {
            CesarScreen cesar = new CesarScreen((Stage) this.getScene().getWindow());
            cesar.show((Stage) this.getScene().getWindow());
        });

        cesarAndASCIIButton.setOnAction(e -> {
            return; 
            /* ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             * A FAIRE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             */ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        });

        quitButton.setOnAction(e -> {
            System.exit(0);
        });
    }

    public void show(Stage primaryStage) {
        Scene scene = new Scene(this, 400, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}
