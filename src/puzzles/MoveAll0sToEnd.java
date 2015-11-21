package puzzles;

public class MoveAll0sToEnd {
	public static void rearrange(int[] numbers) {
		if (numbers == null || numbers.length <= 1) {
			return;
		}

		int leftIndex = 0;
		int rightIndex = numbers.length - 1;
		while (leftIndex <= rightIndex) {
			if (numbers[rightIndex] == 0) {
				rightIndex--;
			} else if (numbers[leftIndex] != 0) {
				leftIndex++;
			} else {
				numbers[leftIndex] = numbers[rightIndex];
				numbers[rightIndex] = 0;
				leftIndex++;
				rightIndex--;
			}
		}
	}
}
