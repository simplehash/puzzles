package simpleDB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Main {
	private static Map<String, String> database;
	private static Map<String, Integer> valueCount;

	private static class Transactions {
		private static Stack<Transaction> transactions = new Stack<>();
		private static Set<String> ignoredKeys = new HashSet<>();

		private static Transaction begin(Transaction current) {
			transactions.push(current);
			return new Transaction();
		}

		private static void commit() throws Exception {
			while (!transactions.isEmpty()) {
				transactions.pop().commit(database, valueCount, ignoredKeys);
			}
		}

		private static Transaction rollback() {
			if (transactions.isEmpty()) {
				return null;
			}
			return transactions.pop();
		}
	}

	public static void main(String[] args) throws Exception {
		database = new HashMap<>();
		valueCount = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		Transaction currentTransaction = new Transaction();
		while (!command.equalsIgnoreCase("end")) {
			System.out.print("Depth: " + Transactions.transactions.size() + " SimpleDB:> ");
			command = br.readLine().trim();
			String[] temp = command.split(" ");
			temp[0] = temp[0].toLowerCase().trim();

			if (temp[0].equals("set") && temp.length == 3) {
				/* Set commands have 3 parts */
				currentTransaction.addCommand(database, valueCount, Transactions.transactions, temp[0], temp[1], temp[2]);
			} else if ((temp[0].equals("get") || temp[0].equals("unset") || temp[0].equals("numequalto")) && temp.length == 2) {
				/* Get, Unset, and Numequalto commands have 2 parts */
				String reply = currentTransaction.addCommand(database, valueCount, Transactions.transactions, temp[0], temp[1]);
				if (reply == null) {
					reply = "NULL";
				}
				System.out.println(" " + reply);
			} else if (temp[0].equals("begin")) {
				currentTransaction = Transactions.begin(currentTransaction);
			} else if (temp[0].equals("rollback")) {
				Transaction tempTransaction = Transactions.rollback();
				if (tempTransaction == null) {
					System.out.println("NO TRANSACTION");
				} else {
					currentTransaction = tempTransaction;
				}
			} else if (temp[0].equals("commit")) {
				Transactions.transactions.push(currentTransaction);
				Transactions.commit();
				currentTransaction = new Transaction();
			}
		}
		Transactions.commit();
	}
}
