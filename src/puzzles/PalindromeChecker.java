package puzzles;

public class PalindromeChecker {
    public static boolean check(String s) {
        if (s != null) {
            if (s.length() <= 1) {
                return true;
            }
            for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
