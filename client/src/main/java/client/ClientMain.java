package client;

import client.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        ClientMain.stage = primaryStage;

        // Show stage
        Scene scene = new Scene(new StartVIew(stage), 600, 600);
        primaryStage.setTitle("Scrabble Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
