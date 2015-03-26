package puzzles;

public class FibonacciDP {
	public int bottomUp(int n) {
		int prev2 = 0;
		int prev1 = 1;
		int current = 0;
		if (n <= 1) {
			return n;
		}
		for (int i = 2; i <= n; i++) {
			current = prev2 + prev1;
			prev2 = prev1;
			prev1 = current;
		}
		return current;
	}
}
