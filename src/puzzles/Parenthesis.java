package puzzles;

public class Parenthesis {
    private static final char leftParenthesis = '(';
    private static final char rightParenthesis = ')';

    public static void go(int pairs) {
        go(pairs, pairs, "");
    }

    private static void go(int left, int right, String result) {
        if (!(left < 0 || right < left)) {
            if (left == 0 && right == 0) {
                System.out.println(result);
            } else {
                if (left > 0) {
                    go(left - 1, right, result + leftParenthesis);
                }
                if (right > left) {
                    go(left, right - 1, result + rightParenthesis);
                }
            }
        }
    }
}
