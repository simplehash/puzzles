package test;

import org.junit.Test;
import puzzles.DP;

import static org.junit.Assert.assertEquals;

public class StringEditDistanceTest {

    @Test
    public void same() throws Exception {
        assertEquals(0, DP.stringEditDistance("fred", "fred"));

    }

    @Test
    public void sameLength() throws Exception {
        assertEquals(4, DP.stringEditDistance("derf", "fred"));
    }

    @Test
    public void different() throws Exception {
        assertEquals(6, DP.stringEditDistance("derfasdf", "fred"));
    }

    @Test
    public void empty() throws Exception {
        assertEquals(4, DP.stringEditDistance("", "fred"));

        assertEquals(4, DP.stringEditDistance("fred", ""));
    }
}
