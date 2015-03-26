package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.LongestIncreasingSubsequence;

public class LongestIncreasingSubsequenceTest {
	@Test
	public void happy() {
		LongestIncreasingSubsequence l = new LongestIncreasingSubsequence(
				new int[] { 2, 3, 4, 5,1, 9 });
		l.calculate();
	}
}
