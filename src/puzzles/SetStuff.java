package puzzles;

import java.util.*;

public class SetStuff {
	public static void main(String[] args) {
		List<String> a = powerset("abc");
		for (String c : a) {
			System.out.println(c);
		}
	}

	public static List<String> powerset(String set) {
		List<String> answer = new ArrayList<>();
		for (char c : set.toCharArray()) {
			int prevSize = answer.size();
			for (int i = 0; i < prevSize; i++) {
				answer.add(answer.get(i) + Character.toString(c));
			}
			answer.add(Character.toString(c));
		}
		return answer;
	}

	public static List<String> subsets(String set) {
		List<String> answer = new ArrayList<>();
		List<String> temp = new ArrayList<>();

		/*
		 * For each character in the set, append it to all current results in
		 * the set then include the character itself in the set
		 */
		for (int i = 0; i < set.length(); i++) {
			String s = Character.toString(set.charAt(i));

			temp.clear();
			for (int j = 0; j < answer.size(); j++) {
				temp.add(answer.get(j) + s);
			}
			/*
			 * Be sure to add the character itself after appending it to all
			 * current results else you will get the character twice
			 */
			answer.add(s);
			answer.addAll(temp);
		}
		return answer;
	}

	public static List<String> rInK(String set, int length) {
		List<String> answer = new ArrayList<>();
		List<String> tempAnswer = subsets(set);

		for (String s : tempAnswer) {
			if (s.length() == length) {
				answer.add(s);
			}
		}

		return answer;
	}
}
