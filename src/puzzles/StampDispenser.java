package puzzles;

/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */
import java.util.*;

public class StampDispenser {
	private class Stamp {
		int value;
		int count;

		public Stamp(int value, int count) {
			this.value = value;
			this.count = count;
		}
	}

	Queue<Stamp> stamps;

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
		stamps = new PriorityQueue<Stamp>(1, new StampComparator());

		// Build hashmap of all denominations and their associated count (how
		// many there are of that denomination)
		Map<Integer, Integer> denominationCount = new HashMap<>();
		for (int i : stampDenominations) {
			int count = 1;
			if (denominationCount.containsKey(i)) {
				count += denominationCount.get(i);
			}
			denominationCount.put(i, count);
		}

		// Put denominations into descending priority queue along with their
		// count
		Iterator<Integer> stampIterator = denominationCount.keySet().iterator();
		while (stampIterator.hasNext()) {
			int stampValue = stampIterator.next();
			int stampCount = denominationCount.get(stampValue);
			stamps.add(new Stamp(stampValue, stampCount));
		}
	}

	private static class StampComparator implements Comparator<Stamp> {
		@Override
		public int compare(Stamp s1, Stamp s2) {
			return s2.value - s1.value;
		}
	}

	/**
	 * Returns the minimum number of stamps that the machine can dispense to
	 * fill the given request.
	 *
	 * @param request
	 *            The total value of the stamps to be dispensed.
	 */
	public int calcMinNumStampsToFillRequest(int request) {
		return 0;
	}

	public static void main(String[] args) {
		int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
		StampDispenser stampDispenser = new StampDispenser(denominations);
		// assert stampDispenser.calcMinNumStampsToFillRequest(18) == 3;

	}
}