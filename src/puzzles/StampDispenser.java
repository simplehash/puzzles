package puzzles;

/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */
import java.util.*;

public class StampDispenser {
	private Queue<Integer> stamps;

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
		stamps = new PriorityQueue<Integer>(1, new StampComparator());

		for (int i : stampDenominations) {
			stamps.add(i);
		}
	}

	private static class StampComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer s1, Integer s2) {
			return s2 - s1;
		}
	}

	/**
	 * Returns the minimum number of stamps that the machine can dispense to
	 * fill the given request, or -1 if unable to dispense current value.
	 *
	 * @param request
	 *            The total value of the stamps to be dispensed.
	 * @throws Exception
	 */
	public int calcMinNumStampsToFillRequest(int request) throws Exception {
		if (request <= 0) {
			throw new Exception("Request should be >= 0");
		}

		int balance = request;
		int stampsNeeded = 0;

		// Stores the stamps removed to fulfil the balance. This queue is used
		// in case the balance cannot be fulfiled with the available stamps, in
		// which case the original stamps queue is refilled with the stamps in
		// this one
		Queue<Integer> usedStamps = new LinkedList<>();
		Queue<Integer> unUsedStamps = new LinkedList<>();

		while (balance > 0 && !stamps.isEmpty()) {
			int currentStamp = stamps.remove();
			if (balance >= currentStamp) {
				balance -= currentStamp;
				stampsNeeded++;
				usedStamps.add(currentStamp);
			} else {
				unUsedStamps.add(currentStamp);
			}

		}

		stamps.addAll(unUsedStamps);

		if (balance > 0) {
			stamps.addAll(usedStamps);
			return -1;
		}
		return stampsNeeded;
	}

	public static void main(String[] args) throws Exception {
		int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
		StampDispenser stampDispenser = new StampDispenser(denominations);
		// Assert was removed since this was not a unit test
		System.out.println("Should be 3 (value: 18): "
				+ stampDispenser.calcMinNumStampsToFillRequest(18));
	}
}