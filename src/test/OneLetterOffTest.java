package test;

import org.junit.Test;
import puzzles.OneLetterOff;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OneLetterOffTest {

	@Test
	public void test() {
		assertTrue(OneLetterOff.check("fred", "fre"));
		assertTrue(OneLetterOff.check("fre", "fred"));
		assertTrue(OneLetterOff.check("fred", "frdd"));
		assertFalse(OneLetterOff.check("fred", "fred"));
		assertFalse(OneLetterOff.check("fred", "ed"));
		assertFalse(OneLetterOff.check("", ""));
		assertFalse(OneLetterOff.check("fred", ""));
	}

}
