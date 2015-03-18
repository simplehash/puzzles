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

	private static HashMap<String, String> dbMap; // Name, value
	private static String[] command;

	/*
	 * Outer stack contains transaction blocks, inner stack contains commands,
	 * String[] is an individual command
	 */
	private static Stack<Stack<String[]>> commands;

	public static void main(String[] args) throws Exception {
		dbMap = new HashMap<>();
		commands = new Stack<>();
		boolean end = false;

		outerLoop: while (!end) {
			System.out.println("SimpleDB prompt> ");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			command = br.readLine().toLowerCase().split(" ");
			if (command.length > 0) {
				switch (command[0]) {
				case "begin":
					begin();
					break;
				case "rollback":
					rollback();
					break;
				case "commit":
					commit();
					break;
				case "set":
					if (command.length == 3) {
						set();
					}
					break;
				case "get":
					if (command.length == 2) {
						get();
					}
					break;
				case "unset":
					if (command.length == 2) {
						unset();
					}
					break;
				case "numequalto":
					if (command.length == 2) {
						numequalto();
					}
					break;
				case "end":
					break outerLoop;
				}
			}
		}
	}

	private static void numequalto() throws Exception {
		if (command.length != 2) {
			throw new Exception("Command ill-formatted");
		}

		String value = command[1];
		int count = 0;
		for (String eachValue : dbMap.values()) {
			if (eachValue.equals(value)) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static void unset() throws Exception {
		if (command.length != 2) {
			throw new Exception("Command ill-formatted");
		}

		String name = command[1];
		dbMap.remove(name);
	}

	private static void get() throws Exception {
		if (command.length != 2) {
			throw new Exception("Command ill-formatted");
		}

		String name = command[1];
		System.out.println(dbMap.get(name));
	}

	private static void set() throws Exception {
		if (command.length != 3) {
			throw new Exception("Command ill-formatted");
		}
		String name = command[1];
		String value = command[2];

		dbMap.put(name, value);
	}

	private static void commit() {
		// TODO Auto-generated method stub

	}

	private static void rollback() {
		// TODO Auto-generated method stub

	}

	private static void begin() {
		// TODO Auto-generated method stub

	}
}
