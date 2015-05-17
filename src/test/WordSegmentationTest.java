package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import puzzles.WordSegmentation;

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
		assertTrue(WordSegmentation.go("ilikesamsung", dictionary));
		assertTrue(WordSegmentation.go("ilikefred", dictionary));
	}

}
