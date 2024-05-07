package client;

import client.model.Board;
import client.model.Pos;
import client.model.Tile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Root layout
        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.valueOf("#050A30"),
                CornerRadii.EMPTY, new Insets(0))));

        /*
         * Board
         */
        Board board = new Board();

        /*
         * Create TileBox
         */
        HBox tilesPile = createHbox();
        Tile tile = new Tile(board,
                Board.MULTIPLIER.DL,
                'c',
                5,
                new Pos(4, 4)
        );

//        tile.setOnDragDetected(event -> {
//            Dragboard db = tile.startDragAndDrop(TransferMode.MOVE);
//            ClipboardContent content = new ClipboardContent();
//            db.setContent(content);
//            event.consume();
//        });

        Tile tile2 = new Tile(board,
                Board.MULTIPLIER.TL,
                'c',
                5,
                new Pos(4, 4)
        );

        tilesPile.getChildren().addAll(tile, tile2);

        // Control buttons
        HBox controlButtons = new HBox(10);
        Button btnResign = new Button("Resign");
        Button btnSkip = new Button("Skip");
        Button btnSwap = new Button("Swap");
        Button btnSubmit = new Button("Submit");
        controlButtons.getChildren().addAll(btnResign, btnSkip, btnSwap, btnSubmit);

        // Adding to root
//        root.getChildren().addAll(board, tilesPile, controlButtons);
        root.getChildren().addAll(board, controlButtons);

        // Show stage
        Scene scene = new Scene(root, 800, 850);
        primaryStage.setTitle("Scrabble Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public HBox createHbox() {
        // Create an HBox
        HBox hbox = new HBox();
        hbox.setSpacing(10); // Set spacing between contained elements

        // Style the HBox
        hbox.setStyle("-fx-background-color: #B2BEB5; " // Ash color
                + "-fx-background-radius: 10; "      // Rounded corners
                + "-fx-padding: 10;");              // Padding inside the HBox

        // Setting margin around the HBox
        HBox.setMargin(hbox, new Insets(10)); // Adds a margin outside the HBox
        return hbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
