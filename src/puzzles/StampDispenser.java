package puzzles;

/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */
import java.util.*;

public class StampDispenser {
	private Integer[] stamps;

	/**
	 * Constructs a new StampDispenser that will be able to dispense the given
	 * types of stamps.
	 *
	 * @param stampDenominations
	 *            The values of the types of stamps that the machine should
	 *            have. Should be sorted in descending order and contain at
	 *            least a 1.
	 */
	public StampDispenser(int[] stampDenominations) {
		// Ensures denominations are in descending order by sorting it
		int length = stampDenominations.length;
		stamps = new Integer[length];
		for (int i = 0; i < length; i++) {
			stamps[i] = stampDenominations[i];
		}
		Arrays.sort(stamps, Collections.reverseOrder());
	}

	/**
	 * Returns the minimum number of stamps that the machine can dispense to
	 * fill the given request, or -1 if unable to dispense current value.
	 *
	 * @param request
	 *            The total value of the stamps to be dispensed.
	 * @throws Exception
	 */
	public int calcMinNumStampsToFillRequest(int request) {
		if (request < 0) { // Can't dispense
			return -1;
		}

		int m = stamps.length + 1;
		int n = request + 1;
		int actualAmount;
		int[][] requiredStamps = new int[m][n];
		int infinity = Integer.MAX_VALUE - 1;

		for (int j = 1; j < n; j++) {
			requiredStamps[0][j] = infinity;
		}

		for (int stampPos = 1; stampPos < m; stampPos++) {
			for (int currentChange = 1; currentChange < n; currentChange++) {
				if (currentChange < stamps[stampPos - 1]) {
					actualAmount = infinity;
				} else {
					actualAmount = requiredStamps[stampPos][currentChange
							- stamps[stampPos - 1]];
				}
				requiredStamps[stampPos][currentChange] = Math.min(
						requiredStamps[stampPos - 1][currentChange],
						1 + actualAmount);
			}
		}

		return requiredStamps[m - 1][n - 1];
	}

	public static void main(String[] args) throws Exception {
		int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
		StampDispenser stampDispenser = new StampDispenser(denominations);
		System.out.println("Should be 3 (value: 18): "
				+ stampDispenser.calcMinNumStampsToFillRequest(18));
	}
}