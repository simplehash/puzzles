package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.MaxConsecutiveProduct;

public class MaxConsecutiveProductTest {

	@Test
	public void test() {
		assertEquals(180, MaxConsecutiveProduct.go(new int[] { 6, -3, -10, 0, 2 }));
		assertEquals(60, MaxConsecutiveProduct.go(new int[] { -1, -3, -10, 0, 60 }));
		assertEquals(80, MaxConsecutiveProduct.go(new int[] { -2, -3, 0, -2, -40 }));
	}
}
