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

	public void stampsLeft() {
		System.out.print("Remaining stamps are (value, count): ");

		Stamp[] stampArray = new Stamp[stamps.size()];
		stamps.toArray(stampArray);
		for (Stamp o : stampArray) {
			System.out.print("(" + o.value + ", " + o.count + "), ");
		}
		System.out.println("");
	}

	private Queue<Stamp> stamps;

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
		// many there are of that denomination). A hashmap allows updating of an
		// individual value in O(1) instead of O(n) (queue)
		Map<Integer, Integer> denominationCount = new HashMap<>();
		for (int i : stampDenominations) {
			int count = 1;
			if (denominationCount.containsKey(i)) {
				count += denominationCount.get(i);
			}
			denominationCount.put(i, count);
		}

		// Now that we've got all stamp demoninations and their associated
		// counts, put the stamps into a descending priority queue
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

		// Since there is no way of iterating through a priority queue without
		// removing prior elements (iterator does not guarantee order, see
		// http://docs.oracle.com/javase/1.5.0/docs/api/java/util/PriorityQueue.html#iterator()),
		// we construct a queue of unused stamps to be re-added to the
		// stamps queue
		Queue<Stamp> leftOverStamps = new LinkedList<>();

		// Stores the stamps removed to fulfil the balance. This queue is used
		// in case the balance cannot be fulfiled with the available stamps, in
		// which case the original stamps queue is refilled with the stamps in
		// this one
		Queue<Stamp> removedStamps = new LinkedList<>();

		while (balance > 0 && !stamps.isEmpty()) {
			Stamp currentStamp = stamps.remove();
			int stampValue = currentStamp.value;
			int stampCount = currentStamp.count;
			// Calculate if the balance > current stamp value, otherwise look at
			// next biggest stamp
			if (balance >= stampValue) {
				int stampsToRemove = balance / stampValue;

				if (stampsToRemove >= stampCount) {// Need at least all of the
													// greatest denomination
													// stamps
					balance -= stampCount * stampValue;
					stampsNeeded += stampCount;
					removedStamps.add(new Stamp(stampValue, stampCount));
				} else { // Doesn't need all of the greatest denomination
							// available
					int valueRemoved = stampsToRemove * stampValue;
					balance -= valueRemoved;
					stampsNeeded += stampsToRemove;

					Stamp stampsRemoved = new Stamp(stampValue, stampCount
							- stampsToRemove);
					// Add unused stamps of this denomination to the left-overs
					// queue
					leftOverStamps.add(stampsRemoved);
					removedStamps.add(stampsRemoved);
				}
			} else {
				leftOverStamps.add(currentStamp);
			}
		}

		while (!leftOverStamps.isEmpty()) {
			stamps.add(leftOverStamps.remove());
		}

		// After going through all available stamps, still unable to dispense
		// the total value. Restore unused stamps
		if (balance > 0) {
			while (!removedStamps.isEmpty()) {
				stamps.add(removedStamps.remove());
			}
			return -1;
		}
		return stampsNeeded;
	}

	public static void main(String[] args) throws Exception {
		int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
		StampDispenser stampDispenser = new StampDispenser(denominations);
		// Assert was removed since this was not a unit test
		System.out.println("Should be 3: "
				+ stampDispenser.calcMinNumStampsToFillRequest(18));
		stampDispenser.stampsLeft();
		System.out.println("Should be 3: "
				+ stampDispenser.calcMinNumStampsToFillRequest(120));
		stampDispenser.stampsLeft();
		System.out.println("Should be -1: "
				+ stampDispenser.calcMinNumStampsToFillRequest(120));
		stampDispenser.stampsLeft();
	}
}