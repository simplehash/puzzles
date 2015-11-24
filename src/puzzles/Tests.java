package puzzles;

import java.util.*;

public class Tests {
	/**
	 * Assume all null checks are done
	 */
	public static void main(String[] args) {
		
	}

	private final static String[] dialPad = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv",
			"wxyz" };
	private static List<String> t9Output;

	private static List<String> t9(Integer[] numbers) {
		t9Output = new ArrayList<>();
		t9("", new ArrayList<Integer>(Arrays.asList(numbers)));
		return t9Output;
	}

	private static void t9(String word, List<Integer> numbers) {
		if (numbers.isEmpty()) {
			t9Output.add(word);
		} else {
			List<Integer> newNumbers = new ArrayList<>(numbers);
			String letters = dialPad[newNumbers.remove(0)];
			if (letters.isEmpty()) {
				t9(word, newNumbers);
			} else {
				for (char c : letters.toCharArray()) {
					t9(word + c, newNumbers);
				}
			}
		}
	}

	private static List<String> permutations;

	private static List<String> permutations(String word) {
		permutations = new ArrayList<>();
		permutations("", word);
		return permutations;
	}

	private static void permutations(String prefix, String word) {
		if (word.isEmpty()) {
			permutations.add(prefix);
			return;
		}

		for (int i = 0; i < word.length(); i++) {
			permutations(prefix + word.charAt(i), word.substring(0, i) + word.substring(i + 1));
		}
	}

	private static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int currentMaxProfit = 0;
		int currentSellPrice = prices[0];
		for (int price : prices) {
			currentSellPrice = Math.min(currentSellPrice, price);
			currentMaxProfit = Math.max(currentMaxProfit, price - currentSellPrice);
		}
		return currentMaxProfit;
	}

	private static boolean hops(int[] values) {
		if (values == null || values.length == 0) {
			return false;
		}

		int currentFarthest = 0;
		for (int i = 0; i < values.length && i <= currentFarthest; i++) {
			if (currentFarthest >= values.length - 1) {
				return true;
			}
			currentFarthest = Math.max(currentFarthest, i + values[i]);
		}
		return false;
	}
}