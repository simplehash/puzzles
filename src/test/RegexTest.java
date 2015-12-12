package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.Regex;

public class RegexTest {

	@Test
	public void test() {
		assertTrue(Regex.regex("a", "a"));
		assertFalse(Regex.regex("a", "b"));
		assertFalse(Regex.regex("a.", "b"));
		assertTrue(Regex.regex("a*", "a"));
		assertTrue(Regex.regex("a*a", "aa"));
		assertTrue(Regex.regex("a*b", "b"));
		assertTrue(Regex.regex("a.*d", "abcd"));
		assertTrue(Regex.regex("a.*d", "abcasdasdasd"));
		assertFalse(Regex.regex("a*.d", "abcasdasdasd"));
	}

}
