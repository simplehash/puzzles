package puzzles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetStuff {
    private final static String[] dialPad = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private static Set<String> answer;

    public static Set<String> t9(String numbers) {
        try {
            Integer.parseInt(numbers);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number entered");
            return null;
        }
        answer = new HashSet<>();
        return t9("", numbers);
    }

    private static Set<String> t9(String prefix, String numbers) {
        // prefix is the combo is various letters translated from T9 dialpad,
        // suffix is the numbers remaining to be translated
        if (numbers.isEmpty()) {
            answer.add(prefix);
        } else {
            String letters = dialPad[Integer.parseInt(numbers.substring(0, 1))];
            if (letters.isEmpty()) {
                t9(prefix, numbers.substring(1));
            } else {
                for (char c : letters.toCharArray()) {
                    t9(prefix + c, numbers.substring(1));
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Set<String> a = rInK("abcdef", 3);
        for (String c : a) {
            System.out.println(c);
        }
    }

    public static Set<String> powerset(String set) {
        if (set == null) {
            return null;
        }
        /*
         * Powerset is the set of all subsets, therefore this method solves the
		 * subsets problem too
		 */
        List<String> answer = new ArrayList<>();
        for (char c : set.toCharArray()) {
            int prevSize = answer.size();
            for (int i = 0; i < prevSize; i++) {
                answer.add(answer.get(i) + Character.toString(c));
            }
            answer.add(Character.toString(c));
        }
        return new HashSet<>(answer);
    }

    public static Set<String> rInK(String set, int length) {
        if (set == null || length < 1) {
            return null;
        }

        Set<String> answer = new HashSet<>();
        Set<String> tempAnswer = powerset(set);

        for (String s : tempAnswer) {
            if (s.length() == length) {
                answer.add(s);
            }
        }
        return answer;
    }
}
