package puzzles;

public class GreatestSubArray {
	private Integer max;

	public GreatestSubArray(int[] numbers) {
		if (numbers != null && numbers.length > 0) {
			max = numbers[0]; // Actual max
			int startIndex = 0;
			int endIndex = 0;
			int currentMax = numbers[0]; // Running total for current max
			int currentStartIndex = 0;
			int currentEndIndex = 0;
			for (int i = 1; i < numbers.length; i++) {
				if (currentMax < 0) {
					currentMax = numbers[i];
					currentStartIndex = i;
					currentEndIndex = i;
				} else {
					currentMax += numbers[i];
					currentEndIndex = i;
				}
				if (currentMax > max) {
					max = currentMax;
					startIndex = currentStartIndex;
					endIndex = currentEndIndex;
				}
			}
			System.out.println(
					"Max sub-array: " + max + ", found between " + startIndex + " and " + endIndex + ", inclusive.");
		}
	}

	public Integer getMax() {
		return max;
	}

}
