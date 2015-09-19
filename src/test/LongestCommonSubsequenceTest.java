package test;

import org.junit.Test;
import puzzles.DP;

import static org.junit.Assert.assertTrue;

public class LongestCommonSubsequenceTest {

    @Test
    public void lcsTest() {
        assertTrue(DP.longestCommonSubsequence("fred", "fred").equals("fred"));
        assertTrue(DP.longestCommonSubsequence("faa", "aaf").equals("aa"));
        System.out.println(DP.longestCommonSubsequence("fred", "freed"));
    }

    @Test
    public void diffTest() {
        DP.diff("freeeeeed sdfas", "freed akkksfasdf");
    }

}
