package puzzles;

public class ChangeMaker {
	public static int go(int[] denominations, int value) {
		int[] values = new int[value + 1];
		for (int i = 0; i < values.length; i++) {
			values[i] = Integer.MAX_VALUE;
		}
		values[0] = 0;

		for (int currentValue = 0; currentValue < values.length; currentValue++) {
			for (int denomination : denominations) {
				if (denomination <= currentValue) {
					values[currentValue] = Math.min(values[currentValue], values[currentValue - denomination] + 1);
				}
			}
		}
		return values[value];
	}
}