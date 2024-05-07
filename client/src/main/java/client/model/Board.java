/**
 * Manjil Pradhan
 * This class represents the board that can be used for
 * putting tiles (letters) to play the game.
 */
package client.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import static client.model.Board.MULTIPLIER.*;

public class Board {

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

    public static class TileColor {
        public static Color TW = Color.RED;
        public static Color DW = Color.PALEVIOLETRED;
        public static Color TL = Color.BLUE;
        public static Color DL = Color.valueOf("#7EC8E3");
        public static Color NO = Color.valueOf("#000C66");
    }

    private int size;
    private Tile[][] tile;

    public Board(int size) {
        this.size = size;
        this.tile = new Tile[size][size];

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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
    public Tile getTile(int row, int col) {
        return tile[row][col];
    }

    /**
     * This function returns the tile at a given position given position(Pos).
     *
     * @param pos
     * @return Tile
     */
    public Tile getTile(Pos pos) {
        return tile[pos.getX()][pos.getY()];
    }

    /**
     * This function adds tile, given row and column of the board.
     *
     * @param addTile
     * @param row
     * @param col
     */
    public void setTile(Tile addTile, int row, int col) {
        this.tile[row][col] = addTile;
    }

    /**
     * This function add tiles, given Pos position of the board.
     *
     * @param addTile
     * @param pos
     */
    public void setTile(Tile addTile, Pos pos) {
        this.tile[pos.getX()][pos.getY()] = addTile;
    }

    /**
     * This function checks if the tile at given position is
     * in bound or not.
     *
     * @param pos
     * @return boolean
     */
    public boolean tileInBound(Pos pos) {
        if (pos.getX() >= 0 && pos.getX() < size &&
                pos.getY() >= 0 && pos.getY() < size) {
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
        if (row >= 0 && row < size &&
                col >= 0 && col < size) {
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
        return size;
    }

    /**
     * This function checks if the tile, given its position is filled or not.
     *
     * @param board
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isFilled(Board board, int row, int col) {
        if (board.getTile(row, col).getData() == '\0') {
            return false;
        }
        return true;
    }

    /**
     * This function checks if the tile, given its position is filled or not.
     *
     * @param board
     * @param pos
     * @return boolean
     */
    public boolean isFilled(Board board, Pos pos) {
        if (board.getTile(pos).getData() == '\0') {
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
