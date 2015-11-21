package puzzles;

public class OneLetterOff {
	public static boolean check(String s1, String s2) {
		if (s1 != null && s2 != null) {
			if (s1.equals(s2)) {
				return false;
			} else if (s1.length() == s2.length()) {
				boolean foundDifferent = false;
				for (int i = 0; i < s1.length(); i++) {
					if (s1.charAt(i) != s2.charAt(i)) {
						// Not the first difference
						if (foundDifferent) {
							return false;
						}
						foundDifferent = true;
					}
				}
				return true;
			} else if (s1.length() > s2.length()) {
				return compare(s1, s2);
			} else if (s1.length() < s2.length()) {
				return compare(s2, s1);
			}
		}
		return false;
	}

	private static boolean compare(String s1, String s2) {
		// s1 is 1 character longer than s2
		if (s1 == null || s2 == null || Math.abs(s1.length() - s2.length()) > 1) {
			return false;
		}

		boolean foundDifferent = false;
		for (int i = 0, j = 0; i < s1.length() && j < s2.length(); i++, j++) {
			if (s1.charAt(i) != s2.charAt(j)) {
				if (foundDifferent) {
					return false;
				}
				i++;
			}
		}
		return true;
	}
}
