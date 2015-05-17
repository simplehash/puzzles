package puzzles;

import java.util.*;

public class WordSegmentation {
	public static boolean go(String s, List<String> dictionary) {
		int length = s.length();
		boolean[] table = new boolean[length + 1]; // Boolean arrays default to
													// false

		for (int i = 1; i <= length; i++) {
			if (!table[i] && dictionary.contains(s.substring(0, i))) {
				table[i] = true;
			}
			if (table[i]) {
				if (i == length) {
					return true;
				}

				for (int j = i + 1; j <= length; j++) {
					String subStr = s.substring(i, j);
					if (!table[j] && dictionary.contains(subStr)) {
						table[j] = true;
					}
					if (j == length && table[j]) {
						return true;
					}
				}
			}
			System.out.println(s.charAt(i - 1) + " " + table[i]);
		}

		return false;
	}
}
