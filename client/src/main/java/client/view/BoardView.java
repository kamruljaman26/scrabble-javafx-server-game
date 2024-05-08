/**
 * Manjil Pradhan
 * This class represents the board that can be used for
 * putting tiles (letters) to play the game.
 */
package client.view;

import client.model.Pos;
import javafx.geometry.Insets;
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
    public static enum MULTIPLIER {
        TW,
        DW,
        DL,
        TL,
        NO;
    }

    /**
     *
     */
    public static class TileColor {
        public static Color TW = Color.RED;
        public static Color DW = Color.PALEVIOLETRED;
        public static Color TL = Color.BLUE;
        public static Color DL = Color.valueOf("#5252a1");
        public static Color NO = Color.valueOf("#bfc1d6");
        public static Color TILE = Color.valueOf("#000C66");
    }

    private final TileView[][] tilesGrid;
    private final int SIZE = 15; // fixed size for multiplayer board

    public BoardView() {
        this.tilesGrid = new TileView[SIZE][SIZE];

        // Creating the board
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
                add(tilesGrid[row][col], col, row);
            }
        }
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

    /*    *//**
     * This function is helpful in printing the board in the terminal.
     *
     * @return
     *//*
    public String toString() {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tile[i][j].getData() == '\0') {
                    if (j == size - 1) {
                        build.append(tile[i][j].getValue());
                    } else {
                        build.append(tile[i][j].getValue() + " ");
                    }
                } else {
                    build.append(" " + tile[i][j].getData() + " ");
                }
            }
            build.append('\n');
        }
        return build.toString();
    }*/

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
