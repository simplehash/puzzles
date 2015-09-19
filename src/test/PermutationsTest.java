package test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import puzzles.Permutations;

import static org.junit.Assert.assertEquals;

public class PermutationsTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();
	Permutations p;

	@Test
	public void happy() throws Exception {
		p = new Permutations("Fred");
		assertEquals(24, p.getCount());
	}

	/*@Test
	public void nullString() throws Exception {
		exception.expect(Exception.class);
		p = new Permutations(null);
	}*/

	@Test
	public void oneLetter() throws Exception {
		p = new Permutations("a");
		assertEquals(1, p.getCount());
	}

	@Test
	public void emptyString() throws Exception {
		p = new Permutations("");
		assertEquals(1, p.getCount());
	}
}
