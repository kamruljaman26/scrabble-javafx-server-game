package client.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TilePile extends HBox {

    private final ArrayList<Tile> tiles;
    private Button shuffleBtn, sortBtn;

    public TilePile() {
        this.tiles = new ArrayList<>();

        setSpacing(10); // Set spacing between contained elements
        // Style the HBox
        setStyle("-fx-background-color: #BBBBBB; " // Ash color
                + "-fx-background-radius: 10; "      // Rounded corners
                + "-fx-padding: 10;");              // Padding inside the HBox
        // Setting margin around the HBox
        HBox.setMargin(this, new Insets(10, 10, 10, 10)); // Adds a margin outside the HBox

        shuffleBtn = createStyledButton("Shuffle");
        sortBtn = createStyledButton("Sort");
        getChildren().addAll(shuffleBtn, sortBtn);
    }

    public Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(40);
        button.setMinHeight(40);
        button.setMinHeight(40);
        button.setMaxHeight(40);
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

    public void addTile(Tile tile) {
        tiles.add(tile);
        getChildren().add(tile);
    }

    public boolean removeTile(Tile tile) {
        tiles.remove(tile);
        return getChildren().remove(tile);
    }
}
