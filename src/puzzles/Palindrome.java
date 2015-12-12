package puzzles;

public class Palindrome {
	public static boolean is(String s) {
		if (s == null) {
			return false;
		}
		int begin = 0;
		int end = s.length() - 1;
		char beginLetter;
		char endLetter;

		while (begin <= end) {
			beginLetter = s.charAt(begin);
			if (!Character.isLetter(beginLetter) && !Character.isDigit(beginLetter)) {
				begin++;
				continue;
			}

			endLetter = s.charAt(end);
			if (!Character.isLetter(endLetter) && !Character.isDigit(endLetter)) {
				end--;
				continue;
			}

			if (Character.toLowerCase(beginLetter) != Character.toLowerCase(endLetter)) {
				return false;
			}
			begin++;
			end--;
		}
		return true;
	}
}
