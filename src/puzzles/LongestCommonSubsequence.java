package puzzles;

public class LongestCommonSubsequence {
	public static void diff(String s1, String s2) {
		if (s1 != null && s2 != null && !s1.equals(s2)) {
			String s = null;
			StringBuilder ops = new StringBuilder();
			if (s1.isEmpty()) {
				for (int i = 0; i < s2.length(); i++) {
					ops.append("+");
				}
				s = s2;
			} else if (s2.isEmpty()) {
				for (int i = 0; i < s1.length(); i++) {
					ops.append("+");
				}
				s = s1;
			} else {
				final String lcs = find(s1, s2);
				StringBuilder changes = new StringBuilder();
				int pos1 = 0;
				int pos2 = 0;
				int posLCS = 0;
				while (true) {
					if (pos1 == s1.length()) {
						changes.append(s2.substring(pos2));
						for (int j = 0; j < (s2.length() - pos2); j++) {
							ops.append("+");
						}
						break;
					} else if (pos2 == s2.length()) {
						changes.append(s1.substring(pos1));
						for (int j = 0; j < (s1.length() - pos1); j++) {
							ops.append("-");
						}
						break;
					} else if (lcs.charAt(posLCS) == s1.charAt(pos1) && lcs.charAt(posLCS) == s2.charAt(pos2)) {
						pos1++;
						pos2++;
						posLCS++;
					} else if (s1.charAt(pos1) != lcs.charAt(posLCS)) {
						changes.append(s1.charAt(pos1));
						ops.append("-");
						pos1++;
					} else if (lcs.charAt(posLCS) != s2.charAt(pos2)) {
						changes.append(s2.charAt(pos2));
						ops.append("+");
						pos2++;
					} else {
						System.err.println("wtf happened?");
					}
				}
				s = changes.toString();
			}
			System.out.println(s);
			System.out.println(ops);
		}
	}

	public static String find(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return null;
		}
		if (s1.isEmpty() || s2.isEmpty()) {
			return "";
		}

		int m = s1.length();
		int n = s2.length();
		int[][] table = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					table[i][j] = table[i - 1][j - 1] + 1;
				} else if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
					table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
				}
			}
		}

		// Build the string from back to front, then reverse
		StringBuilder sb = new StringBuilder();
		int currRow = m;
		int currCol = n;
		while (currRow > 0 && currCol > 0) {
			if (s1.charAt(currRow - 1) == s2.charAt(currCol - 1)) {
				sb.append(s1.charAt(currRow - 1));
				currRow--;
				currCol--;
			} else if (table[currRow - 1][currCol] > table[currRow][currCol - 1]) {
				currRow--;
			} else {
				currCol--;
			}
		}
		return sb.reverse().toString();
	}
}
