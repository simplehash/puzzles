package puzzles;

import java.util.*;

public class SudokuChecker {
	private int result;

	public int getResult() {
		return result;
	}

	public SudokuChecker(String input) {
		int[][] grid = new int[9][9]; // Grid sizes are hardcoded since sudokus
										// are fixed size
		for (int i = 0; i < 81; i++) {
			int row = i / 9;
			int col = i % 9;
			grid[row][col] = Character.getNumericValue(input.charAt(i));
		}

		Set<Integer> usedNumbers = new HashSet<>();

		// Check rows
		for (int row = 0; row < grid.length; row++) {
			usedNumbers.clear();
			for (int col = 0; col < grid[row].length; col++) {
				if (usedNumbers.add(grid[row][col]) == false) {
					System.out.println("0");
					result = 0;
					return;
				}
			}
		}

		// Check columns
		for (int col = 0; col < grid[0].length; col++) {
			usedNumbers.clear();
			for (int row = 0; row < grid.length; row++) {
				if (usedNumbers.add(grid[row][col]) == false) {
					System.out.println("0");
					result = 0;
					return;
				}
			}
		}

		// Check 3x3 subgrids, of which there are 3x3 of them
		for (int gridRow = 0; gridRow < 3; gridRow++) {
			for (int gridCol = 0; gridCol < 3; gridCol++) {
				usedNumbers.clear();
				// Start and end are inclusive
				int startRow = gridRow * 3;
				int startCol = gridCol * 3;
				int endRow = startRow + 2;
				int endCol = startCol + 2;
				for (int row = startRow; row <= endRow; row++) {
					for (int col = startCol; col <= endCol; col++) {
						if (usedNumbers.add(grid[row][col]) == false) {
							System.out.println("0");
							result = 0;
							return;
						}
					}
				}
			}
		}

		System.out.println("1");
		result = 1;
	}
}
