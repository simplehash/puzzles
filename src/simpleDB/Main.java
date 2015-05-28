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
			ignoredKeys.clear();
		}

		private static Transaction rollback() {
			if (transactions.isEmpty()) {
				return null;
			}
			return transactions.pop();
		}

		private static int depth() {
			return transactions.size();
		}
	}

	public static void main(String[] args) throws Exception {
		database = new HashMap<>();
		valueCount = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		Transaction currentTransaction = new Transaction();

		while (!command.equalsIgnoreCase("end")) {
			System.out.print("SimpleDB:> ");
			command = br.readLine().trim();
			String[] temp = command.split(" ");
			temp[0] = temp[0].toLowerCase();

			if (temp[0].equals("set") && temp.length == 3) {
				currentTransaction.set(temp[1], temp[2], Transactions.transactions, database, valueCount);

				/* Auto-commit if not in transaction block */
				if (Transactions.depth() == 0) {
					Transactions.transactions.push(currentTransaction);
					Transactions.commit();
					currentTransaction = new Transaction();
				}
			} else if (temp.length == 2) {
				String reply = null;
				if (temp[0].equals("get")) {
					reply = currentTransaction.get(database, Transactions.transactions, temp[1]);
					if (reply == null) {
						reply = "NULL";
					}
					System.out.println(" " + reply);
				} else if (temp[0].equals("unset")) {
					currentTransaction.unset(database, Transactions.transactions, temp[1]);

					if (Transactions.depth() == 0) {
						Transactions.transactions.push(currentTransaction);
						Transactions.commit();
						currentTransaction = new Transaction();
					}
				} else if (temp[0].equals("numequalto")) {
					reply = currentTransaction.numequalto(valueCount, Transactions.transactions, temp[1]);
					if (reply == null) {
						reply = "0";
					}
					System.out.println(" " + reply);
				}
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
				if (Transactions.depth() == 0) {
					System.out.print("NO TRANSACTION");
				} else {
					Transactions.transactions.push(currentTransaction);
					Transactions.commit();
					currentTransaction = new Transaction();
				}
			}
		}
	}
}
