package puzzles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SimpleDB {
	private static Map<String, String> dbMap; // Name, value
	private static int transactionDepth;

	public static void main(String[] args) throws Exception {
		dbMap = new HashMap<>();
		transactionDepth = 0;

		begin();
	}

	private static void numequalto(String desiredValue) throws Exception {
		if (desiredValue == null) {
			throw new Exception("Command ill-formatted");
		}
		int count = 0;
		for (String value : dbMap.values()) {
			if (value.equals(desiredValue)) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static void unset(String name) throws Exception {
		if (name == null) {
			throw new Exception("Command ill-formatted");
		}

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
	}

	private static void begin() throws Exception {
		Stack<String[]> commands = new Stack<>();
		Map<String, String> prevState = new HashMap<>();

		while (true) {
			System.out.println("SimpleDB> ");
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
					while (!commands.isEmpty()) {
						// Only run the command if it makes a difference in
						// state
						String[] rollBackCommand = commands.pop();

						if (rollBackCommand[0].equals("set")) {
							set(rollBackCommand[1],
									prevState.get(rollBackCommand[1]));
						} else if (rollBackCommand[0].equals("unset")) {
							set(rollBackCommand[1],
									prevState.get(rollBackCommand[1]));
						}
					}
					if (transactionDepth > 0) {
						transactionDepth--;
						return;
					}
					System.out.println("NO TRANSACTION");
					break;
				case "commit":
					// Break the recursion if its within a transaction, else
					// loop
					if (transactionDepth > 0) {
						transactionDepth--;
						return;
					}
					System.out.println("NO TRANSACTION");
					break;
				case "set":
					if (command.length == 3) {
						if (dbMap.containsKey(command[1])) {
							prevState.put(command[1], dbMap.get(command[1]));
						}
						set(command[1], command[2]);
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
