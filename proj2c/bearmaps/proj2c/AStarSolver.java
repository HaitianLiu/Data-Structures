package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;
    private HashMap<Vertex, Double> distToMap = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeToMap = new HashMap<>();
    private ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        solution = new ArrayList<>();

        if (start.equals(end)) {
            solution.add(start);
            solutionWeight = 0;
            numStatesExplored = 0;
            outcome = SolverOutcome.SOLVED;
            timeSpent = sw.elapsedTime();
            return;
        }

        distToMap.put(start, 0.0);
        edgeToMap.put(start, start);
        pq.add(start, distToMap.get(start) + input.estimatedDistanceToGoal(start, end));

        while (pq.size() > 0 && !pq.getSmallest().equals(end) && sw.elapsedTime() < timeout) {
            Vertex p = pq.removeSmallest();
            numStatesExplored += 1;
            for (WeightedEdge<Vertex> e : input.neighbors(p)) {
                relax(e, input, end);
            }
        }

        if (pq.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
            solutionWeight = 0;
        } else if (timeSpent > timeout) {
            outcome = SolverOutcome.TIMEOUT;
            solutionWeight = 0;
        } else {
            outcome = SolverOutcome.SOLVED;
            solutionWeight = distToMap.get(end);
            Vertex parent = edgeToMap.get(end);
            solution.add(end);
            while (!parent.equals(start)) {
                solution.add(0, parent);
                parent = edgeToMap.get(parent);
            }
            solution.add(0, start);
        }

        timeSpent = sw.elapsedTime();
    }

    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input, Vertex goal) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();

        if (!distToMap.containsKey(q)) {
            distToMap.put(q, Double.POSITIVE_INFINITY);
        }
        double dist = distToMap.get(p) + w;
        if (dist < distToMap.get(q)) {
            distToMap.put(q, dist);
            edgeToMap.put(q, p);
            if (pq.contains(q)) {
                pq.changePriority(q, distToMap.get(q) + input.estimatedDistanceToGoal(q, goal));
            } else {
                pq.add(q, distToMap.get(q) + input.estimatedDistanceToGoal(q, goal));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
