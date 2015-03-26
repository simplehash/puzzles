package puzzles;

import java.util.*;

public class LongestIncreasingSubsequence {
	private int[] sequence;

	public LongestIncreasingSubsequence(int[] numbers) {
		sequence = numbers;
	}

	public int[] calculate() {
		List<Integer> tempLISIndex = new ArrayList<>();
		List<Integer> tempLISValue = new ArrayList<>();
		tempLISIndex.add(null);
		tempLISValue.add(null);
		tempLISIndex.add(0);
		tempLISValue.add(sequence[0]);
		Integer[] parentIndex = new Integer[sequence.length];
		for (int i = 1; i < sequence.length; i++) {
			int currentElement = sequence[i];
			if (tempLISValue.get(tempLISValue.size() - 1) == null
					|| currentElement > tempLISValue.get(tempLISValue.size() - 1)) {
				tempLISValue.add(currentElement);
				tempLISIndex.add(i);
				parentIndex[i] = tempLISIndex.get(tempLISIndex.size() - 2);
			} else {
				int j = 1;
				for (; tempLISValue.get(j) < currentElement; j++)
					;
				tempLISIndex.remove(j);
				tempLISIndex.add(j, i);
				tempLISValue.remove(j);
				tempLISValue.add(j, currentElement);
				parentIndex[i] = tempLISIndex.get(j - 1);
			}
		}
		for (Integer i : parentIndex) {
			System.out.println(i);

		}
		int[] subsequence = new int[tempLISIndex.size() - 1];
		subsequence[subsequence.length - 1] = tempLISValue.get(tempLISValue.size() - 1);
		int currentIndex = tempLISIndex.get(tempLISIndex.size() - 1);
		for (int i = subsequence.length - 2; i >= 0; i--) {
			subsequence[i] = sequence[parentIndex[currentIndex]];
			currentIndex = parentIndex[currentIndex];
		}
		print(subsequence);
		return subsequence;
	}

	private void print(int[] subsequence) {
		for (int i : subsequence) {
			System.out.print(i + " ");
		}
	}
}
