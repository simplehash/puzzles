package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import puzzles.DP;

public class WordSegmentationTest {

	@Test
	public void test() {
		List<String> dictionary = new ArrayList<>();
		dictionary.add("i");
		dictionary.add("like");
		dictionary.add("sam");
		dictionary.add("sung");
		dictionary.add("samsung");
		dictionary.add("fred");
		assertTrue(DP.wordSegmentation("ilikesamsung", dictionary));
		assertTrue(DP.wordSegmentation("ilikefred", dictionary));
	}

}
