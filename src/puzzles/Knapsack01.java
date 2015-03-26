package puzzles;

public class Knapsack01 {
	private int[] weights;
	private int[] values;
	private int capacity;
	private int[][] table;

	public Knapsack01(int[] weights, int[] values, int capacity) {
		if (weights.length == values.length) {
			this.weights = weights;
			this.values = values;
			this.capacity = capacity;
			table = new int[weights.length + 1][capacity + 1];
		}
	}

	public int run() {
		// 3 cases for knapsack problem
		// K(n, W) = 0 if 0 items or 0 capacity
		// K(n, W) = K(n - 1, W) if currentWeight > currentCapacity
		// K(n, W) = max(K(n - 1, W), K(n - 1, W - w) + v) if currentWeight <
		// currentCapacity, max of without item and with item
		for (int currentItemIndex = 0; currentItemIndex <= weights.length; currentItemIndex++) {
			for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
				if (currentCapacity == 0 || currentItemIndex == 0) {
					table[currentItemIndex][currentCapacity] = 0;
				} else {
					int withoutItem = table[currentItemIndex - 1][currentCapacity];
					if (weights[currentItemIndex - 1] > currentCapacity) {
						table[currentItemIndex][currentCapacity] = withoutItem;
					} else {
						int withItem = table[currentItemIndex - 1][currentCapacity
								- weights[currentItemIndex - 1]]
								+ values[currentItemIndex - 1];
						table[currentItemIndex][currentCapacity] = Math.max(
								withoutItem, withItem);
					}
				}
			}
		}
		getTable();
		return table[weights.length][capacity];
	}

	private void getTable() {
		for (int[] row : table) {
			for (int col : row) {
				System.out.print(col + "\t");
			}
			System.out.println(" ");
		}
	}
}
