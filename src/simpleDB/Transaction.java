package simpleDB;

import java.util.*;

public class Transaction {
	private Stack<String[]> commands;
	private Map<String, String> currentState;
	private Map<String, Integer> currentValueCount;

	public Transaction() {
		commands = new Stack<>();
		currentState = new HashMap<>();
		currentValueCount = new HashMap<>();
	}

	public String add(String name, String value) {
		commands.push(new String[] { name, value });

		int valueCount = 0;
		if (currentValueCount.containsKey(value)) {
			valueCount = currentValueCount.get(value);
		}

		if (name.equals("set")) {
			currentState.put(name, value);
			currentValueCount.put(value, ++valueCount);
			return "";
		}
		if (name.equals("unset")) {
			/*
			 * Only reduce a valueCount if the key/value to be removed exists in
			 * the original DB or transaction's state
			 */
			if (currentState.remove(name) != null || Main.database.containsKey(name)) {
				currentValueCount.put(value, --valueCount);
			}
			return "";
		}
		if (name.equals("get")) {
			if (currentState.containsKey(name)) {
				return currentState.get(name);
			}
			return Main.database.get(name);
		}
		if (name.equals("numequalto")) {
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

	public String[] pop() {
		return commands.pop();
	}

	public boolean isEmpty() {
		return commands.isEmpty();
	}

	public String getUpdatedValue(String name) {
		return currentState.get(name);
	}
}
