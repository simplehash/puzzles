package puzzles;

import java.util.*;

public class Practice {
	public static void main(String[] args) {
		System.out.println("a".compareTo("b"));
	}

	private static int letterCombos(int[] numbers) {
		int possibilities = 0;
		for (int i = 0; i < numbers.length; i++) {
			int currentNumber = numbers[i];
			if (currentNumber >= 1 && currentNumber <= 9) {
				possibilities++;
			} else if (i > 0) {
				int previousNumber = numbers[i - 1];
				if (currentNumber >= 1 && currentNumber <= 6 && previousNumber <= 2 && previousNumber >= 1) {
					possibilities++;
				}
			}
		}
		return possibilities;
	}
}