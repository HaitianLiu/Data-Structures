package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

import java.util.Random;

import byow.TileEngine.Tileset;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;
import static byow.Core.Hallway.connectRooms;
import static byow.Core.Room.rooms;

public class MapGenerator {
    private long seed;
    static Random RANDOM;

    public static TETile[][] generateWorld(long seed) {
        RANDOM = new Random(seed);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Room.addRooms(world);
        connectRooms(world, rooms());
        generateWalls(world);
        addLockedDoor(world);
        return world;
    }

    public static void generateWalls(TETile[][] world) {
        for (int i = 1; i < WIDTH - 1; i += 1) {
            for (int j = 1; j < HEIGHT - 1; j += 1) {
                if (world[i][j] == Tileset.NOTHING) {
                    if (world[i + 1][j] == Tileset.FLOOR || world[i - 1][j] == Tileset.FLOOR
                            || world[i][j + 1] == Tileset.FLOOR || world[i][j - 1] == Tileset.FLOOR
                            || world[i - 1][j - 1] == Tileset.FLOOR
                            || world[i + 1 ][j - 1] == Tileset.FLOOR
                            || world[i - 1][j + 1] == Tileset.FLOOR
                            || world[i + 1][j + 1] == Tileset.FLOOR) {
                        world[i][j] = Tileset.WALL;
                    }
                }
            }
        }
        for (int i = 1; i < WIDTH - 1; i += 1) {
            if (world[i][0] == Tileset.NOTHING) {
                if (world[i][1] == Tileset.FLOOR || world[i - 1][1] == Tileset.FLOOR
                        || world[i + 1][1] == Tileset.FLOOR) {
                    world[i][0] = Tileset.WALL;
                }
            }
            if (world[i][HEIGHT - 1] == Tileset.NOTHING) {
                if (world[i][HEIGHT - 2] == Tileset.FLOOR
                        || world[i - 1][HEIGHT - 2] == Tileset.FLOOR
                        || world[i + 1][HEIGHT - 2] == Tileset.FLOOR) {
                    world[i][HEIGHT - 1] = Tileset.WALL;
                }
            }
        }
        for (int j = 1; j < HEIGHT - 1; j += 1) {
            if (world[0][j] == Tileset.NOTHING) {
                if (world[1][j] == Tileset.FLOOR || world[1][j - 1] == Tileset.FLOOR
                        || world[1][j + 1] == Tileset.FLOOR) {
                    world[0][j] = Tileset.WALL;
                }
            }
            if (world[WIDTH - 1][j] == Tileset.NOTHING) {
                if (world[WIDTH - 2][j] == Tileset.FLOOR || world[WIDTH - 2][j - 1] == Tileset.FLOOR
                        || world[WIDTH - 2][j + 1] == Tileset.FLOOR) {
                    world[WIDTH - 1][j] = Tileset.WALL;
                }
            }
        }
        if (world[0][1] == Tileset.WALL && world[1][0] == Tileset.WALL) {
            world[0][0] = Tileset.WALL;
        }
        if (world[0][HEIGHT - 2] == Tileset.WALL && world[1][HEIGHT - 1] == Tileset.WALL) {
            world[0][HEIGHT - 1] = Tileset.WALL;
        }
        if (world[WIDTH - 1][1] == Tileset.WALL && world[WIDTH - 2][0] == Tileset.WALL) {
            world[WIDTH - 1][0] = Tileset.WALL;
        }
        if (world[WIDTH - 1][HEIGHT - 2] == Tileset.WALL
                && world[WIDTH - 2][HEIGHT - 1] == Tileset.WALL) {
            world[WIDTH - 1][HEIGHT - 1] = Tileset.WALL;
        }
    }

    public static void addLockedDoor(TETile[][] world) {
        Position pos = Position.randomPosition();
        while (!world[pos.getX()][pos.getY()].equals(Tileset.WALL)) {
            pos = Position.randomPosition();
        }
        world[pos.getX()][pos.getY()] = Tileset.LOCKED_DOOR;
    }


    public static void main(String[] args) {

        TERenderer myworld = new TERenderer();
        myworld.initialize(WIDTH, HEIGHT);

        TETile[][] world = generateWorld(12345678);

        myworld.renderFrame(world);
    }

}
