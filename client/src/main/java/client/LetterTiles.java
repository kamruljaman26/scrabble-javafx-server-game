package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LetterTiles extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox hbox = new HBox(5); // Horizontal box with spacing between child nodes

        // Example tiles, you can generate these dynamically as needed
        String letters = "IEARJNU"; // Example string of letters
        for (char letter : letters.toCharArray()) {
            hbox.getChildren().add(createDraggableTile(letter));
        }

        // Shuffle button
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(e -> java.util.Collections.shuffle(hbox.getChildren()));
        hbox.getChildren().add(shuffleButton);

        Scene scene = new Scene(hbox, 800, 100); // Set appropriate size
        primaryStage.setTitle("Draggable Letter Tiles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createDraggableTile(char letter) {
        HBox tile = new HBox(); // Container for the rectangle and text
        tile.setStyle("-fx-background-color: #DAA520; -fx-padding: 10;"); // Gold background, padding
        tile.setSpacing(5); // Space between rectangle and text inside the tile

        Rectangle rect = new Rectangle(30, 50); // Dimensions for the rectangle
        rect.setFill(Color.WHITE);
        rect.setArcWidth(10);
        rect.setArcHeight(10);

        Text text = new Text(String.valueOf(letter));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        text.setFill(Color.BLACK);

        tile.getChildren().addAll(rect, text);

        // Dragging functionality
        tile.setOnDragDetected(event -> {
            Dragboard db = tile.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(text.getText());
            db.setContent(content);
            event.consume();
        });

        tile.setOnDragOver(event -> {
            if (event.getGestureSource() != tile && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        tile.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Text sourceText = (Text) ((HBox) event.getGestureSource()).getChildren().get(1);
                Text targetText = (Text) tile.getChildren().get(1);
                String temp = targetText.getText();
                targetText.setText(sourceText.getText());
                sourceText.setText(temp);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
