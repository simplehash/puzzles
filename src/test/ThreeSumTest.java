package test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import puzzles.ThreeSum;

public class ThreeSumTest {

	@Test
	public void happy() {
		assertArrayEquals(ThreeSum.find(new int[] { -1, -2, 3 }, 0), new int[] { -1, -2, 3 });
	}

	@Test
	public void bigger() {
		assertArrayEquals(ThreeSum.find(new int[] { 5, 6, 7, -1, -2, 3 }, 0), new int[] { -1, -2, 3 });
	}
}
