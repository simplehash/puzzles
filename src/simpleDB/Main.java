package simpleDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static Map<String, String> database;
	static Map<String, Integer> valueCount;
	private static Stack<Stack<String[]>> transactions;

	private static int transactionDepth() {
		if (transactions == null) {
			return -1;
		}
		return transactions.size();
	}

	private static void unset(String name) {

	}

	private static void get(String name) {

	}

	private static void numequalto(String name) {

	}

	private static void set(String name, String value) {

	}

	private static void begin() {

	}

	private static void rollback() {

	}

	private static void commit() {

	}

	private static void runTransactionCommand(String name, String value) {

	}

	public static void main(String[] args) {
		database = new HashMap<>();
		transactions = new Stack<>();
		valueCount = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		Transaction t = new Transaction();
		while (!command.equals("end")) {
			try {
				command = br.readLine();
				String[] temp = command.split(" ");
				temp[0] = temp[0].toLowerCase().trim();
				if (temp[0].equals("set") || temp.length == 3) {

				} else if (temp[0].equals("get") || temp[0].equals("unset") || temp[0].equals("numequalto")
						&& temp.length == 2) {
					t.add(temp[0], temp[1]);
				} else if (temp[0].equals("begin") || temp[0].equals("rollback") || temp[0].equals("commit")) {

				}
			} catch (IOException e) {
				System.err.println("I/O error while reading command.");
				return;
			}
		}
		while (!t.isEmpty()) {

		}
	}
}
