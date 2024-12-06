package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static class Position {
        int x, y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon as the position
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        // draw row by row from bottom left, so we are going up, they are the same thing
        // add every time cuz bottom; subtract if we start from the top
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon size should be at least 2.");
        }
        // rows number = 2s
        for (int y = 0; y < s * 2; y++) {
            Position rowPos;
            int width;

            int RowY = p.y + y;
            int RowX = p.x - hexRowOffset(s, y);

            rowPos = new Position(RowX, RowY);
            width = hexRowWidth(s, y);

            addRow(world, rowPos, width, t);
        }
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    private static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int i = 0; i < width; i += 1) {
            int x = p.x + i;
            int y = p.y;
            world[x][y] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }
    /**
     * @param s The size of the hex.
     * @param y The row number where y = 0 is the bottom row.
     * @return the relative x coordinate of the leftmost tile in the ith row.
     */
    private static int hexRowOffset(int s, int y) {
        if (y < s) {
            return y;
        } else {
            return 2 * s - 1 - y;
        }
    }
    /**
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return the width of row i for a size s hexagon.
     */
    private static int hexRowWidth(int s, int i) {
        return s - 2 * hexRowOffset(s, i);
    }
}

