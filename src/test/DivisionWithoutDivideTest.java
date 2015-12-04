package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.DivisionWithoutDivide;

public class DivisionWithoutDivideTest {

	@Test
	public void test() {
		assertEquals(4.5, DivisionWithoutDivide.divide(9, 2), 0);
		assertEquals(4, DivisionWithoutDivide.divide(8, 2), 0);
		assertEquals(1, DivisionWithoutDivide.divide(-1, -1), 0);
		assertEquals(-1, DivisionWithoutDivide.divide(1, -1), 0);
		assertEquals(-1, DivisionWithoutDivide.divide(-1, 1), 0);
		assertEquals(0, DivisionWithoutDivide.divide(0, 1), 0);
	}

}
