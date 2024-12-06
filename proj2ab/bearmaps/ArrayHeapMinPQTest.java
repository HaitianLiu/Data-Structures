package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;


public class ArrayHeapMinPQTest {

    @Test
    public void emptyTest() {
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        assertEquals(0, arrayHeapMinPQ.size());
    }

    @Test
    public void smallTest() {
        ArrayHeapMinPQ<String> it = new ArrayHeapMinPQ<>();
        it.add("dhdhd", 1);
        it.add("hshsb", 2);
        it.add("wush", 5);
        it.add("xmsjd", 1.5);
        it.add("snhx", 0.5);
        it.add("wiuzjn", 0.01);
        assertEquals(6,  it.size());
        assertEquals("wiuzjn",  it.getSmallest());
        assertEquals("wiuzjn",  it.removeSmallest());
        assertEquals("snhx",  it.removeSmallest());
        assertEquals("dhdhd",  it.getSmallest());
        assertTrue(it.contains("dhdhd"));
        assertTrue(it.contains("dhdhd"));
        assertFalse(it.contains("dhdddkf"));

    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> it = new ArrayHeapMinPQ<>();
        it.add("dhdhd", 1);
        it.add("hshsb", 2);
        it.add("wush", 5);
        it.add("xmsjd", 1.5);
        it.add("snhx", 0.5);
        it.add("wiuzjn", 0.01);
        it.changePriority("wush",  0.001);
        assertEquals("wush",  it.getSmallest());

    }

    @Test
    public void randomTest() {
        ArrayHeapMinPQ<Double> it = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Double> it1 = new NaiveMinPQ<>();
        for (int i = 1; i < 10000; i++) {
            double x = StdRandom.uniform(-50000, 50000);
            if (!it.contains(x)) {
                it.add(x, x);
                it1.add(x, x);
            }
        }
        for (int i = 1; i <= it.size(); i++) {
            assertEquals(it1.removeSmallest(), it.removeSmallest());
        }
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
    public void speedTest1() {
        List<Integer> Ns = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Double> times1 = new ArrayList<>();
        List<Double> times2 = new ArrayList<>();

        ArrayHeapMinPQ<Double> it = new ArrayHeapMinPQ<>();

        for (int index = 31250; index <= 2000000; index = index * 2) {
            Ns.add(index);
            opCounts.add(index);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < index; i++) {
                double item = StdRandom.uniform(-50000, 50000);
                double priority = StdRandom.uniform(-50000, 50000);
                if (!it.contains(item)) {
                    it.add(item, priority);
                }

            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            Stopwatch sw2 = new Stopwatch();
            for (int i = 1; i <= index; i++) {
                it.contains((double) i);
            }
            double timeInSeconds2 = sw2.elapsedTime();
            times2.add(timeInSeconds2);

            Stopwatch sw1 = new Stopwatch();
            for (int i = 1; i <= it.size(); i++) {
                it.removeSmallest();
            }
            double timeInSeconds1 = sw1.elapsedTime();
            times1.add(timeInSeconds1);
        }

        System.out.printf("Timing table for addArrayHeapMinPQ\n");
        printTimingTable(Ns, times, opCounts);
        System.out.printf("Timing table for removeArrayHeapMinPQ\n");
        printTimingTable(Ns, times1, opCounts);
        System.out.printf("Timing table for containArrayHeapMinPQ\n");
        printTimingTable(Ns, times2, opCounts);

    }

    @Test
    public void speedtest2() {
        List<Integer> Ns = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Double> times = new ArrayList<>();

        ArrayHeapMinPQ<Integer> it = new ArrayHeapMinPQ<>();

        for (int index = 31250; index <= 2000000; index = index * 2) {
            Ns.add(index);
            opCounts.add(index);

            for (int i = 1; i < index; i++) {
                int item = StdRandom.uniform(-50000, 50000);
                int priority = StdRandom.uniform(-50000, 50000);
                if (!it.contains(item)) {
                    it.add(item, priority);
                }
            }

            Stopwatch sw = new Stopwatch();
            for (int i = 1; i < index; i++) {
                it.changePriority(it.getSmallest(), i + 1);
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        System.out.printf("Timing table for changeArrayHeapMinPQ\n");
        printTimingTable(Ns, times, opCounts);
    }
}
