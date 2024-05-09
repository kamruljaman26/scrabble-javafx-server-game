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

import java.util.ArrayList;
import java.util.Collections;

public class GameView extends HBox {

    private final TilePileView tilePileView;
    private final ObservableList<Player> players;
    private final PlayersView playersView;
    private final BoardView boardView;
    private ClientConnection connection;
    private ArrayList<TileView> tileViews;

    public GameView(ClientConnection connection) {
        // init views
        this.connection = connection;
        boardView = new BoardView();
        players = FXCollections.observableArrayList();
        tilePileView = new TilePileView();
        createPile();

        addDemoData();
        playersView = new PlayersView(players);

        initGameViewLayout();
    }

    public void createPile() {
        // Frequencies and scores for letters A-Z
        int[] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        int[] quantities = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        // Create tiles based on the quantities and scores
        tileViews = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < quantities[i]; j++) {
                TileView tile = new TileView(boardView, BoardView.MULTIPLIER.NO, letters[i],
                        scores[i], new Pos(2, 2));
                tileViews.add(tile);
            }
        }

        Collections.shuffle(tileViews);
        tileViews.forEach(tilePileView::addTile);
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
        root.getChildren().addAll(boardView, tilePileView, controlButtons);
        getChildren().addAll(root, playersView);
    }

    /**
     * Handle Resign button action
     */
    private void resignButtonAction() {
        //todo
        System.exit(0);
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
        tilePileView.removeAll();
        Collections.shuffle(tileViews);
        tileViews.forEach(tilePileView::addTile);
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

        if (playersView != null)
            playersView.appendLog("This os log section");


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
