package puzzles;

public class Regex {
	/**
	 * Asterisks (*) match 0 or more of the preceding character
	 * 
	 * Dots (.) match exactly 1 of any letter
	 * 
	 * Case sensitive
	 * 
	 * Note: a*b == b
	 */
	public static boolean regex(String pattern, String word) {
		// Both null/empty
		if ((pattern == null && word == null) || (pattern.isEmpty() && word.isEmpty())) {
			return true;
		}

		// Base case of a* ?= a
		if (pattern.length() >= 2 && (word == null || word.isEmpty()) && pattern.charAt(1) == '*') {
			return true;
		}

		// Only 1 is null/empty
		if ((pattern == null && word != null) || (pattern.isEmpty() && !word.isEmpty())
				|| (pattern != null && word == null) || (!pattern.isEmpty() && word.isEmpty())) {
			return false;
		}

		// * case
		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			return regex(pattern.substring(2), word)
					|| (isMatch(pattern.charAt(0), word.charAt(0)) && regex(pattern, word.substring(1)));
		}
		return isMatch(pattern.charAt(0), word.charAt(0)) && regex(pattern.substring(1), word.substring(1));

	}

	private static boolean isMatch(char pattern, char word) {
		return pattern == word || pattern == '.';
	}
}
