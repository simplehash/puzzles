package test;

import org.junit.Test;
import puzzles.DP;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void happy() {
        assertTrue(Arrays.equals(DP.longestIncreasingSubsequence(new int[]{2, 6, 3, 4, 1, 2, 9, 5, 8}), new int[]{2, 3, 4, 5, 8}));

        assertTrue(Arrays.equals(DP.longestIncreasingSubsequence(new int[]{2, 3, 4, 5, 1, 9}), new int[]{2, 3, 4, 5, 9}));
    }

    @Test
    public void empty() {
        assertNull(DP.longestIncreasingSubsequence(new int[]{}));
    }

    @Test
    public void nullNumbers() {
        assertNull(DP.longestIncreasingSubsequence(null));
    }

    @Test
    public void allSame() {
        assertTrue(Arrays.equals(DP.longestIncreasingSubsequence(new int[]{1, 1, 1}), new int[]{1}));
    }
}
