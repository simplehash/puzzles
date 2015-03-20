package puzzles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SimpleDB {
	private static Map<String, String> dbMap; // Name, value
	private static int transactionDepth;
	private static boolean isCommit = false;
	private static Map<String, Integer> valueCount;

	public static void main(String[] args) throws Exception {
		dbMap = new HashMap<>();
		transactionDepth = 0;
		valueCount = new HashMap<>();

		while (true) {
			begin();
			isCommit = false;
		}
	}

	private static void numequalto(String desiredValue) throws Exception {
		if (desiredValue == null) {
			throw new Exception("Command ill-formatted");
		}

		int count = 0;
		if (valueCount.containsKey(desiredValue)) {
			count = valueCount.get(desiredValue);
		}
		System.out.println(count);
	}

	private static void unset(String name) throws Exception {
		if (name == null) {
			throw new Exception("Command ill-formatted");
		}

		String value = dbMap.get(name);
		int count = valueCount.get(value);
		valueCount.replace(value, --count);
		dbMap.remove(name);
	}

	private static void get(String name) throws Exception {
		if (name == null) {
			throw new Exception("Command ill-formatted");
		}

		System.out.println(dbMap.get(name));
	}

	private static void set(String name, String value) throws Exception {
		if (name == null) {
			throw new Exception("Command ill-formatted");
		}

		dbMap.put(name, value);
		int count = 0;
		if (valueCount.containsKey(value)) {
			count = valueCount.get(value);
		}
		valueCount.put(value, ++count);
	}

	private static void begin() throws Exception {
		if (isCommit) {
			return;
		}
		Stack<String[]> commands = new Stack<>();
		Map<String, String> prevState = new HashMap<>();

		while (true) {
			System.out.print("SimpleDB> ");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String input = br.readLine();
			if (input == null) {
				return;
			}
			String[] command = input.toLowerCase().split(" ");

			if (command.length > 0) {
				commands.push(command);
				switch (command[0]) {
				case "end":
					System.exit(0);
				case "begin":
					transactionDepth++;
					begin();
					break;
				case "rollback":
					if (transactionDepth > 0) {
						while (!commands.isEmpty()) {
							// Only run the command if it makes a difference in
							// state
							String[] rollBackCommand = commands.pop();

							if (rollBackCommand.length > 1
									&& rollBackCommand[0] != null
									&& rollBackCommand[1] != null) {
								if (rollBackCommand[0].equals("set")
										|| rollBackCommand[0].equals("unset")) {
									String name = rollBackCommand[1];
									String oldValue = dbMap.get(name); // To
																		// unset
									String value = prevState
											.get(rollBackCommand[1]); // To
																		// set

									if (valueCount.get(oldValue) != null) {
										int oldCount = valueCount.get(oldValue);

										valueCount
												.replace(oldValue, --oldCount);
										if (oldCount == 0) {
											valueCount.remove(oldValue);
										}
									}

									set(name, value);
								}
							}
						}

						transactionDepth--;
						return;
					}
					System.out.println("NO TRANSACTION");
					break;
				case "commit":
					// Break the recursion if its within a transaction, else
					// loop
					if (transactionDepth > 0) {
						isCommit = true;
						transactionDepth = 0;
						return;
					}
					System.out.println("NO TRANSACTION");
					break;
				case "set":
					if (command.length == 3) {
						String name = command[1];
						String value = command[2];
						if (transactionDepth > 0
								&& !(prevState.containsKey(name) && dbMap.get(
										name).equals(value))) {

							prevState.put(name, dbMap.get(name));

						}

						set(name, value);
					}
					break;
				case "get":
					if (command.length == 2) {
						get(command[1]);
					}
					break;
				case "unset":
					if (command.length == 2) {
						prevState.put(command[1], dbMap.get(command[1]));
						unset(command[1]);
					}
					break;
				case "numequalto":
					if (command.length == 2) {
						numequalto(command[1]);
					}
					break;
				}
			}
		}
	}
}
