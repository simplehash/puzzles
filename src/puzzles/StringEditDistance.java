package puzzles;

import java.util.Arrays;
import java.util.Collections;

public class StringEditDistance {
	private int[][] distance;
	private int answer;

	public StringEditDistance(String s1, String s2) throws Exception {
		if (s1 == null || s2 == null) {
			throw new Exception("Null strings cannot be matched");
		}
		int s1Length = s1.length();
		int s2Length = s2.length();

		if (s1.isEmpty()) {
			answer = s2Length;
		} else if (s2.isEmpty()) {
			answer = s1Length;
		} else if (s1.equals(s2)) {
			answer = 0;
		} else {
			distance = new int[s1Length + 1][s2Length + 1];

			for (int s1Pos = 0; s1Pos < distance.length; s1Pos++) {
				for (int S2Pos = 0; S2Pos < distance[s1Pos].length; S2Pos++) {
					int entry = -1;
					if (s1Pos == 0 && S2Pos == 0) {
						entry = 0;
					} else if (s1Pos == 0) {
						entry = S2Pos;
					} else if (S2Pos == 0) {
						entry = s1Pos;
					} else {
						Integer[] options = new Integer[3];
						if (s1.charAt(s1Pos - 1) == s2.charAt(S2Pos - 1)) {
							// The if statement's comparison has index-1 because
							// s1Pos and s2Pos count in the cache matrix, which
							// is of dimensions length+1
							options[0] = distance[s1Pos - 1][S2Pos - 1];
						} else {
							options[0] = distance[s1Pos - 1][S2Pos - 1] + 1;
						}
						options[1] = distance[s1Pos - 1][S2Pos] + 1;
						options[2] = distance[s1Pos][S2Pos - 1] + 1;
						entry = Collections.min(Arrays.asList(options));
					}
					if (entry == -1) {
						throw new Exception("answer no assigned");
					}
					distance[s1Pos][S2Pos] = entry;
				}
			}
			answer = distance[s1Length][s2Length];
		}
	}

	public int getAnswer() {
		return answer;
	}

	public void getMatrix() {
		if (distance != null) {
			for (int[] row : distance) {
				for (int cell : row) {
					System.out.print(cell + " ");
				}
				System.out.println("");
			}
		}
	}
}