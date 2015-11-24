package puzzles;

/**
 * Given a string with alpha-numeric characters and parentheses, return a string
 * with balanced parentheses by removing the fewest characters possible. You
 * cannot add anything to the string.
 * 
 * [edit] Examples if needed
 * 
 * balance("()") -> "()" balance("a(b)c)") -> "a(b)c" balance(")(") -> ""
 * balance("(((((") -> "" balance("(()()(") -> "()()" balance(")(())(") ->
 * "(())" There can be multiple correct results per input
 * 
 * balance("(())())") -> "(()())" or "(())()"
 */

public class BalancedParenthesis {
	public static String balance(String input) {
		int excessOpenCount = 0;
		int completeCount = 0;
		int excessCloseCount = 0;

		StringBuilder answer = new StringBuilder();

		char[] array = input.toCharArray();
		for (char c : array) {
			if (c == '(') {
				excessOpenCount++;
			} else if (c == ')') {
				if (excessOpenCount > 0) {
					completeCount++;
					excessOpenCount--;
				} else {
					excessCloseCount++;
				}
			}
		}

		for (char c : array) {
			if (c == '(' && completeCount > 0) {
				answer.append('(');
				completeCount--;
			} else if (c == ')') {
				if (excessCloseCount == 0) {
					answer.append(')');
				} else {
					excessCloseCount--;
				}
			}
		}

		return answer.toString();
	}
}
