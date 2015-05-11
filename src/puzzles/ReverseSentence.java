package puzzles;

public class ReverseSentence {
	private StringBuilder answer = null;

	public ReverseSentence(String sentence) throws Exception {
		if (sentence == null) {
			throw new Exception("Null sentence");
		}
		String[] words = sentence.split(" ");
		answer = new StringBuilder();
		for (int i = words.length - 1; i >= 0; i--) {
			answer.append(words[i]).append(" ");
		}
	}

	public String getAnswer() {
		return answer.toString().trim();
	}
}
