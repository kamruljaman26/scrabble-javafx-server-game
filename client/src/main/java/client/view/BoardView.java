/**
 * Manjil Pradhan
 * This class represents the board that can be used for
 * putting tiles (letters) to play the game.
 */
package client.view;

import client.model.Pos;
import javafx.geometry.Insets;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static client.view.BoardView.MULTIPLIER.*;

public class BoardView extends GridPane {

    /**
     * This enum is used to represent the premium tiles which
     * can multiply words or letters.
     */
    public enum MULTIPLIER {
        TW,
        DW,
        DL,
        TL,
        NO;
    }

    public static class TileColor {
        public static Color TW = Color.RED;
        public static Color DW = Color.PALEVIOLETRED;
        public static Color TL = Color.BLUE;
        public static Color DL = Color.valueOf("#5252a1");
        public static Color NO = Color.valueOf("#bfc1d6");
    }

    private final TileView[][] tilesGrid;
    private final int SIZE = 15; // fixed size for multiplayer board

    public BoardView() {
        this.tilesGrid = new TileView[SIZE][SIZE];

        initBoardView();
    }

    // init board view
    private void initBoardView() {
        // init board view
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {

                // Create TileView
                tilesGrid[row][col] = new TileView(this,
                        getMultiplier()[row][col],
                        '0',
                        5,
                        new Pos(row, col)
                );

                GridPane.setMargin(tilesGrid[row][col], new Insets(1.5));

                enableDropNode(tilesGrid[row][col], row, col);

                add(tilesGrid[row][col], col, row);
            }
        }
    }

    // enable drop tile functionality,
    // todo: add condition for selected cell, if he can place the tile or not.
    private void enableDropNode(TileView tileView, int row, int col) {
        tileView.setOnDragOver(event -> {
            /* Accept it only if it is not being dragged from the same node
             * and if it has string data */
            if (event.getGestureSource() != tileView && event.getDragboard().hasString()) {
                /* Allow for moving */
                event.acceptTransferModes(javafx.scene.input.TransferMode.MOVE);
            }

            event.consume();
        });

        /* Data dropped */
        tileView.setOnDragDropped(event -> {

            // todo: implement data logic
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                char c = db.getString().split(",")[0].toCharArray()[0];
                int s = Integer.parseInt(db.getString().split(",")[1]);
                tilesGrid[row][col] = new TileView(this, NO, db.getString().toCharArray()[0],
                        12, new Pos(1, 1));
                add(tilesGrid[row][col], col, row);
                success = true;
            }

            /* Let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });
    }


    /**
     * This function returns the list of all the valid
     * position of the baord. It is helpful in iterating
     * each position of the board.
     *
     * @return List</ Pos>
     */
    public List<Pos> validPosition() {
        List<Pos> validPosition = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Pos pos = new Pos(i, j);
                validPosition.add(pos);
            }
        }
        return validPosition;
    }

    /**
     * This function returns the tile at a given position given row and column.
     *
     * @param row
     * @param col
     * @return Tile
     */
    public TileView getTile(int row, int col) {
        return tilesGrid[row][col];
    }

    /**
     * This function returns the tile at a given position given position(Pos).
     *
     * @param pos
     * @return Tile
     */
    public TileView getTile(Pos pos) {
        return tilesGrid[pos.getX()][pos.getY()];
    }

    /**
     * This function adds tile, given row and column of the board.
     *
     * @param addTileView
     * @param row
     * @param col
     */
    public void setTile(TileView addTileView, int row, int col) {
        this.tilesGrid[row][col] = addTileView;
    }

    /**
     * This function add tiles, given Pos position of the board.
     *
     * @param addTileView
     * @param pos
     */
    public void setTile(TileView addTileView, Pos pos) {
        this.tilesGrid[pos.getX()][pos.getY()] = addTileView;
    }

    /**
     * This function checks if the tile at given position is
     * in bound or not.
     *
     * @param pos
     * @return boolean
     */
    public boolean tileInBound(Pos pos) {
        if (pos.getX() >= 0 && pos.getX() < SIZE &&
                pos.getY() >= 0 && pos.getY() < SIZE) {
            return true;
        }
        return false;
    }

    /**
     * This functions checks if the tile at a given row or column is
     * in bound or not.
     *
     * @param col
     * @param row
     * @return boolean
     */
    public boolean tileInBound(int col, int row) {
        if (row >= 0 && row < SIZE &&
                col >= 0 && col < SIZE) {
            return true;
        }
        return false;
    }

    /**
     * This function returns the size of the board.
     *
     * @return int
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * This function checks if the tile, given its position is filled or not.
     *
     * @param boardView
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isFilled(BoardView boardView, int row, int col) {
        if (boardView.getTile(row, col).getData() == '\0') {
            return false;
        }
        return true;
    }

    /**
     * This function checks if the tile, given its position is filled or not.
     *
     * @param boardView
     * @param pos
     * @return boolean
     */
    public boolean isFilled(BoardView boardView, Pos pos) {
        if (boardView.getTile(pos).getData() == '\0') {
            return false;
        }
        return true;
    }


    /**
     * This function returns the enum multiplier of each tile.
     *
     * @return
     */
    public MULTIPLIER[][] getMultiplier() {
        MULTIPLIER[][] multiplierArray = new MULTIPLIER[][]
                {{TW, NO, NO, DL, NO, NO, NO, TW, NO, NO, NO, DL, NO, NO, TW},
                        {NO, DW, NO, NO, NO, TL, NO, NO, NO, TL, NO, NO, NO, DW, NO},
                        {NO, NO, DW, NO, NO, NO, DL, NO, DL, NO, NO, NO, DW, NO, NO},
                        {DL, NO, NO, DW, NO, NO, NO, DL, NO, NO, NO, DW, NO, NO, DL},
                        {NO, NO, NO, NO, DW, NO, NO, NO, NO, NO, DW, NO, NO, NO, NO},
                        {NO, TL, NO, NO, NO, TL, NO, NO, NO, TL, NO, NO, NO, TL, NO},
                        {NO, NO, DL, NO, NO, NO, DL, NO, DL, NO, NO, NO, DL, NO, NO},
                        {TL, NO, NO, DL, NO, NO, NO, TW, NO, NO, NO, DL, NO, NO, TL},
                        {NO, NO, DL, NO, NO, NO, DL, NO, DL, NO, NO, NO, DL, NO, NO},
                        {NO, TL, NO, NO, NO, TL, NO, NO, NO, TL, NO, NO, NO, TL, NO},
                        {NO, NO, NO, NO, DW, NO, NO, NO, NO, NO, DW, NO, NO, NO, NO},
                        {DL, NO, NO, DW, NO, NO, NO, DL, NO, NO, NO, DW, NO, NO, DL},
                        {NO, NO, DW, NO, NO, NO, DL, NO, DL, NO, NO, NO, DW, NO, NO},
                        {NO, DW, NO, NO, NO, TL, NO, NO, NO, TL, NO, NO, NO, DW, NO},
                        {TW, NO, NO, DL, NO, NO, NO, TW, NO, NO, NO, DL, NO, NO, TW},};

        return multiplierArray;
    }

}
