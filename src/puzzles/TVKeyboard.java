package puzzles;

import java.util.LinkedList;
import java.util.Queue;

public class TVKeyboard {
	private enum Key {
		UP, DOWN, LEFT, RIGHT, ENTER
	}

	private final int width;
	private final int height;
	private Queue<Key> sequence;

	public TVKeyboard(String string, int width) throws Exception {
		if (string == null || width < 1) {
			throw new Exception("Null string or keyboard width < 1 provided");
		}

		this.width = width;
		height = 26 / width + 1;
		if (string.length() > 0) {
			sequence = new LinkedList<>();
			int prevRow = 0;
			int prevCol = 0;
			int a = (int) 'a'; // Used to diff
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				int cInt = (int) c;
				int currentRow = (cInt - a) / this.width;
				int currentCol = (cInt - a) % this.width;
				int colDiff = currentCol - prevCol;
				int rowDiff = currentRow - prevRow;

				if (prevRow + rowDiff >= height) {
					// If moving vertically off keyboard, do horizontal first
				} else {
					// Do vertical movement first
				}
			}
		}
	}

	private void moveVertically() {

	}

	private void moveHorizontally() {

	}

	public Queue<Key> getSequence() {
		return sequence;
	}
}
