package client.view;

import client.model.Player;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * JavaFX view to show a list of players with their names and scores in a rectangle with a soft black background,
 * followed by a text area view with a black background and white text.
 */
public class PlayersView extends VBox {

    private ObservableList<Player> players;
    private TextArea logArea;  // TextArea to display logs or additional information

    public PlayersView(ObservableList<Player> players) {
        super(20); // spacing between player views
        this.players = players;
        this.setMinWidth(350);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(20));

        initPlayerViews();
        initTextArea();
    }

    private void initPlayerViews() {
        players.forEach(p -> {
            HBox playerBox = new HBox(20);
            playerBox.setStyle("-fx-background-color: #bfc1d6; -fx-background-radius: 10;"); // Dark background with rounded corners
            playerBox.setMinWidth(160);
            playerBox.setMinHeight(50);
            playerBox.setAlignment(Pos.CENTER);

            Text playerName = new Text(p.getName() + " - " + p.getId());
            playerName.setStyle("-fx-text-fill: #000000; -fx-font-size: 12pt;");  // Styling

            playerName.setFill(Color.BLACK);
            playerBox.getChildren().add(playerName);

            this.getChildren().add(playerBox);
        });
    }

    private void initTextArea() {
        logArea = new TextArea();
        logArea.setEditable(false);  // Making TextArea non-editable if it's just for display
        logArea.setMinHeight(300);
        logArea.setMaxWidth(700);
        logArea.setStyle("-fx-control-inner-background: #bfc1d6; -fx-text-fill: #000000; -fx-font-size: 12pt;");  // Styling

        this.getChildren().addAll(logArea);
    }

    public void appendLog(String message) {
        logArea.appendText(message + "\n");
    }
}