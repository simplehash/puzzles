package puzzles;

public class Robbery {
	/**
	 * Linear houses to rob, where adjacent houses cannot be robbed. Values is
	 * the list of house values by index. Similar to
	 * http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-
	 * adjacent/
	 */
	public static int linear(int[] values) {
		int includingCurrent = 0;
		int previousIncludingCurrent = includingCurrent;
		int excludingCurrent = 0;

		for (int value : values) {
			previousIncludingCurrent = includingCurrent;
			includingCurrent = excludingCurrent + value;
			excludingCurrent = Math.max(previousIncludingCurrent, excludingCurrent);
		}

		return Math.max(includingCurrent, excludingCurrent);
	}
}
