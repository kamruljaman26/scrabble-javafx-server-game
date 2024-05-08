package client.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.Collections;
import java.util.Comparator;

public class TilePileView extends HBox {

    private final ObservableList<TileView> tileViews;
    private Button shuffleBtn, sortBtn;

    public TilePileView() {
        this.tileViews = FXCollections.observableArrayList();

        initPileView();
    }

    // design and init view
    private void initPileView() {
        setSpacing(10); // Set spacing between contained elements
        // Style the HBox
        setStyle("-fx-background-color: #BBBBBB; " // Ash color
                + "-fx-background-radius: 10; "      // Rounded corners
                + "-fx-padding: 10;");              // Padding inside the HBox
        // Setting margin around the HBox
        HBox.setMargin(this, new Insets(10, 10, 10, 10)); // Adds a margin outside the HBox

        // create button and handler
        shuffleBtn = createStyledButton("Shuffle");
        sortBtn = createStyledButton("Sort");

        // shuffle button action
        shuffleBtn.setOnAction(e -> {
            Collections.shuffle(tileViews);
            refreshTilePile();
        });

        // sort button action
        sortBtn.setOnAction(e -> {
            tileViews.sort(Comparator.comparingInt(o -> (int) o.getData()));
            refreshTilePile();
        });

        getChildren().addAll(shuffleBtn, sortBtn);
    }

    // refresh pile view
    public void refreshTilePile() {
        tileViews.forEach(tileView -> {
            getChildren().remove(tileView);
        });
        tileViews.forEach(tileView -> {
            getChildren().add(tileView);
        });
    }

    // add title on tile pile
    public void addTile(TileView tileView) {

        // can't add more than 7 tiles
        if (tileViews.size() >= 7) return;

        tileView.setOnDragDetected(event -> {
            /* Allow any transfer mode */
            tileView.startDragAndDrop(javafx.scene.input.TransferMode.ANY);

            /* Put a string on the drag board */
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
            content.putString(tileView.getData() + "," + tileView.getScore());
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

        tileViews.add(tileView);
        getChildren().add(tileView);
    }

    // remove tile from view and list
    public void removeTile(TileView tileView) {

        tileViews.remove(tileView);
        getChildren().remove(tileView);
    }

    public ObservableList<TileView> getTileViews() {
        return tileViews;
    }

    // create stylish button
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
}
