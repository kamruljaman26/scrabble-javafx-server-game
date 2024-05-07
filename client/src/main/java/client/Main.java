package client;

import client.view.Board;
import client.model.Pos;
import client.view.Game;
import client.view.Tile;
import client.view.TilePile;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Game root = new Game();

        // Show stage
        Scene scene = new Scene(root, 573, 850);
        primaryStage.setTitle("Scrabble Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
