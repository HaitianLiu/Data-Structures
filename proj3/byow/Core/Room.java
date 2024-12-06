package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.ArrayList;
import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;
import static byow.Core.MapGenerator.RANDOM;
import static byow.Core.Position.randomPosition;


public class Room implements Comparable<Room> {
    // @source: https://zhuanlan.zhihu.com/p/30724817
    // @source: http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/

    private Position position; //position of the left bottom corner
    private int width; // width of the room
    private int height; // height of the room
    private static ArrayList<Room> rooms = new ArrayList<>(); // store all the rooms

    public Room(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }
    public Position position() {
        return position;
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    // procedure to generate rooms
    // @source: https://us.edstem.org/courses/979/discussion/174517 comment from Henry Maier
    // 1. Generate one room. (generateRoom() method)
    // 2. Place rooms so that rooms are not overlapping and not out of bound. (addRooms method)
    // 3. Generate hallways that connect these rooms together.
    public static Room generateRoom() {
        int w = RandomUtils.uniform(RANDOM, 4,  8);
        int h = RandomUtils.uniform(RANDOM, 4,  8);
        Position p = randomPosition();
        if (p.getX() + w > WIDTH) {
            w = WIDTH - p.getX();
        }
        if (p.getY() + h > HEIGHT) {
            h = HEIGHT - p.getY();
        }
        Room room = new Room(p, w, h);


        return room;
    }
    // helper for addRooms
    public static void addRoom(TETile[][] world, Room room) {
        int xPos = room.position().getX();
        int yPos = room.position().getY();
//        for (int x = 0; x < room.width(); x++) {
////            world[xPos + x][yPos] = Tileset.WALL;
////            world[xPos + x][yPos - 1 + room.height()] = Tileset.WALL;
//        }
//        for (int y = 0; y < room.height(); y++) {
////            world[xPos][yPos + y] = Tileset.WALL;
////            world[xPos - 1 + room.width()][yPos + y] = Tileset.WALL;
//        }
        for (int i = xPos + 1; i < xPos + room.width() - 1; i++) {
            for (int j = yPos + 1; j < yPos + room.height() - 1; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }
    private static boolean pOver(Position p1, Room r1) {
        int r1X = r1.position().getX();
        int r1Y = r1.position().getY();
        int w = r1.width();
        int h = r1.height();
        if (r1X <= p1.getX() && r1X + w  >= p1.getX() && r1Y <= p1.getY() && r1Y + h >= p1.getY()) {
            return true;
        }
        return false;
    }
    // see if there's overlap
    private static boolean isOverlap(Room r1, Room r2) {
        Position p1 = r1.position();
        int r1X = r1.position().getX();
        int r1Y = r1.position().getY();
        Position p2 = new Position(r1X, r1Y + r1.height() - 1);
        Position p3 = new Position(r1X + r1.width() - 1, r1Y);
        Position p4 = new Position(r1X + r1.width() - 1, r1Y + r1.height() - 1);
        if (pOver(p1, r2) || pOver(p2, r2) || pOver(p3, r2) || pOver(p4, r2)) {
            return true;
        }
        return false;
    }
    public static boolean isOverlap(Room r1, ArrayList<Room> r1s) {
        if (r1.height < 3 || r1.width < 3) {
            return true;
        }
        for (Room r: r1s) {
            if (isOverlap(r1, r)) {
                return true;
            }
        }
        return false;
    }

//    public static boolean isOverlap(Position p1, ArrayList<Room> rooms) {
//        for (Room r: rooms) {
//            if (pOver(p1, r)) {
//                return true;
//            }
//        }
//        return false;
//    }
    // To add random rooms:
    // 1. put the room in the map. If there's overlap/out of bound. If so, delete.
    // 2. limit the times of random generations
    public static void addRooms(TETile[][] world) {
        int numRoomTries = 150;
        for (int i = 0; i < numRoomTries; i++) {
            Room r = generateRoom();
            if (!isOverlap(r, rooms)) {
                addRoom(world, r);
                rooms.add(r);
            }
        }
    }
    // return a list of rooms
    public static ArrayList<Room> rooms() {
        return rooms;
    }

    @Override
    public int compareTo(Room r) {
        int r1sum = this.position().getX() + this.position().getY();
        int r2sum = r.position().getX() + r.position().getY();
        return r1sum - r2sum;
    }

}
