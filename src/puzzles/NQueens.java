package puzzles;

public class NQueens {
	private static boolean isValid(int[] q, int currentQueen) {
		if (q == null) {
			return false;
		}

		for (int i = 0; i < currentQueen; i++) {
			if (q[i] == q[currentQueen] || Math.abs(q[i] - q[currentQueen]) == Math.abs(i - currentQueen)) {
				return false;
			}
		}
		return true;
	}

	public static int[][] nQueens(int n) {
		return nQueens(new int[n], 0);
	}

	private static int[][] nQueens(int[] q, int currentQueen) {
		if (q.length == currentQueen) {
			return print(q);
		}
		for (int i = 0; i < q.length; i++) {
			q[currentQueen] = i;
			if (isValid(q, currentQueen)) {
				int[][] temp = nQueens(q, currentQueen + 1);
				if (temp != null) {
					return temp;
				}
			}
		}
		return null;
	}

	private static int[][] print(int[] q) {
		int[][] answer = new int[q.length][q.length];

		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[i].length; j++) {
				answer[i][j] = q[i] == j ? 1 : 0;
			}
		}
		return answer;
	}

}
