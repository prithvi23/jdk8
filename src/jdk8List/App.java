package jdk8List;

import java.util.List;


public class App {
	/**
	 * Demonstrates forEach
	 * @param args
	 */
	public static void main(String[] args) {
		List<Transaction> transactions = ListOperations.populateRandomTransactions(10);
		ListOperations.print(transactions);
		ListOperations.groceryTransactions7(transactions).forEach(System.out::println);
		ListOperations.print(ListOperations.getSortedList());
	}
}
