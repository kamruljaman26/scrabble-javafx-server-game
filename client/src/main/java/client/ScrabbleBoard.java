package client;

import client.model.Board;
import client.model.Pos;
import client.model.Tile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScrabbleBoard extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.valueOf("#050A30"),
                CornerRadii.EMPTY, new Insets(0))));

        GridPane grid = new GridPane();

        Board board = new Board(15);
        Board.MULTIPLIER[][] multiplier = board.getMultiplier();

        // Creating the board
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {

                Tile tile = new Tile(grid,
                        multiplier[row][col],
                        'c',
                        5,
                        new Pos(row, col)
                );
//                Text text = new Text();
                GridPane.setMargin(tile, new Insets(1.5));

                // Adding elements to the grid
                grid.add(tile, col, row);
            }
        }

        // Control buttons
        HBox controlButtons = new HBox(10);
        Button btnResign = new Button("Resign");
        Button btnSkip = new Button("Skip");
        Button btnSwap = new Button("Swap");
        Button btnSubmit = new Button("Submit");
        controlButtons.getChildren().addAll(btnResign, btnSkip, btnSwap, btnSubmit);

        // Adding to root
        root.setCenter(grid);
        root.setBottom(controlButtons);

        Scene scene = new Scene(root, 800, 850);
        primaryStage.setTitle("Scrabble Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
