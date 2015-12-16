package puzzles;

public class MaxConsecutiveProduct {
	public static int go(int[] numbers) {
		if (numbers == null || numbers.length == 0) {
			return Integer.MIN_VALUE;
		}
		if (numbers.length == 1) {
			return numbers[0];
		}

		int currentMax = numbers[0];
		int currentMin = numbers[0];
		int max = currentMax;

		for (int number : numbers) {
			if (number == 0) {
				currentMax = 1;
				currentMin = 1;
			} else {
				currentMax = Math.max(currentMax * number, Math.max(currentMin * number, number));
				currentMin = Math.min(currentMax, Math.min(currentMin * number, number));
				max = Math.max(currentMax, max);
			}
		}

		return max;
	}
}
