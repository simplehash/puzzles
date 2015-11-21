package puzzles;

public class RotatedSortedList {
	private int[] list;

	public RotatedSortedList(int[] list) {
		if (list != null && list.length > 0) {
			this.list = list;
		}
	}

	public Integer find(int value) {
		/*
		 * General idea here is that each half the array will be a regular
		 * sorted list. We check if the value is in that half (left or right) of
		 * the list and drop 1/2 the array based on that assumption
		 */
		int lowIndex = 0;
		int highIndex = list.length - 1;

		while (lowIndex <= highIndex) {
			int midIndex = (lowIndex + highIndex) / 2;
			if (list[midIndex] == value) {
				return midIndex;
			}

			if (lowIndex == highIndex) { // 1 element left and it's not the
				// value we want
				break;
			}

			if (list[lowIndex] < list[midIndex]) { // Left 1/2 sorted
				if (value >= list[lowIndex] && value <= list[midIndex]) { // Item
					// in
					// left
					highIndex = midIndex - 1;
				} else {// Item in right
					lowIndex = midIndex + 1;
				}
			} else if (list[midIndex] < list[highIndex]) { // Right 1/2 sorted
				if (value >= list[midIndex] && value <= list[highIndex]) { // Item
					// in
					// right
					lowIndex = midIndex + 1;
				} else { // Item in left
					highIndex = midIndex - 1;
				}
			}
		}
		return null;
	}
}
