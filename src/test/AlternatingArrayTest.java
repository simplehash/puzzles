package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import puzzles.AlternatingArray;

public class AlternatingArrayTest {
	AlternatingArray a;

	@Test
	public void happy() {
		a = new AlternatingArray(new int[] { 10, 12, 20, 80, 100 });
		assertTrue(Arrays.equals(a.calculate(), new int[] { 10, 20, 12, 100, 80 }));
	}
}
