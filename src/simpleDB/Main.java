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

	private static void begin() {

	}

	private static void rollback() {

	}

	private static void commit() {

	}

	public static void main(String[] args) throws Exception {
		database = new HashMap<>();
		transactions = new Stack<>();
		valueCount = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		Transaction t = new Transaction();
		while (!command.equals("end")) {
			command = br.readLine();
			String[] temp = command.split(" ");
			temp[0] = temp[0].toLowerCase().trim();
			if (temp[0].equals("set") || temp.length == 3) {
				t.add(temp[0], temp[1], temp[2]);
			} else if (temp[0].equals("get") || temp[0].equals("unset") || temp[0].equals("numequalto")
					&& temp.length == 2) {
				t.add(temp[0], temp[1]);
			} else if (temp[0].equals("begin") || temp[0].equals("rollback") || temp[0].equals("commit")) {

			}
		}
		t.commit(database);
	}
}
