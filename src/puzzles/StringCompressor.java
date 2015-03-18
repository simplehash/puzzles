package puzzles;

public class StringCompressor {
	private String answer;

	public StringCompressor(String string2) throws Exception {
		// Currently assumes each character appears < 10 times otherwise gotta
		// change up the system design
		if (string2 == null) {
			throw new Exception("Null string");
		}
		if (string2.length() <= 2) {
			answer = string2;
			return;
		}
		char prevC = string2.charAt(0);
		int count = 0;
		answer = "";
		for (char c : string2.toCharArray()) {
			if (c != prevC) {
				answer += "" + prevC;
				if (count > 1) {
					answer += count;
				}
				prevC = c;
				count = 1;
			} else {
				count++;
			}
		}
		// To catch the last letters
		answer += "" + prevC;
		if (count > 1) {
			answer += count;
		}
	}

	public String getAnswer() {
		System.out.println(answer);
		return answer;
	}
}
