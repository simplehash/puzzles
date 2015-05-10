package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.LongestCommonSubsequence;

public class LongestCommonSubsequenceTest {

	@Test
	public void test() {
		assertTrue(LongestCommonSubsequence.find("fred", "fred").equals("fred"));
		assertTrue(LongestCommonSubsequence.find("faa", "aaf").equals("aa"));
		System.out.println(LongestCommonSubsequence.find("fa", "af"));
	}

}
