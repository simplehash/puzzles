package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.Knapsack01;

public class Knapsack01Test {
	@Test
	public void happy() {
		int[] weights = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] values = { 4, 5, 6, 7, 8, 9, 10, 11 };
		Knapsack01 k = new Knapsack01(weights, values, 8);

		assertEquals(17, k.run());
	}

	@Test
	public void empty() {
		int[] weights = { 0 };
		int[] values = { 0 };
		Knapsack01 k = new Knapsack01(weights, values, 8);

		assertEquals(0, k.run());
	}

	@Test
	public void nonGreedy() {
		int[] weights = { 3, 2, 2 };
		int[] values = { 165, 100, 100 };
		Knapsack01 k = new Knapsack01(weights, values, 4);

		assertEquals(200, k.run());
	}

	// And the usual null tests
}
