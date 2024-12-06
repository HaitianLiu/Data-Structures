package byow.Core;


import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

// define the position of a point with x and y axis
public class Position {
    private int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static Position randomPosition() {
        int x = RandomUtils.uniform(MapGenerator.RANDOM, WIDTH);
        int y = RandomUtils.uniform(MapGenerator.RANDOM, HEIGHT);
        Position p = new Position(x, y);
        return p;
    }
}
