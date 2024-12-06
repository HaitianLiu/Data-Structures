package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Collections;



public class Hallway {
    private int connectedRooms = 0;
    // directly call when r1.x = r2.x
    // vertical 是往上画的
    public static void drawVertical(TETile[][] world, Position p, int h) {
        for (int i = 0; i < h; i++) {
            int x = p.getX();
            int y = p.getY();
//            if (world[x][y + i] == Tileset.FLOOR) {
////                break;
//            }
//            world[x - 1][y + i] = Tileset.WALL;
//            world[x + 1][y + i] = Tileset.WALL;
            world[x][y + i] = Tileset.FLOOR;

        }
    }
    // directly call when r1.y = r2.y
    // horizontal 是往右画的
    public static void drawHorizontal(TETile[][] world, Position p, int w) {
        for (int i = 0; i < w; i++) {
            int x = p.getX();
            int y = p.getY();
//            if (world[x + i][y] == Tileset.FLOOR) {
////                break;
//            }
//            world[x + i][y - 1] = Tileset.WALL;
//            world[x + i][y + 1] = Tileset.WALL;
            world[x + i][y] = Tileset.FLOOR;

        }
    }
    public static void drawCorner(TETile[][] world, Position corner) {
        int x = corner.getX();
        int y = corner.getY();
//        for (int i = x - 1; i < x + 2; i++) {
//            for (int j = y - 1; j < y + 2; j++) {
////               world[i][j] = Tileset.WALL;
//            }
//        }
        world[x][y] = Tileset.FLOOR;
    }

    public static void connectRoom(TETile[][] world, Room r1, Room r2) {
        Position corner;
        int r1x = r1.position().getX();
        int r1y = r1.position().getY();
        int r1h = r1.height();
        int r1w = r1.width();
        int r2x = r2.position().getX();
        int r2y = r2.position().getY();
        int r2w = r2.width();
        int r2h = r2.height();

        if (r2y >= r1y + r1h - 2) {

            if (r2x + r2w - 2 <= r1x) {
                corner = new Position(r1x + r1w - 2, r2y + r2h - 2);
                Position v = new Position(corner.getX(), r1y + r1h - 1);
                Position h = new Position(r2x + r2w - 1, corner.getY());
                drawCorner(world, corner);
                drawVertical(world, v, corner.getY() - v.getY());
                drawHorizontal(world, h, corner.getX() - h.getX());
            } else if (r1x + r1w - 2 <= r2x) {
                corner = new Position(r2x + r2w - 2, r1y + 1);
                Position v = new Position(corner.getX(), corner.getY() + 1);
                Position h = new Position(r1x + r1w - 1, corner.getY());
                drawCorner(world, corner);
                drawVertical(world, v, r2y - corner.getY());
                drawHorizontal(world, h, corner.getX() - h.getX());

            } else {
                int diffx = r1x - r2x;
                if (diffx >= 0) {
                    Position v = new Position(r1x + 1, r1y + r1h - 1);
                    drawVertical(world, v, r2y - v.getY() + 1);

                } else {
                    Position v = new Position(r2x + 1, r1y + r1h - 1);
                    drawVertical(world, v, r2y - v.getY() + 1);
                }
            }
        } else if (r2y + r2h - 2 <= r1y) {
            if (r2x + r2w - 2 <= r1x) {
                corner = new Position(r2x + 1, r1y + r1h - 2);
                Position v = new Position(corner.getX(), r2y + r2h - 1);
                Position h = new Position(corner.getX() + 1, corner.getY());
                drawCorner(world, corner);
                drawVertical(world, v, corner.getY() - v.getY());
                drawHorizontal(world, h, r1x - h.getX() + 1);
            } else if (r1x + r1w - 2 <= r2x) {
                corner = new Position(r1x + 1, r2y + 1);
                Position v = new Position(corner.getX(), corner.getY() + 1);
                Position h = new Position(corner.getX() + 1, corner.getY());
                drawCorner(world, corner);
                drawVertical(world, v, r1y - v.getY() + 1);
                drawHorizontal(world, h, r2x - h.getX() + 1);
            } else {
                int diffx = r1x - r2x;
                if (diffx >= 0) {
                    Position v = new Position(r1x + 1, r2y + r2h - 1);
                    drawVertical(world, v, r1y - v.getY() + 1);
                } else {
                    Position v = new Position(r2x + 1, r2y + r2h - 1);
                    drawVertical(world, v, r1y - v.getY() + 1);
                }
            }
        } else {
            int diffy = r1y - r2y;
            if (r2x + r2w - 2 <= r1x) {
                if (r2y <= r1y && (r1y + r1h) <= (r2y + r2h)) {
                    Position h = new Position(r2x + r2w - 1, r1y + 1);
                    drawHorizontal(world, h, r1x - h.getX() + 1);
                } else if (diffy > 0) {
                    Position h = new Position(r2x + r2w - 1, r2y + r2h - 2);
                    drawHorizontal(world, h, r1x - h.getX() + 1);

                } else {
                    Position h = new Position(r2x + r2w - 1, r2y + 1);
                    drawHorizontal(world, h, r1x - h.getX() + 1);
                }
            } else {
                if (r1y <= r2y && (r2y + r2h) <= (r1y + r1h)) {
                    Position h = new Position(r1x + r1w - 1, r2y + 1);
                    drawHorizontal(world, h, r2x - h.getX() + 1);
                } else if (diffy > 0) {
                    Position h = new Position(r1x + r1w - 1, r1y + 1);
                    drawHorizontal(world, h, r2x - h.getX() + 1);

                } else {
                    Position h = new Position(r1x + r1w - 1, r1y + r1h - 2);
                    drawHorizontal(world, h, r2x - h.getX() + 1);
                }
            }
        }
    }

    public static void connectRooms(TETile[][] world, ArrayList<Room> rooms) {
        Collections.sort(rooms);
        for (int i = 0; i < rooms.size() - 1; i++) {
            connectRoom(world, rooms.get(i), rooms.get(i + 1));
        }
    }

}
