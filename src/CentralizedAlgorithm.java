/*  -------------------------------------------------------
 *  CentralizedAlgorithm.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Date:	 November 12th 2020
 *  Class:   COSC4436
 *  ------------------------------------------------------- */

public class CentralizedAlgorithm {
    // Variables
    public class Node implements Runnable{
        public void run() {
            new FloodingAlgorithm().run();
        }
    }

    private BinarySearchTree treeNetwork;

    // No-arg Constructor
    public CentralizedAlgorithm() {
        // Populate tree with Nodes
        treeNetwork = new BinarySearchTree();
        for (int x = 0; x <= 20; x++)
            treeNetwork.insert(new Node());
    }

    /*
     * -- Methods --
     * 1. run()
     * 2. printResults()
     */

    // run()
    public long[] run() {
        // Start Algorithm
        long[] times = new long[2000];

        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            treeNetwork.traverseAndRun();
            long end = System.nanoTime();

            times[i] = start;
            times[i+1] = end;
        }

        return times;
    }

    // printResults()
    public void printResults(long[] results) {
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------- Centralized Algorithm Test --------------------------------------------------------------");
        System.out.println(" This test is run on a simulated tree network of 20 TreeNodes, each TreeNode containing its own instance of a flooding algorithm object.");
        System.out.println(" The tree network is first instantiated and then traversed, as each TreeNode is visited, the internal flooding algorithm object is run.");
        System.out.println(" The flooding algorithm is run on a 2D Matrix [50x7]. It floods the matrix recursively, visiting each indexed position which simulates a network node.");
        System.out.println(" Once the node is visited, it gets marked off as visited. We can see the matrix change as all the unvisited nodes become visited.");
        System.out.println();
        System.out.println(" 20 TreeNode Network --> Each TreeNode Contains a 2D Matrix [50x7] which will run a Flooding Algorithm");
        System.out.println(" Tree Network Height: " + treeNetwork.height());
        System.out.println(" Running 1000 test cases on 1000 trees, each tree made up of 98 TreeNodes, each TreeNode containing a Flooding Algorithm object...");
        System.out.println();

        long[] differences = new long[1000];
        for (int i = 0; i < results.length/2; i++) {
            System.out.println(" -- Results Test #" + (i+1) + " --");
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

