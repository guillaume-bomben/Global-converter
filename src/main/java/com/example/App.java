package com.example;

import javafx.application.Application;

import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Cesar enc = new Cesar();
        enc.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }    

}