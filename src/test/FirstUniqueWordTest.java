package test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import puzzles.FirstUniqueWord;

public class FirstUniqueWordTest {
	FirstUniqueWord f;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void happy() throws Exception {
		f = new FirstUniqueWord("fred fred a hi sadf hi hi a");
		assertTrue(f.getAnswer().equals("sadf"));
	}

	@Test
	public void noUnique() throws Exception {
		f = new FirstUniqueWord("fred fred");
		assertNull(f.getAnswer());
	}

	@Test
	public void empty() throws Exception {
		f = new FirstUniqueWord("");
		assertNull(f.getAnswer());
	}

	@Test
	public void nullParagraph() throws Exception {
		exception.expect(Exception.class);
		f = new FirstUniqueWord(null);
		assertNull(f.getAnswer());
	}

	@Test
	public void alphaNumeric() throws Exception {
		f = new FirstUniqueWord("fred fred 12 12 5");
		assertTrue(f.getAnswer().equals("5"));
	}
}
