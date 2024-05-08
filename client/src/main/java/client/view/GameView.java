package client.view;

import client.model.Player;
import client.model.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameView extends HBox {

    private final TilePileView tilesPile;

    public GameView() {
        // Root layout
        VBox root = new VBox(10);
        root.setBackground(new Background(new BackgroundFill(Color.valueOf("#DDDDDD"),
                CornerRadii.EMPTY, new Insets(0))));

        /*
         * Board
         */
        BoardView boardView = new BoardView();

        /*
         * Create TileBox
         */
        TileView tileView = new TileView(boardView, BoardView.MULTIPLIER.NO, 'A', 5, new Pos(4, 4));
        TileView tileView2 = new TileView(boardView, BoardView.MULTIPLIER.NO, 'B', 5, new Pos(4, 4));

        // create and int tile pile
        tilesPile = new TilePileView();
        tilesPile.addTile(tileView);
        tilesPile.addTile(tileView2);

        // Control buttons
        HBox controlButtons = new HBox(10);
        controlButtons.setAlignment(javafx.geometry.Pos.CENTER);
        Button btnResign = createStyledButton("Resign");
        Button btnSkip = createStyledButton("Skip");
        Button btnSwap = createStyledButton("Swap");
        Button btnSubmit = createStyledButton("Submit");
        controlButtons.getChildren().addAll(btnResign, btnSkip, btnSwap, btnSubmit);

        btnSubmit.setOnAction(actionEvent -> {
            TileView tileView3 = new TileView(boardView, BoardView.MULTIPLIER.NO, 'A', 5, new Pos(4, 4));
            TileView tileView4 = new TileView(boardView, BoardView.MULTIPLIER.NO, 'B', 5, new Pos(4, 4));
            tilesPile.addTile(tileView3);
            tilesPile.addTile(tileView4);
        });

        // Create Player View
        //    public Player(String id, String serverIpAddress, int serverPort, int listeningPort, String name) {
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.add(new Player("12", "12", 12, 12, "Jhon Due"));
        players.add(new Player("12", "12", 12, 12, "Lactor"));
        PlayersView playersView = new PlayersView(players);
        playersView.appendLog("This os log section");

        // Adding to root
        root.getChildren().addAll(boardView, tilesPile, controlButtons);
        getChildren().addAll(root, playersView);
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
