package test;

import org.junit.Test;
import puzzles.TopologicalSort;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class TopologicalSortTest {

    @Test
    public void happy() {
        int[][] adjacencyMatrix = new int[][]{{0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 0}};
        Set<Integer> top = TopologicalSort.bfs(adjacencyMatrix, new Integer[]{2});
        Set<Integer> correct = new LinkedHashSet<>();
        correct.add(2);
        correct.add(0);
        correct.add(1);
        correct.add(3);
        correct.add(4);
        assertTrue(top.equals(correct));
    }

}
