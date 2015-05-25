package puzzles;

/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */
import java.util.*;

public class ChangeMaker {
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
	public ChangeMaker(int[] stampDenominations) {
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

		int[] stampsRequired = new int[request + 1];
		stampsRequired[0] = 0;
		for (int i = 1; i < stampsRequired.length; i++) {
			stampsRequired[i] = Integer.MAX_VALUE;
		}
		for (int currentRequest = 1; currentRequest < stampsRequired.length; currentRequest++) {
			for (int currentStamp : stamps) {
				if (currentStamp <= currentRequest) {
					stampsRequired[currentRequest] = Math.min(stampsRequired[currentRequest - currentStamp] + 1, stampsRequired[currentRequest]);
				}
			}
		}
		return stampsRequired[request];
	}

	public static void main(String[] args) throws Exception {
		int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
		ChangeMaker stampDispenser = new ChangeMaker(denominations);
		System.out.println("Should be 3 (value: 18): " + stampDispenser.calcMinNumStampsToFillRequest(18));
	}
}