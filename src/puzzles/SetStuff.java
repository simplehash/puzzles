package puzzles;

import java.util.*;

public class SetStuff {
	private static Set<String> answer;
	private final static String[] dialPad = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv",
			"wxyz" };

	public static Set<String> t9(String numbers) {
		try {
			Integer.parseInt(numbers);
		} catch (NumberFormatException e) {
			System.err.println("Invalid number entered");
			return null;
		}
		answer = new HashSet<>();
		return t9("", numbers);
	}

	private static Set<String> t9(String prefix, String suffix) {
		// prefix is the combo is various letters translated from T9 dialpad,
		// suffix is the numbers remaining to be translated
		if (suffix.isEmpty()) {
			answer.add(prefix);
		} else {
			String letters = dialPad[Integer.parseInt(suffix.substring(0, 1))];
			if (letters.isEmpty()) {
				t9(prefix, suffix.substring(1));
			} else {
				for (char c : letters.toCharArray()) {
					t9(prefix + c, suffix.substring(1));
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		Set<String> a = rInK("abcdef", 3);
		for (String c : a) {
			System.out.println(c);
		}
	}

	public static Set<String> powerset(String set) {
		/*
		 * Powerset is the set of all subsets, therefore this method solves the
		 * subsets problem too
		 */
		List<String> answer = new ArrayList<>();
		for (char c : set.toCharArray()) {
			int prevSize = answer.size();
			for (int i = 0; i < prevSize; i++) {
				answer.add(answer.get(i) + Character.toString(c));
			}
			answer.add(Character.toString(c));
		}
		return new HashSet<>(answer);
	}

	public static Set<String> rInK(String set, int length) {
		Set<String> answer = new HashSet<>();
		Set<String> tempAnswer = powerset(set);

		for (String s : tempAnswer) {
			if (s.length() == length) {
				answer.add(s);
			}
		}

		return answer;
	}
}
