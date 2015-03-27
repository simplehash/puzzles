package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import puzzles.LongestIncreasingSubsequence;

public class LongestIncreasingSubsequenceTest {
	LongestIncreasingSubsequence l;

	@Test
	public void happy() {
		l = new LongestIncreasingSubsequence(new int[] { 2, 6, 3, 4, 1, 2, 9, 5, 8 });
		assertTrue(Arrays.equals(l.calculate(), new int[] { 2, 3, 4, 5, 8 }));
		l = new LongestIncreasingSubsequence(new int[] { 2, 3, 4, 5, 1, 9 });
		assertTrue(Arrays.equals(l.calculate(), new int[] { 2, 3, 4, 5, 9 }));
	}

	@Test
	public void empty() {
		l = new LongestIncreasingSubsequence(new int[] {});
		assertNull(l.calculate());
	}

	@Test
	public void nullNumbers() {
		l = new LongestIncreasingSubsequence(null);
		assertNull(l.calculate());
	}

	@Test
	public void allSame() {
		l = new LongestIncreasingSubsequence(new int[] { 1, 1, 1 });
		assertTrue(Arrays.equals(l.calculate(), new int[] { 1 }));
	}
}
