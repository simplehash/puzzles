package test;

import org.junit.Test;

import puzzles.NQueens;

public class NQueensTest {

	@Test
	public void test() {
		int[][] answer = NQueens.nQueens(4);
		for (int[] a : answer) {
			for (int i : a) {
				System.out.print(i);
			}
			System.out.println("");
		}
	}

}
