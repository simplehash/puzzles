package test;

import static org.junit.Assert.*;

import org.junit.Test;

import puzzles.RotatedSortedList;

public class RotatedSortedListTest {
	RotatedSortedList r;

	@Test
	public void happy() {
		int[] list = new int[] { 4, 5, 6, 7, 8, 1, 2, 3 };
		r = new RotatedSortedList(list);
		for (int i = 0; i < list.length; i++) {
			assertTrue(r.find(list[i]).equals(i));
		}
	}

	@Test
	public void notFound() {
		int[] list = new int[] { 4, 5, 6, 7, 8, 1, 2, 3 };
		r = new RotatedSortedList(list);
		assertNull(r.find(0));
	}
}
