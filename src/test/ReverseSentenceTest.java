package test;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import puzzles.ReverseSentence;

public class ReverseSentenceTest {
	@Rule
	public ExpectedException exc = ExpectedException.none();

	@Test
	public void happy() throws Exception {
		ReverseSentence r = new ReverseSentence("Hi my name is Fred");
		assertTrue(r.getAnswer().equals("Fred is name my Hi"));
	}

	@Test
	public void nullSentence() throws Exception {
		exc.expect(Exception.class);
		ReverseSentence r = new ReverseSentence(null);
	}

	@Test
	public void emptySentence() throws Exception {
		ReverseSentence r = new ReverseSentence("");
		assertTrue(r.getAnswer().equals(""));
	}
}
