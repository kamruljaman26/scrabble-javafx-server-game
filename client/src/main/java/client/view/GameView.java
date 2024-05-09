package client.view;

import client.model.Player;
import client.model.Pos;
import client.server.ClientConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameView extends HBox {

    private final TilePileView tilesPile;
    private final ObservableList<Player> players;
    private final PlayersView playersView;
    private final BoardView boardView;
    private ClientConnection connection;

    public GameView(ClientConnection connection) {
        // init views
        this.connection = connection;
        boardView = new BoardView();
        players = FXCollections.observableArrayList();
        tilesPile = new TilePileView();
        addDemoData();
        playersView = new PlayersView(players);

        initGameViewLayout();
    }

    // init layout and button view and handler
    private void initGameViewLayout() {
        // Root layout
        VBox root = new VBox(10);
        root.setBackground(new Background(new BackgroundFill(Color.valueOf("#DDDDDD"),
                CornerRadii.EMPTY, new Insets(0))));

        // Control buttons
        Button btnResign = createStyledButton("Resign");
        Button btnSkip = createStyledButton("Skip");
        Button btnSwap = createStyledButton("Swap");
        Button btnSubmit = createStyledButton("Submit");

        btnResign.setOnAction(actionEvent -> resignButtonAction());
        btnSkip.setOnAction(actionEvent -> skipButtonAction());
        btnSwap.setOnAction(actionEvent -> swapButtonAction());
        btnSubmit.setOnAction(actionEvent -> submitButtonAction());

        // Adding to root
        HBox controlButtons = new HBox(10);
        controlButtons.setAlignment(javafx.geometry.Pos.CENTER);
        controlButtons.getChildren().addAll(btnResign, btnSkip, btnSwap, btnSubmit);
        root.getChildren().addAll(boardView, tilesPile, controlButtons);
        getChildren().addAll(root, playersView);
    }

    /**
     * Handle Resign button action
     */
    private void resignButtonAction() {
        //todo
    }

    /**
     * Handle Skip button action
     */
    private void skipButtonAction() {

    }

    /**
     * Handle Swap button action
     */
    private void swapButtonAction() {
        addDemoData();
    }

    /**
     * Handle Submit button action
     */
    private void submitButtonAction() {
        // todo
    }

    private void addDemoData() {
        players.add(new Player("12", "12", 12, 12, "Jhon Due"));
        players.add(new Player("12", "12", 12, 12, "Lactor"));

        if(playersView != null)
            playersView.appendLog("This os log section");

        /*
         * Create TileBox
         */
        TileView tileView = new TileView(boardView, BoardView.MULTIPLIER.NO, 'A', 5, new Pos(4, 4));
        TileView tileView2 = new TileView(boardView, BoardView.MULTIPLIER.NO, 'B', 5, new Pos(4, 4));
        tilesPile.addTile(tileView);
        tilesPile.addTile(tileView2);
    }

    // create stylish button
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
