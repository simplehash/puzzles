package puzzles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SimpleDB {
	/*
	 * SET name value – Set the variable name to the value value. Neither
	 * variable names nor values will contain spaces. GET name – Print out the
	 * value of the variable name, or NULL if that variable is not set. UNSET
	 * name – Unset the variable name, making it just like that variable was
	 * never set. NUMEQUALTO value – Print out the number of variables that are
	 * currently set to value. If no variables equal that value, print 0. END –
	 * Exit the program. Your program will always receive this as its last
	 * command.
	 */

	private static Map<String, String> dbMap; // Name, value
	private static int isTransaction;

	public static void main(String[] args) throws Exception {
		dbMap = new HashMap<>();
		isTransaction = 0;

		begin();
	}

	private static void io(String[] command, Map<String, String> prevCommand)
			throws Exception {
		switch (command[0]) {
		case "set":
			if (command.length == 3) {
				if (dbMap.containsKey(command[1])) {
					prevCommand.put(command[1], dbMap.get(command[1]));
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
				prevCommand.put(command[1], dbMap.get(command[1]));
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

	private static void numequalto(String command) throws Exception {
		if (command == null) {
			throw new Exception("Command ill-formatted");
		}
		int count = 0;
		for (String value : dbMap.values()) {
			if (value.equals(command)) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static void unset(String command) throws Exception {
		if (command == null) {
			throw new Exception("Command ill-formatted");
		}

		dbMap.remove(command);
	}

	private static void get(String command) throws Exception {
		if (command == null) {
			throw new Exception("Command ill-formatted");
		}

		System.out.println(dbMap.get(command));
	}

	private static void set(String name, String value) throws Exception {
		if (name == null || value == null) {
			throw new Exception("Command ill-formatted");
		}

		dbMap.put(name, value);
	}

	private static void begin() throws Exception {
		Stack<String[]> commands = new Stack<>();
		Map<String, String> prevState = new HashMap<>();
		boolean done = false;
		while (!done) {
			System.out.println("SimpleDB prompt> ");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String[] command = br.readLine().toLowerCase().split(" ");
			if (command.length > 0) {
				commands.push(command);
				switch (command[0]) {
				case "end":
					System.exit(0);
				case "begin":
					isTransaction++;
					begin();
					break;
				case "rollback":
					isTransaction--;
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
					break;
				case "commit":
					// Break the recursion if its within a transaction, else
					// loop
					if (--isTransaction < 1) {
						break;
					}
					return;
				default:
					io(command, prevState);
					break;
				}
			}
		}
	}
}
