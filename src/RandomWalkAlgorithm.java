/*  -------------------------------------------------------
 *  RandomWalkAlgorithm.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Date:	 November 12th 2020
 *  Class:   COSC4436
 *  ------------------------------------------------------- */

import java.util.Random;
import java.util.ArrayList;

public class RandomWalkAlgorithm {
    // Variables
    private Random random;
    private final int MAX_STEPS = 98;
    private int stepsTaken, x, y;
    private ArrayList<String> steps;

    // No-arg Constructor
    public RandomWalkAlgorithm() {
        random = new Random();
        steps = new ArrayList<>();
        stepsTaken = 0;
        x = 0;
        y = 0;
    }

    /*
     * -- Methods --
     * 1. run()
     * 2. printResults()
     */

    // run()
    public long[] run() {
        long[] results = new long[2000];

        for (int i = 0; i < 1000; i++) {
            x = 0;
            y = 0;

            long start = System.nanoTime();
            while ((Math.abs(x) + Math.abs(y)) < MAX_STEPS) {
                switch (random.nextInt(4)) {
                    // North
                    case 0:
                        y += 1;
                        stepsTaken++;
                        break;

                    // South
                    case 1:
                        y -= 1;
                        stepsTaken++;
                        break;

                    // West
                    case 2:
                        x += 1;
                        stepsTaken++;
                        break;

                    // East
                    case 3:
                        x -= 1;
                        stepsTaken++;
                        break;
                }
            }
            long end = System.nanoTime();

            results[i] = start;
            results[i+1] = end;
            steps.add(stepsTaken + "");
            stepsTaken = 0;
        }

        return results;
    }

    // printResults()
    public void printResults(long[] results) {
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------- Random Walk Algorithm Test --------------------------------------------------------------");
        System.out.println(" This test is not run on a simulated network due to the random nature of it. The maximum number of steps to be taken is 98, we will run the random walk");
        System.out.println(" 1000 times. We keep track of the coordinates that we generate as we take steps. We start off with a random position of steps, x and y.");
        System.out.println();

        long[] differences = new long[1000];
        for (int i = 0; i < differences.length; i++) {
            System.out.println(" -- Results Test #" + (i+1) + " --");
            System.out.println(" Steps Taken This Walk: " + steps.get(i));
            System.out.println(" Starting Time: " + results[i]);
            System.out.println(" Ending Time: " + results[i+1]);
            differences[i] = Math.abs(results[i] - results[i+1]);

            System.out.println(String.format(" Difference (Nanoseconds): %d", differences[i]));
            System.out.println();
        }

        int avgRuntime = 0;
        for (long i : differences)
            avgRuntime += i;

        System.out.println(String.format(" Best Average Runtime: %d", Math.abs(avgRuntime/1000)));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
