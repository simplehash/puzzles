package puzzles;

import java.util.*;

public class SetStuff {
	public static void main(String[] args) {
		Set<String> a = rInK("abcdef", 3);
		for (String c : a) {
			System.out.println(c);
		}
	}

	public static Set<String> powerset(String set) {
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
