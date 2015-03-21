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
		if (request <= 0) { // Can't dispense
			return -1;
		}

		int minStamps = Integer.MAX_VALUE;

		outerLoop: for (int i = 0; i < stamps.length; i++) {
			if (stamps[i] == null) { // Can't dispense
				return -1;
			}

			int startingStamp = stamps[i];
			if (startingStamp <= request) { // Skip denominations > request
				int stampCount = 0; // Trial with a new starting denomination,
									// so reset counter
				int balance = request;
				for (int j = i; j < stamps.length; j++) {
					/*
					 * Why is this if statement here?
					 * 
					 * If the stamps collection has a denomination = request,
					 * min should be 1. Else, min should be 2. This condition
					 * saves us from trying further combinations of stamps
					 * because 2 is the best case if 1 is not achieved.
					 */
					if (minStamps <= 2) {
						break outerLoop;
					}
					int currentStamp = stamps[j];
					if (currentStamp <= balance) { // Skip denominations >
													// remaining balance,
													// redundant check
						int stampsNeeded = balance / currentStamp;
						balance -= stampsNeeded * currentStamp;
						stampCount += stampsNeeded;
					}
				}
				if (stampCount < minStamps) { // If starting at current
												// denomination yielded a
												// smaller count, store it
					minStamps = stampCount;
				}
			}
		}

		return minStamps;
	}

	public static void main(String[] args) throws Exception {
		int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
		StampDispenser stampDispenser = new StampDispenser(denominations);
		// Assert was removed since this was not a unit test
		System.out.println("Should be 3 (value: 18): "
				+ stampDispenser.calcMinNumStampsToFillRequest(18));

		// Tests greedy and sorting
		stampDispenser = new StampDispenser(new int[] { 4, 1, 3 });
		// Greedy would've failed here
		System.out.println("Should be 2 (value: 6): "
				+ stampDispenser.calcMinNumStampsToFillRequest(6));
	}
}