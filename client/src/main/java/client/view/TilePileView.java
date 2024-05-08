package client.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TilePileView extends HBox {

    private final ArrayList<TileView> tileViews;
    private Button shuffleBtn, sortBtn;

    public TilePileView() {
        this.tileViews = new ArrayList<>();

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

    public void addTile(TileView tileView) {
        tileViews.add(tileView);
        tileView.setOnDragDetected(event -> {
            /* Allow any transfer mode */
            tileView.startDragAndDrop(javafx.scene.input.TransferMode.ANY);

            /* Put a string on the drag board */
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
            content.putString(tileView.getData() + "");
            tileView.startDragAndDrop(javafx.scene.input.TransferMode.MOVE).setContent(content);

            event.consume();
        });

        tileView.setOnDragDone(event -> {
            /* the drag and drop gesture ended */
            /* if the data was successfully moved, clear it */
            if (event.getTransferMode() == javafx.scene.input.TransferMode.MOVE) {
                removeTile(tileView);
            }

            event.consume();
        });

        getChildren().add(tileView);
    }

    public boolean removeTile(TileView tileView) {
        tileViews.remove(tileView);
        return getChildren().remove(tileView);
    }
}
