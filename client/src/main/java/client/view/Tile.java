/**
 * Manjil Pradhan
 * This class represents the individual tile on the board which contains the
 * character, their position, points of the tile, and premium points.
 */
package client.view;

import client.model.Pos;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private Board.MULTIPLIER multiplier;
    private char data;
    private int score;
    private Pos pos;

    private Rectangle background;
    private Text label;
    private GridPane grid;

    public Tile(GridPane grid, Board.MULTIPLIER multiplier, char data, int score, Pos pos) {
        this.multiplier = multiplier;
        this.data = data;
        this.score = score;
        this.pos = pos;
        this.grid = grid;

        background = new Rectangle(35, 35);
        background.setArcWidth(10);
        background.setArcHeight(10);

        this.label = new Text(multiplier.toString());
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set the font to Arial, bold, with a size of 14
        label.setFill(Color.WHITE);

        if (multiplier.equals(Board.MULTIPLIER.NO)) {

            if (data != '0') {
                label.setText(String.valueOf(data));
                background.setFill(Color.BLACK);
            } else {
                label.setText(" ");
                background.setFill(Board.TileColor.NO);
            }

        } else if (multiplier.equals(Board.MULTIPLIER.TW)) {
            background.setFill(Board.TileColor.TW);
        } else if (multiplier.equals(Board.MULTIPLIER.DW)) {
            background.setFill(Board.TileColor.DW);
        } else if (multiplier.equals(Board.MULTIPLIER.TL)) {
            background.setFill(Board.TileColor.TL);
        } else if (multiplier.equals(Board.MULTIPLIER.DL)) {
            background.setFill(Board.TileColor.DL);
        }

        this.getChildren().addAll(background, label);
        StackPane.setAlignment(label, javafx.geometry.Pos.CENTER);

        setupDragAndDrop();
    }

    private void setupDragAndDrop() {

        this.setOnDragDetected(event -> {
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            // Store the indices as part of the drag content
            content.putString(GridPane.getRowIndex(this) + "," + GridPane.getColumnIndex(this));
            db.setContent(content);
            event.consume();
        });

        this.setOnDragOver(event -> {
            if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        this.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            if (db.hasString()) {
                String[] indices = db.getString().split(",");
                int sourceRow = Integer.parseInt(indices[0]);
                int sourceCol = Integer.parseInt(indices[1]);

                Node sourceNode = getNodeByRowColumnIndex(sourceRow, sourceCol, grid);
                int targetRow = GridPane.getRowIndex(this);
                int targetCol = GridPane.getColumnIndex(this);

                // Swap the nodes
                grid.getChildren().remove(this);
                grid.getChildren().remove(sourceNode);

                grid.add(this, sourceCol, sourceRow);
                grid.add(sourceNode, targetCol, targetRow);

                event.setDropCompleted(true);
                event.consume();
            }
        });
    }

    // Helper to find a node by its grid position
    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }


    /**
     * This function returns the position of the tile in the board.
     *
     * @param pos
     */
    public Tile(Pos pos) {
        this.pos = pos;
    }

    /**
     * This function is used to set the enum of the premium tile.
     *
     * @param multiplier
     */
    public void setMultiplier(Board.MULTIPLIER multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * This function is used to set
     *
     * @param data
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * This function returns the premium enum of a tile.
     *
     * @return Multiplier
     */
    public Board.MULTIPLIER getMultiplier() {
        return multiplier;
    }

    /**
     * This function returns the letter that was placed on the tile.
     *
     * @return
     */
    public char getData() {
        return data;
    }

    /**
     * This function returns the position of the tile in board.
     *
     * @return
     */
    public Pos getPos() {
        return pos;
    }

    /**
     * This function sets the position of the tile in board.
     *
     * @param pos
     */
    public void setPos(Pos pos) {
        this.pos = pos;
    }
}
