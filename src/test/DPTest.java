package test;

import org.junit.Test;
import puzzles.DP;

import static org.junit.Assert.assertEquals;

public class DPTest {

	@Test
	public void test() {
		assertEquals(DP.changeMaker(new int[] { 1, 3, 4 }, 6), 2);
		assertEquals(DP.changeMaker(new int[] { 4, 3, 1 }, 6), 2);
	}
}
