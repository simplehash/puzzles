package puzzles;

public class Permutations {
	private int count;

	public Permutations(String string) throws Exception {
		if (string == null) {
			throw new Exception("Null string provided");
		}
		count = 0;
		permutate("", string);
	}

	private void permutate(String word, String remainingLetters) {
		if (remainingLetters.isEmpty()) {
			System.out.println(word);
			count++;
			return;
		}
		for (int i = 0; i < remainingLetters.length(); i++) {
			permutate(word + remainingLetters.charAt(i),
					remainingLetters.substring(0, i) + remainingLetters.substring(i + 1));
		}
	}

	public int getCount() {
		return count;
	}
}
