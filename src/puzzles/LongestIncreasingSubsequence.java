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
		List<Integer> tempLISIndex = new ArrayList<>();
		tempLISIndex.add(null);
		tempLISIndex.add(0);
		Integer[] parentIndex = new Integer[sequence.length];
		for (int i = 1; i < sequence.length; i++) {
			int currentElement = sequence[i];
			if (currentElement > sequence[tempLISIndex.get(tempLISIndex.size() - 1)]) {
				tempLISIndex.add(i);
				parentIndex[i] = tempLISIndex.get(tempLISIndex.size() - 2);
			} else {
				int j = 1;
				for (; sequence[tempLISIndex.get(j)] < currentElement; j++)
					;
				tempLISIndex.remove(j);
				tempLISIndex.add(j, i);
				parentIndex[i] = tempLISIndex.get(j - 1);
			}
		}

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
