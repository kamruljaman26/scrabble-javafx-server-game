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

public class TileView extends StackPane {

    private BoardView.MULTIPLIER multiplier;
    private char data;
    private int score;
    private Pos pos;

    private Rectangle background;
    private Text label;
    private GridPane grid;

    public TileView(GridPane grid, BoardView.MULTIPLIER multiplier, char data, int score, Pos pos) {
        this.multiplier = multiplier;
        this.data = data;
        this.score = score;
        this.pos = pos;
        this.grid = grid;

        initLayoutView();
    }

    public TileView(BoardView.MULTIPLIER multiplier, char data, int score) {
        this.multiplier = multiplier;
        this.data = data;
        this.score = score;
    }

    // init layout view
    private void initLayoutView() {
        background = new Rectangle(35, 35);
        background.setArcWidth(10);
        background.setArcHeight(10);

        this.label = new Text(multiplier.toString());
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set the font to Arial, bold, with a size of 14
        label.setFill(Color.WHITE);

        if (multiplier.equals(BoardView.MULTIPLIER.NO)) {

            if (data != '0') {
                label.setText(String.valueOf(data));
                background.setFill(Color.ORANGE);
            } else {
                label.setText(" ");
                background.setFill(BoardView.TileColor.NO);
            }

        } else if (multiplier.equals(BoardView.MULTIPLIER.TW)) {
            background.setFill(BoardView.TileColor.TW);
        } else if (multiplier.equals(BoardView.MULTIPLIER.DW)) {
            background.setFill(BoardView.TileColor.DW);
        } else if (multiplier.equals(BoardView.MULTIPLIER.TL)) {
            background.setFill(BoardView.TileColor.TL);
        } else if (multiplier.equals(BoardView.MULTIPLIER.DL)) {
            background.setFill(BoardView.TileColor.DL);
        }

        this.getChildren().addAll(background, label);
        StackPane.setAlignment(label, javafx.geometry.Pos.CENTER);
    }

    /**
     * This function returns the position of the tile in the board.
     *
     * @param pos
     */
    public TileView(Pos pos) {
        this.pos = pos;
    }

    /**
     * This function is used to set the enum of the premium tile.
     *
     * @param multiplier
     */
    public void setMultiplier(BoardView.MULTIPLIER multiplier) {
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
    public BoardView.MULTIPLIER getMultiplier() {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBackground(Rectangle background) {
        this.background = background;
    }

    public Text getLabel() {
        return label;
    }

    public void setLabel(Text label) {
        this.label = label;
    }

    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }
}
