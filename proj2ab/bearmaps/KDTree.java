package bearmaps;

import java.util.List;

//@sourse: https://www.youtube.com/watch?v=irkJ4gczM0I&ab_channel=JoshHug
public class KDTree implements PointSet {

    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;
    private Node root;

    private class Node {
        private Point p;
        private boolean orientation;
        private Node leftChild;   //also refers to be downChild
        private Node rightChild;  //also refers to be upChild

        Node(Point p, boolean o) {
            this.p = p;
            this.orientation = o;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    private Node add(Point p, Node n, boolean o) {
        if (n == null) {
            return new Node(p, o);
        }
        if (p.equals(n.p)) {
            return n;
        }
        int cmp = comparePoints(p, n.p, o);
        if (cmp < 0) {
            n.leftChild = add(p, n.leftChild, !o);
        } else if (cmp >= 0) {
            n.rightChild = add(p, n.rightChild, !o);
        }
        return n;
    }

    private int comparePoints(Point a, Point b, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearest(root, target, root.p);
    }

    private Point nearest(Node n, Point target, Point best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.p, target) < Point.distance(best, target)) {
            best = n.p;
        }

        Node goodSide = null;
        Node badSide = null ;
        Point vsPoint = null;

        int cmp = comparePoints(target, n.p, n.orientation);

        if (cmp <0 ) {
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }

        if (n.orientation == HORIZONTAL) {
            vsPoint = new Point(n.p.getX(), target.getY());
        } else {
            vsPoint = new Point(target.getX(), n.p.getY());
        }

        best = nearest(goodSide, target, best);
        if (Point.distance(vsPoint, target) < Point.distance(best, target)) {
            best = nearest(badSide, target, best);
        }
        return best;
    }
}
