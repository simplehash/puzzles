package puzzles;

import java.util.*;

public class T9 {
	private static List<String> answer;
	private final static String[] dialPad = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public static List<String> go(String numbers) {
		try {
			Integer.parseInt(numbers);
		} catch (NumberFormatException e) {
			System.err.println("Invalid number entered");
			return null;
		}
		answer = new ArrayList<>();
		return go("", numbers);
	}

	private static List<String> go(String prefix, String suffix) {
		if (suffix.length() == 0) {
			answer.add(prefix);
		} else {
			String letters = dialPad[Integer.parseInt(suffix.substring(0, 1))];
			if (letters.isEmpty()) {
				go(prefix, suffix.substring(1));
			} else {
				for (char c : letters.toCharArray()) {
					go(prefix + c, suffix.substring(1));
				}
			}
		}
		return answer;
	}
}
