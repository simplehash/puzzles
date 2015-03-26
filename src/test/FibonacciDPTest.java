package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.FibonacciDP;

public class FibonacciDPTest {

	@Test
	public void test() {
		FibonacciDP f = new FibonacciDP();
		assertEquals(f.bottomUp(8), 21);
	}

}
