package puzzles;

public class ReverseSentence {
	private String answer = null;

	public ReverseSentence(String sentence) throws Exception {
		if (sentence == null) {
			throw new Exception("Null sentence");
		}
		String[] words = sentence.split(" ");
		answer = "";
		for (int i = words.length - 1; i >= 0; i--) {
			answer += words[i] + " ";
		}
		answer = answer.trim();
	}

	public String getAnswer() {
		return answer;
	}
}
