package puzzles;

import java.util.LinkedList;
import java.util.Queue;

public class TVKeyboard {
    private final int width;
    private Queue<Key> sequence;
    private int currentRow;
    private int currentCol;
    private int prevRow;
    private int prevCol;

    public TVKeyboard(String string, int width) throws Exception {
        if (string == null || width < 1) {
            throw new Exception("Null string or keyboard width < 1 provided");
        }

        this.width = width;
        if (string.length() > 0) {
            sequence = new LinkedList<>();
            prevRow = 0;
            prevCol = 0;
            int a = (int) 'a'; // Used to diff
            char prevChar = 'a';

            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (c != ' ') {
                    int cInt = (int) c;
                    currentRow = (cInt - a) / this.width;
                    currentCol = (cInt - a) % this.width;

                    if (!(cInt + width < 26)) {
                        // If moving vertically off keyboard, do horizontal
                        // first
                        moveHorizontally(prevChar, c);
                        moveVertically(prevChar, c);
                    } else {
                        // Do vertical movement first
                        moveVertically(prevChar, c);
                        moveHorizontally(prevChar, c);
                    }

                    sequence.add(Key.ENTER);
                    prevChar = c;
                    prevRow = currentRow;
                    prevCol = currentCol;
                }
            }
        }
    }

    private void moveVertically(char prevC, char currC) {
        int rowDiff = currentRow - prevRow;
        Key key = Key.DOWN;
        if (rowDiff < 0) {
            key = Key.UP;
        }
        for (int i = 0; i < Math.abs(rowDiff); i++) {
            sequence.add(key);
        }
    }

    private void moveHorizontally(char prevC, char currC) {
        int colDiff = currentCol - prevCol;
        Key key = Key.RIGHT;
        if (colDiff < 0) {
            key = Key.LEFT;
        }
        for (int i = 0; i < Math.abs(colDiff); i++) {
            sequence.add(key);
        }
    }

    public void getSequence() {
        while (!sequence.isEmpty()) {
            System.out.print(sequence.remove().toString() + ", ");
        }
        System.out.println("");
    }

    private enum Key {
        UP, DOWN, LEFT, RIGHT, ENTER
    }
}
