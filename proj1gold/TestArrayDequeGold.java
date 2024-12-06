import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /* @source StudentArrayDequeLauncher
     * */
    @Test
    public void testStuArray() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String message = "";

        while (true) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            int i = StdRandom.uniform(10);

            if (numberBetweenZeroAndOne < 0.25) {
                student.addFirst(i);
                solution.addFirst(i);
                message = message + "addFirst(" + i + ")\n";

            } else if (numberBetweenZeroAndOne < 0.5) {
                student.addLast(i);
                solution.addLast(i);
                message = message + "addLast(" + i + ")\n";


            } else if (numberBetweenZeroAndOne < 0.75) {
                if (student.size() > 0) {
                    message = message + "removeFirst()\n";
                    assertEquals(message, student.removeFirst(), solution.removeFirst());

                }
            } else {
                if (student.size() > 0) {
                    message = message + "removeLast()\n";
                    assertEquals(message, student.removeLast(), solution.removeLast());

                }
            }
        }

    }
}

