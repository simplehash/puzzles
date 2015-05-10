package puzzles;

public class LongestCommonSubsequence {
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
