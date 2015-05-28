package simpleDB;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Transaction {
	private Stack<String[]> commands;
	private Map<String, String> currentState;
	private Map<String, Integer> currentValueCount;
	private Set<String> justDeleted;

	public Transaction() {
		commands = new Stack<>();
		currentState = new HashMap<>();
		currentValueCount = new HashMap<>();
		justDeleted = new HashSet<>();
	}

	public String get(Map<String, String> database, Stack<Transaction> transactions, String key) {
		if (currentState.containsKey(key) || justDeleted.contains(key)) {
			/*
			 * If the get value exists in current state, take it from there. If
			 * it's in justDelete (meaning it exists in the main DB but was
			 * unset in a transaction), then return null
			 */
			return currentState.get(key);
		}

		/*
		 * If the currentState doesn't contain the key, check all transaction
		 * levels before checking main DB
		 */
		Iterator<Transaction> iterator = transactions.iterator();
		while (iterator.hasNext()) {
			Map<String, String> lowerLevelMap = iterator.next().currentState;
			if (lowerLevelMap.containsKey(key)) {
				return lowerLevelMap.get(key);
			}
		}

		return database.get(key);
	}

	public void set(String key, String value) {
		/*
		 * Set: push the command on the stack for easy rollback/commit, put the
		 * addition into the cache, increment cached value count
		 * 
		 * Recall that set <name> <value> is actually 3 parts
		 */
		int valueCount = 0;
		if (currentValueCount.containsKey(value)) {
			valueCount = currentValueCount.get(value);
		}

		commands.push(new String[] { "set", key, value });

		String oldValue = currentState.get(key);
		if (oldValue != null) {
			int oldCount = currentValueCount.get(oldValue);
			currentValueCount.put(oldValue, --oldCount);
		}

		currentState.put(key, value);
		currentValueCount.put(value, ++valueCount);
	}

	public void unset(Map<String, String> database, Stack<Transaction> transactions, String key) {
		/*
		 * Unset: push command onto stack for easy rollback/commit
		 * 
		 * Add the deleted key/value pair to justDeleted if it exists in the
		 * permanent DB. This takes care of the case where unset is called on an
		 * unset value that exists in the permanent DB
		 * 
		 * Decrement the currentValueCount for the key/value pair if the
		 * key/value pair existed in either the permanent or current DBs
		 */

		String value = null;
		boolean exists = false;

		if (database.containsKey(key)) {
			exists = true;
			value = database.get(key);
		} else if (currentState.containsKey(key)) {
			exists = true;
			value = currentState.get(key);
		} else {
			Iterator<Transaction> iterator = transactions.iterator();
			while (iterator.hasNext()) {
				Map<String, String> lowerLevelState = iterator.next().currentState;
				if (lowerLevelState.containsKey(key)) {
					exists = true;
					value = lowerLevelState.get(key);
				}
			}
		}

		if (exists) {
			commands.push(new String[] { "unset", key });
			justDeleted.add(key);

			int valueCount = 0;
			if (currentValueCount.containsKey(value)) {
				valueCount = currentValueCount.get(value);
			}

			currentValueCount.put(value, --valueCount);
			currentState.remove(key);
		}
	}

	public String numequalto(Map<String, Integer> valueMap, Stack<Transaction> transactions, String value) {
		/*
		 * Just return the sum of the counts in the permanent DB and all
		 * transactions
		 */
		Integer current = currentValueCount.get(value);
		if (current == null) {
			current = 0;
		}

		Integer permanent = valueMap.get(value);
		if (permanent == null) {
			permanent = 0;
		}

		Iterator<Transaction> iterator = transactions.iterator();
		while (iterator.hasNext()) {
			Map<String, Integer> lowerLevelValueCount = iterator.next().currentValueCount;
			if (lowerLevelValueCount.containsKey(value)) {
				current += lowerLevelValueCount.get(value);
			}
		}

		return String.valueOf(current + permanent);
	}

	public void commit(Map<String, String> database, Map<String, Integer> valueMap, Set<String> ignoredKeys) throws Exception {
		/*
		 * Because commands are stored in a stack (LIFO), once a key is actioned
		 * upon we can ignore it for all future occurrences
		 */
		while (!commands.isEmpty()) {
			String[] commandArray = commands.pop();
			String key = commandArray[1];

			if (!ignoredKeys.contains(key)) {
				ignoredKeys.add(key);
				String command = commandArray[0];
				String value = commandArray[2];

				if (command.equals("set") || command.equals("unset")) {
					if (command.equals("set")) {
						database.put(key, value);
					} else {
						database.remove(key);
					}
					int currentCount = 0;
					if (currentValueCount.containsKey(value)) {
						currentCount = currentValueCount.get(value);
					}
					int dbCount = 0;
					if (valueMap.containsKey(value)) {
						dbCount = valueMap.get(value);
					}
					valueMap.put(value, currentCount + dbCount);
				} else {
					throw new Exception("Unknown command in transaction command history: " + command + " " + key);
				}
			}
		}
	}
}
