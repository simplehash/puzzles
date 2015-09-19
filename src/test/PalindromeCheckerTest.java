package test;

import org.junit.Test;
import puzzles.PalindromeChecker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeCheckerTest {

    @Test
    public void test() {
        assertTrue(PalindromeChecker.check("frrf"));
        assertTrue(PalindromeChecker.check("freeeeeeerf"));
        assertTrue(PalindromeChecker.check("ff"));
        assertTrue(PalindromeChecker.check("f"));
        assertTrue(PalindromeChecker.check(""));
        assertFalse(PalindromeChecker.check("frr"));
        assertFalse(PalindromeChecker.check("fr"));
        assertFalse(PalindromeChecker.check(null));
    }

}
