package puzzles;

public class DP {
	public static int stringEditDistance(String s1, String s2) {
		if (s1 == null || s1.isEmpty()) {
			return s2.length();
		}
		if (s2 == null || s2.isEmpty()) {
			return s1.length();
		}
		if (s1.equals(s2)) {
			return 0;
		}

		int[][] table = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 && j == 0) {
					table[i][j] = 0;
				} else if (i == 0) {
					table[i][j] = j;
				} else if (j == 0) {
					table[i][j] = i;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					table[i][j] = table[i - 1][j - 1];
				} else {
					int substitute = table[i - 1][j - 1] + 1;
					int delete = table[i][j - 1] + 1;
					int add = table[i - 1][j] + 1;
					table[i][j] = Math.min(substitute, Math.min(delete, add));
				}
			}
		}

		return table[s1.length()][s2.length()];
	}

	public static String diff(String s1, String s2) {
		if (s1 == null || s1.isEmpty()) {
			return s2;
		}
		if (s2 == null || s2.isEmpty()) {
			return s1;
		}
		if (s1.equals(s2)) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		StringBuilder ops = new StringBuilder();

		int pos1 = 0;
		int pos2 = 0;
		int posL = 0;
		String lcs = longestCommonSubsequence(s1, s2);

		while (true) {
			if (pos1 == s1.length()) {
				sb.append(s2.substring(pos2));
				for (int i = 0; i < (s2.length() - pos2); i++) {
					ops.append("-");
				}
				break;
			} else if (pos2 == s2.length()) {
				sb.append(s1.substring(pos1));
				for (int i = 0; i < (s1.length() - pos1); i++) {
					ops.append("+");
				}
				break;
			} else if (s1.charAt(pos1) == s2.charAt(pos2)) {
				pos1++;
				pos2++;
				posL++;
			} else {
				if (lcs.isEmpty() || s1.charAt(pos1) != lcs.charAt(posL)) {
					sb.append(s1.charAt(pos1));
					ops.append("-");
					pos1++;
				}
				if (lcs.isEmpty() || s2.charAt(pos2) != lcs.charAt(posL)) {
					sb.append(s2.charAt(pos2));
					ops.append("+");
					pos2++;
				}
			}
		}

		return sb.toString() + "\n" + ops.toString();
	}

	public static String longestCommonSubsequence(String s1, String s2) {
		if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
			return "";
		}
		if (s1.equals(s2)) {
			return s1;
		}

		int[][] table = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					table[i][j] = table[i - 1][j - 1] + 1;
				} else if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
					table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int i = s1.length();
		int j = s2.length();

		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				sb.append(s1.charAt(i - 1));
				i--;
				j--;
			} else if (table[i - 1][j] > table[i][j - 1]) {
				i--;
			} else if (table[i - 1][j] <= table[i][j - 1]) {
				j--;
			}
		}
		return sb.reverse().toString();
	}

	public static int changeMaker(int[] denominations, int value) {
		if (denominations == null || denominations.length < 1 || value < 0) {
			return -1;
		}

		int[] values = new int[value + 1];
		for (int i = 0; i < values.length; i++) {
			values[i] = Integer.MAX_VALUE;
		}
		values[0] = 0;

		for (int currentValue = 0; currentValue < values.length; currentValue++) {
			for (int denomination : denominations) {
				if (denomination <= currentValue) {
					values[currentValue] = Math.min(values[currentValue], values[currentValue - denomination] + 1);
				}
			}
		}
		return values[value];
	}

	public static int knapsack01(int[] values, int[] weights, int capacity) {
		if (values == null || weights == null || capacity < 0) {
			return -1;
		}
		
		int items = values.length;
		int[][] table = new int[items + 1][capacity + 1];

		for (int currentItem = 0; currentItem <= items; currentItem++) {
			for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
				if (currentCapacity == 0 || currentItem == 0) {
					table[currentItem][currentCapacity] = 0;
				} else if (weights[currentItem - 1] > currentCapacity) {
					table[currentItem][currentCapacity] = table[currentItem - 1][currentCapacity];
				} else if (weights[currentItem - 1] <= currentCapacity) {
					table[currentItem][currentCapacity] = Math.max(table[currentItem - 1][currentCapacity], table[currentItem - 1][currentCapacity
							- weights[currentItem - 1]]
							+ values[currentItem - 1]);
				}
			}
		}

		return table[items][capacity];
	}
}