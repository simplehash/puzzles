package test;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import puzzles.TVKeyboard;

public class TVKeyboardTest {
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void happy() throws Exception {
		TVKeyboard t = new TVKeyboard("fred", 5);
		t.getSequence();
	}

	@Test
	public void space() throws Exception {
		TVKeyboard t = new TVKeyboard("fred fred", 5);
		t.getSequence();
	}

	@Test
	public void narrow() throws Exception {
		TVKeyboard t = new TVKeyboard("fred fred", 1);
		t.getSequence();
	}

	@Test
	public void wide() throws Exception {
		TVKeyboard t = new TVKeyboard("fred fred", 26);
		t.getSequence();
	}

	@Test
	public void nullSentence() throws Exception {
		ex.expect(Exception.class);
		TVKeyboard t = new TVKeyboard(null, 5);
	}

	@Test
	public void impossible() throws Exception {
		ex.expect(Exception.class);
		TVKeyboard t = new TVKeyboard("fred fred", 0);
	}

	@Test
	public void empty() throws Exception {
		TVKeyboard t = new TVKeyboard("", 5);
	}
}
