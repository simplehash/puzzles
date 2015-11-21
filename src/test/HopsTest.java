package test;

import org.junit.Test;
import puzzles.Hops;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HopsTest {
	@Test
	public void test() {
		assertFalse(Hops.go(new int[] { 1, 2, 0, 1, 0, 1 }));
		assertTrue(Hops.go(new int[] { 1, 2, 0, 1, 0 }));
		assertTrue(Hops.go(new int[] { 0 }));
		assertFalse(Hops.go(null));
		assertFalse(Hops.go(new int[0]));
	}

}
