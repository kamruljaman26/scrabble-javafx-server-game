/**
 * This class represents each letters of a valid word tile. It
 * can be used to print the tile and check if the word is
 * highest scoring word or not.
 */
package client.model;

public class PrintTile {

    String direction;
    String word;
    Pos pos;
    int totalScore;

    public PrintTile(){
        this.direction = "";
        this.word = "";
        this.pos = null;
        this.totalScore = 0;
    }

    /**
     * This function returns the valid word that can be played, but may not
     * be the highest scoring word.
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     * This function sets the word of a printTile.
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * This function returns the position of the last letters of a valid word.
     * @return Pos pos
     */
    public Pos getPos() {
        return pos;
    }

    /**
     * This function is used to set the position of last letter of a valid word.
     * @param pos
     */
    public void setPos(Pos pos) {
        this.pos = pos;
    }

    /**
     * This function returns the total score a valid word.
     * @return
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * This function sets the total score a valid word.
     * @param totalScore
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * This function returns the direction on which the word will be placed in board.
     * @return String
     */
    public String getDirection() {
        return direction;
    }

    /**
     * This function sets the direction along which the word will be placed in the board.
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

}
