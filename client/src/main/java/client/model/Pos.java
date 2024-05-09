/**
 * This class represents the position of the tile.
 */

package client.model;

import java.util.Objects;

/**
 * This class represents the position of a tile.
 */
public class Pos {

    private int x;
    private int y;

    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * This function returns the row number of the position
     * @return int
     */
    public int getX(){ return x;}

    /**
     * This function returns the row number of the position
     * @return int
     */
    public int getY(){ return y;}

    /**
     * This function returns position i.e. both row and column of the tile.
     * @return
     */
    public Pos getPosition(){return this;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pos pos = (Pos) obj;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
