package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.Palindrome;

public class PalindromeTest {

	@Test
	public void test() {
		assertFalse(Character.isLetter(' '));
		assertTrue(Palindrome.is("racecar"));
		assertFalse(Palindrome.is("Hi Fred, how's it going?"));
		assertTrue(Palindrome.is("Racecar"));
		assertTrue(Palindrome.is("R.a'c\"ecar"));
		assertTrue(Palindrome.is("A man, a plan, a canal, Panama!"));
	}

}
