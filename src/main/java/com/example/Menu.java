package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Menu extends Pane {

    public Menu() {
        this.display();
    }

    public void display() {

        Button cesarButton = new Button("Cesar");
        cesarButton.setLayoutX(175);
        cesarButton.setLayoutY(100);
        this.getChildren().add(cesarButton);



        cesarButton.setOnAction(e -> {
            Cesar cesar = new Cesar((Stage) this.getScene().getWindow());
            cesar.show((Stage) this.getScene().getWindow());
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
