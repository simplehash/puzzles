package puzzles;

import java.util.HashMap;

public class MatrixTraverse {
	private static String[][] matrix;
	private static int maxCol;
	private static int maxRow;

	public MatrixTraverse(String[][] matrix) {
		if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
			MatrixTraverse.matrix = matrix;
			maxCol = matrix[0].length;
			maxRow = matrix.length;
		}
	}

	public boolean traverse() {
		if (!matrix[0][0].equals("@")) {
			Cell cell = new Cell(matrix[0][0], 0, 0);
			Direction prevDirection = null;
			Direction currDirection = null;
			try {
				while (!cell.getValue().equals("@")) {
					String value = cell.getValue();
					System.out.println(value);
					if (value.equals(" ")) {
						cell.move(prevDirection);
					} else if (value.equals("^")) {
						currDirection = Direction.UP;
						cell.move(currDirection);
						prevDirection = currDirection;
					} else if (value.equals(">")) {
						currDirection = Direction.RIGHT;
						cell.move(currDirection);
						prevDirection = currDirection;
					} else if (value.equals("v")) {
						currDirection = Direction.DOWN;
						cell.move(currDirection);
						prevDirection = currDirection;
					} else if (value.equals("<")) {
						currDirection = Direction.LEFT;
						cell.move(currDirection);
						prevDirection = currDirection;
					}
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
				return false;
			}
		}
		System.out.println("Success!");
		return true;
	}

	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	static class Cell {
		private String value;
		private int row;
		private int col;
		private HashMap<String, Boolean> visited;

		public Cell(String value, int row, int col) {
			this.value = value;
			this.row = row;
			this.col = col;
			visited = new HashMap<>();
		}

		public void move(Direction direction) throws Exception {
			if (visited.putIfAbsent(row + ", " + col, true) != null) {
				throw new Exception("Infinite loop detected");
			}
			if (direction == null) {
				throw new Exception("Null direction");
			} else if (direction == Direction.UP) {
				if (--row < 0) {
					throw new Exception("Up is out of bounds");
				}
			} else if (direction == Direction.RIGHT) {
				if (++col >= maxCol) {
					throw new Exception("Right is out of bounds");
				}
			} else if (direction == Direction.DOWN) {
				if (++row >= maxRow) {
					throw new Exception("Down is out of bounds");
				}

			} else if (direction == Direction.LEFT) {
				if (--col < 0) {
					throw new Exception("Left is out of bounds");
				}
			}
			value = matrix[row][col];
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}
	}
}
