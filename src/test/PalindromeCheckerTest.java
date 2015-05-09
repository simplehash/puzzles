package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.PalindromeChecker;

public class PalindromeCheckerTest {

	@Test
	public void test() {
		assertTrue(PalindromeChecker.check("frrf"));
		assertTrue(PalindromeChecker.check("freeeeeeerf"));
		assertTrue(PalindromeChecker.check("ff"));
		assertTrue(PalindromeChecker.check("f"));
		assertTrue(PalindromeChecker.check(""));
		assertFalse(PalindromeChecker.check("frr"));
		assertFalse(PalindromeChecker.check("fr"));
		assertFalse(PalindromeChecker.check(null));
	}

}
