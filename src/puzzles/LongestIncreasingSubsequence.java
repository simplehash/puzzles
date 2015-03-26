package puzzles;

import java.util.*;

public class LongestIncreasingSubsequence {
	private int[] sequence;

	public LongestIncreasingSubsequence(int[] numbers) {
		if (numbers != null && numbers.length > 0) {
			sequence = numbers;
		}
	}

	public int[] calculate() {
		if (sequence == null || sequence.length < 1) {
			return null;
		}
		// LIS of length i ending at sequence[i]
		List<Integer> tempLISIndex = new ArrayList<>();
		tempLISIndex.add(null);
		tempLISIndex.add(0);
		// The parent index of each index of int[] sequence that forms the
		// overall LIS
		Integer[] parentIndex = new Integer[sequence.length];
		for (int i = 1; i < sequence.length; i++) {
			int currentElement = sequence[i];
			// Increasing #, add it on to the LIS list (this element makes a
			// longer LIS)
			if (currentElement > sequence[tempLISIndex.get(tempLISIndex.size() - 1)]) {
				tempLISIndex.add(i);
				parentIndex[i] = tempLISIndex.get(tempLISIndex.size() - 2);
			} else { // Lower/equal number, replace the smallest # in
						// sequence[tempLISIndex] that is still larger than it
				int j = 1;
				for (; sequence[tempLISIndex.get(j)] < currentElement; j++)
					;
				tempLISIndex.remove(j);
				tempLISIndex.add(j, i);
				parentIndex[i] = tempLISIndex.get(j - 1);
			}
		}

		// Build the overall LIS backwards with parentIndex and tempLISIndex
		int[] subsequence = new int[tempLISIndex.size() - 1];
		subsequence[subsequence.length - 1] = sequence[tempLISIndex.get(tempLISIndex.size() - 1)];
		int currentIndex = tempLISIndex.get(tempLISIndex.size() - 1);
		for (int i = subsequence.length - 2; i >= 0; i--) {
			subsequence[i] = sequence[parentIndex[currentIndex]];
			currentIndex = parentIndex[currentIndex];
		}
		return subsequence;
	}
}
