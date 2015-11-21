package puzzles;

public class Tests {
	/**
	 * Assume all null checks are done
	 */
	public static void main(String[] args) {
		maxProfit(new int[] { 5, 8, 4, 0, 4 });
	}

	private static void maxProfit(int[] prices) {
		int currentBuyPrice = prices[0];
		int maxProfit = 0;

		int i = 0;
		while (i < prices.length) {
			currentBuyPrice = Math.min(currentBuyPrice, prices[i]);
			maxProfit = Math.max(maxProfit, prices[i] - currentBuyPrice);
			i++;
		}

		System.out.println("Max profit is " + maxProfit);
	}

	private static void hops(int[] values) {
		int currentFarthest = values[0];
		for (int i = 0; i < values.length && i <= currentFarthest; i++) {
			currentFarthest = Math.max(currentFarthest, i + values[i]);
			if (currentFarthest >= values.length) {
				System.out.println("Reachable!");
				return;
			}
		}
		System.out.println("Not reachable!");
	}

	private static void greatestSubArray(int[] values) {
		int firstIndex = 0;
		int lastIndex = 0;
		int globalMax = values[0];

		int currentMax = values[0];
		int currentFirstIndex = 0;
		int currentLastIndex = 0;

		while (currentLastIndex < values.length) {
			if (currentMax < 0) {
				currentMax = values[currentLastIndex];
				currentFirstIndex = currentLastIndex++;
				currentLastIndex = currentFirstIndex;
			} else if (currentMax > globalMax) {
				globalMax = currentMax;
				firstIndex = currentFirstIndex;
				lastIndex = currentLastIndex;
			} else if (currentMax <= globalMax) {
				if (++currentLastIndex < values.length) {
					currentMax += values[currentLastIndex];
				}
			}
		}

		System.out.println(
				"Greatest subarray found between indices " + firstIndex + " and " + lastIndex + ", inclusive.");
	}

	private static void consecutiveSumEqualTo(int[] values, int desiredSum) {
		int firstIndex = 0;
		int lastIndex = 0;
		int currentSum = values[0];

		while (lastIndex < values.length) {
			if (currentSum == desiredSum) {
				System.out.println("Found it! Bounded by " + firstIndex + " and " + lastIndex + ", inclusive.");
				break;
			} else if (currentSum < desiredSum) {
				currentSum += values[++lastIndex];
			} else {
				currentSum -= values[firstIndex++];
			}
		}
		if (lastIndex == values.length) {
			System.out.println("Not found!");
		}
	}

	private static void alternatingArray(int[] values) {
		for (int i = 0; i < values.length - 1; i++) {
			if ((i % 2 == 0 && values[i] < values[i + 1]) || (i % 2 != 0 && values[i] > values[i + 1])) {
				int temp = values[i];
				values[i] = values[i + 1];
				values[i + 1] = temp;
			}
			System.out.println(values[i]);
		}
		System.out.println(values[values.length - 1]);
	}

	private static void permutations(String prefix, String remainder) {
		int length = remainder.length();
		if (length == 0) {
			System.out.println(prefix);
		} else {
			for (int i = 0; i < length; i++) {
				permutations(prefix + remainder.charAt(i), remainder.substring(0, i) + remainder.substring(i + 1));
			}
		}
	}
}
