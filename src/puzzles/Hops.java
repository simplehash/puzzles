package puzzles;

public class Hops {
	public static boolean go(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		/*
		 * You are given an array of non-negative integers (0, 1, 2 etc). The
		 * value in each element represents the number of hops you may take to
		 * the next destination. Write a function that determines when you start
		 * from the first element whether you will be able to reach the last
		 * element of the array.
		 * 
		 * if a value is 3, you can take either 0, 1, 2 or 3 hops.
		 * 
		 * For eg: for the array with elements 1, 2, 0, 1, 0, 1, any route you
		 * take from the first element, you will not be able to reach the last
		 * element.
		 */
		int max = 0;
		for (int i = 0; i < array.length && i <= max; i++) {
			max = Math.max(max, array[i] + i);
			if (max >= array.length - 1) {
				return true;
			}
		}
		return false;
	}
}
