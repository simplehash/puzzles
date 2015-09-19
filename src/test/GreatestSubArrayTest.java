package test;

import org.junit.Test;
import puzzles.GreatestSubArray;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GreatestSubArrayTest {
    GreatestSubArray g;

    @Test
    public void allPositive() {
        g = new GreatestSubArray(new int[]{0, 1, 2});
        assertEquals(3, g.getMax().intValue());
    }

    @Test
    public void negative() {
        g = new GreatestSubArray(new int[]{-1, -2, -3});
        assertEquals(-1, g.getMax().intValue());
    }

    @Test
    public void negativeAndPositive() {
        g = new GreatestSubArray(new int[]{-1, -2, -3, 1, 2, 3});
        assertEquals(6, g.getMax().intValue());
    }

    @Test
    public void negativePositiveMixed() {
        g = new GreatestSubArray(new int[]{-1, 3, -2, 4, -3, 2, 3});
        assertEquals(7, g.getMax().intValue());
    }

    @Test
    public void singleElement() {
        g = new GreatestSubArray(new int[]{-1});
        assertEquals(-1, g.getMax().intValue());
    }

    @Test
    public void noElements() {
        g = new GreatestSubArray(new int[]{});
        assertNull(g.getMax());
    }
}
