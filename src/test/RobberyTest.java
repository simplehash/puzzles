package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.Robbery;

public class RobberyTest {

	@Test
	public void linearTest() {
		assertEquals(Robbery.linear(new int[] { 5, 5, 10, 40, 50, 35 }), 80);
		assertEquals(Robbery.linear(new int[] { -5, 0, 10, 10 }), 10);
		assertEquals(Robbery.linear(new int[] { -5, -4, -2, -3, -10 }), 0);
	}

}
