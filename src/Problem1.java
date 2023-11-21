import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Problem1 {

    /*
     * Handles getting input from file in order to be sent off to a method that determines the feasibility of the graph.
     */
    public static void P1 () {
        // Gets list of edges per vertex.
        ArrayList<Integer> edgesPerVertex = new ArrayList<>();
        try {
            File inputFile = new File("./input1.txt");
            Scanner fileIn = new Scanner(inputFile);
            fileIn.useDelimiter(" ");

            // Get each amount of edges into list.
            while (fileIn.hasNext()) {
                edgesPerVertex.add(Integer.parseInt(fileIn.next()));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Determine whether the edges per vertex can actually form a graph.
        System.out.println(graphFeasibility(edgesPerVertex));
    }

    /*
     * Determines greedily whether a set of edges per vertex can form an undirected graph.
     */
    private static String graphFeasibility(ArrayList<Integer> edgesPerVertex) {
        // Covers the case of the null graph.
        if (edgesPerVertex.isEmpty()) {
            return "yes";
        }

        // Does an initial check to make sure there are no non-negative integers in the list.
        for (Integer vertex : edgesPerVertex) {
            if (vertex < 0) {
                return "no";
            }
        }

        // Iterates through vertices, always choosing the one with the most amount of edges first, by removing said
        // element k and decrementing the next k elements by 1. Returns no if there are not enough elements to decrement
        // or if an element being decremented falls below 0.
        edgesPerVertex.sort(Collections.reverseOrder());
        while (!edgesPerVertex.isEmpty() && edgesPerVertex.get(0) != 0) {
            int numConnections = edgesPerVertex.remove(0);
            if (numConnections > edgesPerVertex.size()) {
                return "no";
            }

            for (int i = 0; i < numConnections; i++) {
                if (edgesPerVertex.get(i) == 0) {
                    return "no";
                }
                edgesPerVertex.set(i, edgesPerVertex.get(i) - 1);
            }

            edgesPerVertex.sort(Collections.reverseOrder());
        }

        // If we haven't hit a return statement by this point, the graph must be valid by having only 0s left in it!
        return "yes";
    }
}
