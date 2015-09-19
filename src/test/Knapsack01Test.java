package test;

import org.junit.Test;
import puzzles.DP;

import static org.junit.Assert.assertEquals;

public class Knapsack01Test {
    @Test
    public void happy() {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] values = {4, 5, 6, 7, 8, 9, 10, 11};
        int k = DP.knapsack01(values, weights, 8);

        assertEquals(17, k);
    }

    @Test
    public void empty() {
        int[] weights = {0};
        int[] values = {0};
        int k = DP.knapsack01(values, weights, 8);

        assertEquals(0, k);
    }

    @Test
    public void nonGreedy() {
        int[] weights = {3, 2, 2};
        int[] values = {165, 100, 100};
        int k = DP.knapsack01(values, weights, 8);

        assertEquals(365, k);
    }

    // And the usual null tests
}
