package simpleDB;

import java.util.*;

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

	public void set(String key, String value, Stack<Transaction> transactions, Map<String, String> db,
			Map<String, Integer> countMap) {
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

		/*
		 * If we are unsetting a value that doesn't exist in the current
		 * transaction but does in lower level ones or in the main DB, check
		 */
		String oldValue = null;
		int oldValueCount = 0;
		boolean exists = false;

		if (currentState.containsKey(key)) {
			// Reducing value count in current block
			oldValue = currentState.get(key);
			/*
			 * oldValueCount is only updated if the value exists in the current
			 * transaction block because each transaction stores the delta of
			 * value counts
			 */
			oldValueCount = currentValueCount.get(value);
			exists = true;
		} else {
			/*
			 * Check lower level transactions for the key's value and the
			 * value's count
			 */
			Iterator<Transaction> i = transactions.iterator();
			while (i.hasNext()) {
				Transaction lowerLevel = i.next();
				Map<String, String> lowerLevelDB = lowerLevel.currentState;
				if (lowerLevelDB.containsKey(key)) {
					oldValue = lowerLevelDB.get(key);
					exists = true;
					break;
				}
			}
		}
		/*
		 * Check the main DB for the key's value and the value's count
		 */
		if (oldValue == null && db.containsKey(key)) {
			oldValue = db.get(key);
			exists = true;
		}

		if (exists) {
			currentValueCount.put(oldValue, --oldValueCount);
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
					break;
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
				break;
			}
		}

		return String.valueOf(current + permanent);
	}

	public void commit(Map<String, String> database, Map<String, Integer> valueMap, Set<String> ignoredKeys) {
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
				String value = null;
				if (commandArray.length == 3) {
					value = commandArray[2];
				}

				if (command.equals("set")) {
					database.put(key, value);
				} else if (command.equals("unset")) {
					database.remove(key);
				}
			}
		}

		Iterator<String> i = currentValueCount.keySet().iterator();
		while (i.hasNext()) {
			String value = i.next();

			int currentCount = currentValueCount.get(value);

			int dbCount = 0;
			if (valueMap.containsKey(value)) {
				dbCount = valueMap.get(value);
			}

			if (currentCount + dbCount > 0) {
				valueMap.put(value, currentCount + dbCount);
			} else {
				valueMap.remove(value);
			}
		}
	}
}
