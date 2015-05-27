package simpleDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static Map<String, String> database;
	static Map<String, Integer> valueCount;

	private static class Transactions {
		private static Stack<Transaction> transactions = new Stack<>();
		private static Set<String> ignoredKeys = new HashSet<>();

		private static Transaction begin(Transaction current) {
			transactions.push(current);
			return new Transaction();
		}

		private static void commit() throws Exception {
			while (!transactions.isEmpty()) {
				transactions.pop().commit(database, ignoredKeys);
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
			System.out.print("Depth: " + Transactions.transactions.size());
			command = br.readLine().trim();
			String[] temp = command.split(" ");
			temp[0] = temp[0].toLowerCase().trim();

			if (temp[0].equals("set") || temp.length == 3) {
				currentTransaction.addCommand(temp[0], temp[1], temp[2]);
			} else if (temp[0].equals("get") || temp[0].equals("unset") || temp[0].equals("numequalto")
					&& temp.length == 2) {
				String reply = currentTransaction.addCommand(temp[0], temp[1]);
				if (!temp[0].equals("unset")) {
					System.out.print(reply);
				}
			} else if (temp[0].equals("begin")) {
				currentTransaction = Transactions.begin(currentTransaction);
			} else if (temp[0].equals("rollback")) {
				Transaction tempTransaction = Transactions.rollback();
				if (tempTransaction == null) {
					System.out.println("NULL");
				} else {
					currentTransaction = tempTransaction;
				}
			} else if (temp[0].equals("commit")) {
				Transactions.commit();
			}
		}
		Transactions.commit();
	}
}
