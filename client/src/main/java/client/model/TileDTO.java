package client.model;

import client.view.BoardView;

/**
 * This class represents a Data Transfer Object (DTO) for a tile on the board.
 */
public class TileDTO {

    private BoardView.MULTIPLIER multiplier;
    private char data;
    private int score;
    private Pos pos;

    public TileDTO(BoardView.MULTIPLIER multiplier, char data, int score) {
        this.multiplier = multiplier;
        this.data = data;
        this.score = score;
    }

    public BoardView.MULTIPLIER getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(BoardView.MULTIPLIER multiplier) {
        this.multiplier = multiplier;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "TileDTO{" +
                "multiplier=" + multiplier +
                ", data=" + data +
                ", score=" + score +
                ", pos=" + pos +
                '}';
    }
}
