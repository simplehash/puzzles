package puzzles;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FirstUniqueWord {
	private String[] words;
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public FirstUniqueWord(String paragraph) throws Exception {
		if (paragraph == null) {
			throw new Exception("Null paragraph provided");
		} else if (paragraph.length() == 0) {
			answer = null;
			return;
		}
		// Assumes no punctuation
		words = paragraph.toLowerCase().split(" ");

		HashMap<String, Integer> wordBank = new HashMap<>();
		Queue<String> wordQ = new LinkedList<>();

		for (String word : words) {
			if (!wordBank.containsKey(word)) {
				// New unique word
				wordBank.put(word, 1);
				wordQ.add(word);
			} else {
				// Seen this word before
				int formerCount = wordBank.get(word);
				wordBank.replace(word, formerCount + 1);
			}
		}

		while (!wordQ.isEmpty()) {
			String word = wordQ.peek();
			if (wordBank.containsKey(word)) {
				if (wordBank.get(word) == 1) {
					answer = word;
					return;
				} else {
					wordBank.remove(word);
					wordQ.remove();
				}
			}
		}
	}
}
