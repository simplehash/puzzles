package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.TopologicalSort;
import java.util.*;

public class TopologicalSortTest {

	@Test
	public void test() {
		int[][] adjacencyMatrix = new int[][] { { 0, 1, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 } };
		Set<Integer> top = TopologicalSort.bfs(adjacencyMatrix, new Integer[] { 2 });
		for (Integer i : top) {
			System.out.print(i + " ");
		}
	}

}
