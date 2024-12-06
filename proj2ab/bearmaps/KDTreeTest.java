package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@sourse: https://www.youtube.com/watch?v=lp80raQvE5c&ab_channel=JoshHug
public class KDTreeTest {

    private static KDTree buildLectureTree() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        return kd;
    }

    private static void buildTreeWithDoubles() {
        Point p1 = new Point(2,3);
        Point p2 = new Point(2,3);

        KDTree kd = new KDTree(List.of(p1, p2));
    }

    @Test
    public void testNearestDemoSlides() {
        KDTree kd = buildLectureTree();
        Point actual = kd.nearest(0, 7);
        Point expected = new Point(1, 5);
        assertEquals(expected, actual);
    }


    private static Random r = new Random(500);

    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            points.add(randomPoint());
        }
        return points;
    }


    private void testWithNPointsAndQQueris(int pointCount, int queryCount) {
        List<Point> points = randomPoints(pointCount);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);

        List<Point> queries = randomPoints(queryCount);
        for (Point p : queries) {
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    @Test
    public void randomizedTest() {
        int pointCount = 1000;
        int queryCount = 200;
        testWithNPointsAndQQueris(pointCount, queryCount);
    }

    @Test
    public void randomizedTest1() {
        int pointCount = 10000;
        int queryCount = 2000;
        testWithNPointsAndQQueris(pointCount, queryCount);
    }

    //@sourse: lab5
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    @Test
    public void speedtest1() {
        List<Integer> Ns = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Double> times = new ArrayList<>();

        for (int index = 31250; index <= 2000000; index = index * 2) {
            Ns.add(index);
            opCounts.add(index);
            Stopwatch sw = new Stopwatch();
            List<Point> points = randomPoints(index);
            KDTree kd = new KDTree(points);
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.printf("Timing table for Kd-Tree Construction\n");
        printTimingTable(Ns, times, opCounts);
    }

    @Test
    public void speedtest2() {
        List<Integer> Ns = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Point> points = randomPoints(1000000);
        NaivePointSet nps = new NaivePointSet(points);


        for (int index = 125; index <= 1000; index = index * 2) {
            Ns.add(index);
            opCounts.add(1000000);
            Stopwatch sw = new Stopwatch();
            List<Point> q1 = randomPoints(index);
            for (Point p : q1) {
                nps.nearest(p.getX(), p.getY());
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.printf("Timing table for Naive Nearest\n");
        printTimingTable(Ns, times, opCounts);
    }

    @Test
    public void speedtest3() {
        List<Integer> Ns = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Point> points = randomPoints(1000000);
        KDTree kd = new KDTree(points);


        for (int index = 31250; index <= 1000000; index = index * 2) {
            Ns.add(index);
            opCounts.add(1000000);
            Stopwatch sw = new Stopwatch();
            List<Point> q2 = randomPoints(index);
            for (Point p : q2) {
                kd.nearest(p.getX(), p.getY());
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.printf("Timing table for Kd-Tree Nearest\n");
        printTimingTable(Ns, times, opCounts);
    }
}
