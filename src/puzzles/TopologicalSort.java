package puzzles;

import java.util.*;

public class TopologicalSort {
    public static Set<Integer> bfs(int[][] adjacencyMatrix, Integer[] roots) {
        int rowLength = adjacencyMatrix.length;
        int colLength = adjacencyMatrix[0].length;
        if (adjacencyMatrix == null || rowLength != colLength || colLength < 1 || roots == null) {
            return null;
        }
        for (int root : roots) {
            if (root < 0 || root >= adjacencyMatrix.length - 1) {
                return null;
            }
        }

        // Essentially BFS, keeping a list of nodes to visit
        Queue<Integer> temp = new LinkedList<>(Arrays.asList(roots));
        // Final answer
        Set<Integer> answer = new LinkedHashSet<>(Arrays.asList(roots));

        while (!temp.isEmpty()) {
            int node = temp.remove();
            for (int target = 0; target < colLength; target++) {
                if (adjacencyMatrix[node][target] > 0) {
                    /*
                     * It's important to use Integer.valueOf(...) here because
					 * otherwise it'd remove the item at the specified index
					 * rather than with the specified value
					 */
                    Integer value = Integer.valueOf(target);
                    temp.add(value);
                    answer.remove(value);
                    answer.add(value);
                }
            }
        }
        return answer;
    }
}