package puzzles;

import java.util.*;

public class SetBased {
	public static List<String> subsets(String set) {
		List<String> answer = new ArrayList<>();
		List<String> temp = new ArrayList<>();

		for (int i = 0; i < set.length(); i++) {
			String s = Character.toString(set.charAt(i));

			temp.clear();
			for (int j = 0; j < answer.size(); j++) {
				temp.add(s + answer.get(j));
			}
			answer.add(s);
			answer.addAll(temp);
		}
		return answer;
	}

	public static void main(String[] args) {
		List<String> a = subsets("abc");
		for (String answer : a) {
			System.out.print(answer + ", ");
		}
	}
}
