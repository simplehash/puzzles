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

	public String addCommand(String... args) {
		String command = args[0];
		String key = null;
		String value = null;
		int valueCount = 0;

		if (command.equals("get") || command.equals("unset")) {
			key = args[1];
		} else if (command.equals("set") || command.equals("numequalto")) {
			if (command.equals("set")) {
				key = args[1];
				value = args[2];
			} else {
				value = args[1];
			}
			if (currentValueCount.containsKey(value)) {
				valueCount = currentValueCount.get(value);
			}
		}

		if (command.equals("set")) {
			/*
			 * Set: push the command on the stack for easy rollback/commit, put
			 * the addition into the cache, increment cached value count
			 * 
			 * Recall that set <name> <value> is actually 3 parts
			 */
			commands.push(new String[] { command, key, value });
			currentState.put(key, value);
			currentValueCount.put(value, ++valueCount);
			return "";
		}
		if (command.equals("unset")) {
			/*
			 * Unset: push command onto stack for easy rollback/commit
			 * 
			 * Add the deleted key/value pair to justDeleted if it exists in the
			 * permanent DB. This takes care of the case where unset is called
			 * on an unset value that exists in the permanent DB
			 * 
			 * Decrement the currentValueCount for the key/value pair if the
			 * key/value pair existed in either the permanent or current DBs
			 */
			commands.push(new String[] { command, key });
			/*
			 * Only reduce a valueCount if the key/value to be removed exists in
			 * the original DB or transaction's state
			 */
			if (Main.database.containsKey(key)) {
				justDeleted.add(key);
			}
			if (Main.database.containsKey(key) || currentState.containsKey(key)) {
				currentValueCount.put(value, --valueCount);
				currentState.remove(key);
			}
			return "";
		}
		if (command.equals("get")) {
			if (currentState.containsKey(key) || justDeleted.contains(key)) {
				/*
				 * If the get value exists in current state, take it from there.
				 * If it's in justDelete (meaning it exists in the main DB but
				 * was unset in a transaction), then return null
				 */
				return currentState.get(key);
			}
			return Main.database.get(key);
		}
		if (command.equals("numequalto")) {
			/*
			 * Jsut return the sum of the counts in the permanent DB and current
			 * transaction's state
			 */
			Integer current = currentValueCount.get(value);
			Integer permanent = Main.valueCount.get(value);
			if (current == null) {
				current = 0;
			}
			if (permanent == null) {
				permanent = 0;
			}
			return String.valueOf(current + permanent);
		}
		return null;
	}

	public void commit(Map<String, String> database, Set<String> ignoredKeys) throws Exception {
		/*
		 * Because commands are stored in a stack (LIFO), once a key is actioned
		 * upon we can ignore it for all future occurrences
		 */
		while (!commands.isEmpty()) {
			String[] commandArray = commands.pop();
			String key = commandArray[1];

			if (!ignoredKeys.contains(key)) {
				String command = commandArray[0];
				String value = commandArray[2];

				if (command.equals("set")) {
					database.put(key, value);
				} else if (command.equals("unset")) {
					database.remove(key);
				} else {
					throw new Exception("Unknown command in transaction command history: " + command + " " + key);
				}
				ignoredKeys.add(key);
			}
		}
	}
}
