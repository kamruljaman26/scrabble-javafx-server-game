package client.view;

import client.model.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Game extends HBox {

    public Game() {
        // Root layout
        VBox root = new VBox(10);
        root.setBackground(new Background(new BackgroundFill(Color.valueOf("#DDDDDD"),
                CornerRadii.EMPTY, new Insets(0))));

        /*
         * Board
         */
        Board board = new Board();

        /*
         * Create TileBox
         */
        Tile tile = new Tile(board, Board.MULTIPLIER.NO, 'A', 5, new Pos(4, 4));
        Tile tile2 = new Tile(board, Board.MULTIPLIER.NO, 'B', 5, new Pos(4, 4));

        // create and int tile pile
        TilePile tilesPile = new TilePile();
        tilesPile.addTile(tile);
        tilesPile.addTile(tile2);

        // Control buttons
        HBox controlButtons = new HBox(10);
        controlButtons.setAlignment(javafx.geometry.Pos.CENTER);
        Button btnResign = createStyledButton("Resign");
        Button btnSkip = createStyledButton("Skip");
        Button btnSwap = createStyledButton("Swap");
        Button btnSubmit = createStyledButton("Submit");
        controlButtons.getChildren().addAll(btnResign, btnSkip, btnSwap, btnSubmit);

        // Adding to root
        root.getChildren().addAll(board, tilesPile, controlButtons);
        getChildren().addAll(root);
    }

    public Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(120);
        button.setMinHeight(40);
        button.setStyle("-fx-background-color: black; " +    // Base color
                "-fx-background-radius: 5; " +      // Rounded corners
                "-fx-text-fill: white;");            // Text color

        // Change style on hover using CSS pseudo-class
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: white; " +
                "-fx-background-radius: 5; " +
                "-fx-text-fill: black;"));

        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 5; " +
                "-fx-text-fill: white;"));

        return button;
    }

}
