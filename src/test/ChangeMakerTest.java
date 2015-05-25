package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.ChangeMaker;

public class ChangeMakerTest {

	@Test
	public void test() {
		assertEquals(ChangeMaker.go(new int[] { 1, 3, 4 }, 6), 2);
		assertEquals(ChangeMaker.go(new int[] { 4, 3, 1 }, 6), 2);
	}
}
