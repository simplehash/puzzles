package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.StringEditDistance;

public class StringEditDistanceTest {
	StringEditDistance s;

	@Test
	public void same() throws Exception {
		s = new StringEditDistance("fred", "fred");
		assertEquals(0, s.getAnswer());

	}

	@Test
	public void sameLength() throws Exception {
		s = new StringEditDistance("derf", "fred");
		assertEquals(4, s.getAnswer());
		s.getMatrix();
	}

	@Test
	public void different() throws Exception {
		s = new StringEditDistance("derfasdf", "fred");
		assertEquals(6, s.getAnswer());
		s.getMatrix();
	}

	@Test
	public void empty() throws Exception {
		s = new StringEditDistance("", "fred");
		assertEquals(4, s.getAnswer());
		s.getMatrix();
		s = new StringEditDistance("fred", "");
		assertEquals(4, s.getAnswer());
		s.getMatrix();
	}
}
